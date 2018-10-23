class PageIndex
{
    private MyLinkedList<WordEntry> page_entries = new MyLinkedList<>();
    void addPositionForWord(String str, Position p)
    {
        for(int i=0; i<page_entries.getSize(); i++)
        {
            if(page_entries.get(i).getWord().equals(str))
            {
                page_entries.get(i).addPosition(p);
                return;
            }
        }
        WordEntry newest = new WordEntry(str);
        newest.addPosition(p);
        page_entries.add(newest);
        return;
    }

    MyLinkedList<WordEntry> getWordEntriesForThisPage()
    {
        return this.page_entries;
    }
}
