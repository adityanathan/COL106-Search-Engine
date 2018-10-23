import java.util.Scanner;
import java.io.*;

class PageEntry
{
    private PageIndex page_index = new PageIndex();
    private String page_name;
    int wordcount=0;
    int ncwordcount=0;

    boolean isConnector(String str)
    {
        String[] c = new String[]{"a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or", "and", "does", "will", "whose"};
        for(int i = 0; i<c.length; i++)
        {
            if(str.equals(c[i]))
            {
                return true;
            }
        }
        return false;
    }

    Boolean isPunctuation(char c)
    {
        char[] l = new char[]{'{','}','[',']','<','>','=','(',')','.',',',';',(char)39,'"','?','#','!','-',':',' '};
        for(int i = 0; i<l.length; i++)
        {
            if(c==l[i])
            {
                return true;
            }
        }
        return false;
    }

    PageEntry(String pageName) throws FileNotFoundException
    {
        this.page_name=pageName.substring(9,pageName.length()); //removing the "webpages/" part.
        Scanner sc = new Scanner(new File(pageName));
        String line=""; String word="";
        while(sc.hasNextLine())
        {
            line=sc.nextLine();
            int i=0; int j=0;
            while(i<line.length() && j<line.length())
            {
                while(!isPunctuation(line.charAt(j)))
                {
                   j++;
                   if(j>=line.length())
                   {
                       break;
                   }
                }
                word=line.substring(i,j).toLowerCase();
                if(word.length()>0)
                {
                    this.wordcount++;
                    if(!isConnector(word))
                    {
                        if(word.equals("stacks"))
                        {
                            word="stack";
                        }
                        else if(word.equals("structures"))
                        {
                            word="structure";
                        }
                        else if(word.equals("applications"))
                        {
                            word="application";
                        }

                        Position p = new Position(this, wordcount);
                        this.page_index.addPositionForWord(word, p);
                        this.ncwordcount++;
                    }
                }
                j++;
                i=j;
            }
        }
    }

    String getPageName()
    {
        return this.page_name;
    }

    PageIndex getPageIndex()
    {
        return this.page_index;
    }

    float getTermFrequency(String word)
    {
        MyLinkedList<WordEntry> a = this.page_index.getWordEntriesForThisPage();
        int term_counter=0;
        for(int i=0; i<a.getSize(); i++)
        {
            if(a.get(i).getWord().equals(word))
            {
                term_counter++;
            }
        }
        return ((float)term_counter)/((float)this.wordcount);
    }

    MyLinkedList<Integer> getWordIndex(String w) // Returns list of word occurrences in this page as a list of indices.
    {
        MyLinkedList<WordEntry> x = page_index.getWordEntriesForThisPage();
        int i;
        MyLinkedList<Integer> result = new MyLinkedList<>();
        MyLinkedList<Position> temp = null;
        for(i=0; i<x.getSize(); i++)
        {
            if(x.get(i).getWord().equals(w))
            {
                temp = x.get(i).getAllPositionsForThisWord();
                break;
            }
        }

        if(!(temp==null || temp.getSize()==0))
        {
            for(i=0; i<temp.getSize(); i++)
            {
                if(temp.get(i).getPageEntry().getPageName().equals(this.page_name))
                {
                    result.add(temp.get(i).getWordIndex());
                }
            }
            return result;
        }

        return null;
    }
}
