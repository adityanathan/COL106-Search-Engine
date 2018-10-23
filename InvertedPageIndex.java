class InvertedPageIndex
{
    private MyHashTable inverted_index;
    private MySet<String> list_of_all_pages;

    InvertedPageIndex()
    {
        inverted_index=new MyHashTable();
        list_of_all_pages= new MySet<String>();
    }
    void addPage(PageEntry p)
    {
        MyLinkedList<WordEntry> list_of_words_in_page=p.getPageIndex().getWordEntriesForThisPage();
        int i;
        WordEntry word;
        for(i=0; i<list_of_words_in_page.getSize(); i++)
        {
            word=list_of_words_in_page.get(i);
            inverted_index.addPositionsForWord(word);
        }
        if(!list_of_all_pages.setContains(p.getPageName()))
        {
            list_of_all_pages.addElement(p.getPageName());
        }
    }

    MySet<PageEntry> getPagesWhichContainWord(String str)
    {
        MySet<PageEntry> result = new MySet<PageEntry>();
        WordEntry word=inverted_index.inverseHash(str);
        if(!(word==null))
        {
            MyLinkedList<Position> list_of_positions = word.getAllPositionsForThisWord();
            int i;
            for(i=0; i<list_of_positions.getSize(); i++)
            {
                if(!result.setContains(list_of_positions.get(i).getPageEntry()))
                {
                    result.addElement(list_of_positions.get(i).getPageEntry());
                }
            }

            return result;
        }
        return null;
    }

    boolean page_exist(String str)
    {
        for(int i=0; i<list_of_all_pages.size(); i++)
        {
            if(list_of_all_pages.getElement(i).equals(str))
            {
                return true;
            }
        }
        return false;
    }
}
