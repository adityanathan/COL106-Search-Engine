import java.util.regex.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Sort positions according to relevance. Go through notes at the end of the assignment. Throw Exceptions
class SearchEngine
{
    private InvertedPageIndex myIndex;

    SearchEngine()
    {
        myIndex=new InvertedPageIndex();
    }

    void performAction(String actionMessage) throws FileNotFoundException
    {
        int i; String str; String x=""; String y=null; String result=null; MySet<PageEntry> all_pages=null; MyLinkedList<Integer> word_indices=null;
        if(Pattern.matches("addPage \\w+\\W*", actionMessage))
        {
            str=actionMessage.substring(8,actionMessage.length());
            PageEntry newPage = new PageEntry("webpages/"+str);
            myIndex.addPage(newPage);
            //System.out.println(str);
            //System.out.println("webpages/"+str);
        }

        else if(Pattern.matches("queryFindPagesWhichContainWord \\w+\\W*", actionMessage))
        {
            str=actionMessage.substring(31,actionMessage.length()).replaceAll("([{}\\[\\]<>=().,;’\"?#!\\-:])", " ").toLowerCase();
            //System.out.println(str);
            if(str.equals("stacks"))
            {
                str="stack";
            }
            else if(str.equals("structures"))
            {
                str="structure";
            }
            else if(str.equals("applications"))
            {
                str="application";
            }
            MySet<PageEntry> answer = myIndex.getPagesWhichContainWord(str);
            //System.out.println(answer);
            if(answer==null)
            {
                System.out.println("No webpage contains word "+actionMessage.substring(31,actionMessage.length()));
            }
            else
            {
                for(i=0; i<answer.size()-1; i++)
                {
                    //System.out.println(answer.getElement(i).getPageName());
                    x=x+answer.getElement(i).getPageName()+", ";
                }
                x=x+answer.getElement(answer.size()-1).getPageName();
                System.out.println(x);
            }
        }

        else if(Pattern.matches("queryFindPositionsOfWordInAPage \\w+\\W* \\w+", actionMessage))
        {
            for(i=32;i<actionMessage.length();i++)
            {
                if(actionMessage.charAt(i)==' ')
                {
                    break;
                }
            }
            x = actionMessage.substring(32,i).replaceAll("([{}\\[\\]<>=().,;’\"?#!\\-:\\n\\r\\t])", " ").toLowerCase();
            y = actionMessage.substring(i+1,actionMessage.length());
            if(x.equals("stacks"))
            {
                x="stack";
            }
            else if(x.equals("structures"))
            {
                x="structure";
            }
            else if(x.equals("applications"))
            {
                x="application";
            }
            if(!myIndex.page_exist(y))
            {
                System.out.println("No webpage "+y+" found");
                return;
            }
            all_pages = myIndex.getPagesWhichContainWord(x);
            //System.out.println(x);
            //System.out.println(y);
            result="";
            if(all_pages==null )
            {
                System.out.println("Webpage "+y+" does not contain word "+x);
            }
            else
            {
                for(i=0; i<all_pages.size(); i++)
                {
                    if(all_pages.getElement(i).getPageName().equals(y))
                    {
                        word_indices = all_pages.getElement(i).getWordIndex(x);
                        break;
                    }
                }
                if(word_indices==null)
                {
                    System.out.println("Webpage "+y+" does not contain word "+x);
                }
                else
                {
                    //System.out.println(word_indices.getSize());
                    for(i=0; i<word_indices.getSize(); i++)
                    {
                        result = result + Integer.toString(word_indices.get(i))+", ";
                    }
                    System.out.println(result.substring(0, (result.length()-2)));
                }
            }
        }

        else
        {
            System.out.println("Invalid Input");
        }
        return;
    }
}
