import java.util.Scanner;

public class WordEntry
{
    private String word;
    private MyLinkedList<Position> entries;

    WordEntry(String word)
    {
        this.word=word;
        entries = new MyLinkedList<>();

    }

    String getWord()
    {
        return this.word;
    }

    void addPosition(Position position)
    {
        entries.add(position);
    }

    void addPositions(MyLinkedList<Position> positions)
    {
        for(int i=0; i<positions.getSize(); i++)
        {
            addPosition(positions.get(i));
        }
    }

    MyLinkedList<Position> getAllPositionsForThisWord()
    {
        return this.entries;
    }
}