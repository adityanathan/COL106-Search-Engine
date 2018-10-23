class Position
{
    private PageEntry page;
    private int word_index;

    Position(PageEntry p, int wordIndex)
    {
        this.page=p;
        this.word_index=wordIndex;
    }

    PageEntry getPageEntry()
    {
        return this.page;
    }

    int getWordIndex()
    {
        return this.word_index;
    }
}
