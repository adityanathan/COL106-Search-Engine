import java.util.HashMap; 
import java.util.Map; 

class MyHashTable
{
    private HashMap< Integer, MyLinkedList<WordEntry> > hm = new HashMap<>();

    private int getHashIndex(String str)
    {
        int i;
        int hash=0;
        for(i=0; i<str.length(); i++)
        {
            char c=str.charAt(i);
            hash = hash+(((int) c) *(i+1));
        }
        return hash%2069;
    }

    void addPositionsForWord(WordEntry w)
    {
        int hash_value=getHashIndex(w.getWord());
        if(hm.containsKey(hash_value))
        {
            MyLinkedList<WordEntry> list = hm.get(hash_value);
            for(int i=0; i<list.getSize(); i++)
            {
                if(list.get(i).getWord().equals(w.getWord()))
                {
                    list.get(i).addPositions(w.getAllPositionsForThisWord());
                    return;
                }
            }
            list.add(w);
            return;
        }
        else
        {
            MyLinkedList<WordEntry> newest = new MyLinkedList<>();
            newest.add(w);
            hm.put(hash_value, newest);
        }
    }

    WordEntry inverseHash(String str)
    {
        int hash_value=getHashIndex(str);
        int i;
        if(hm.containsKey(hash_value))
        {
            MyLinkedList<WordEntry> list = hm.get(hash_value);
            for(i=0; i<list.getSize();i++)
            {
                if(list.get(i).getWord().equals(str))
                {
                    return list.get(i);
                }
            }
        }
        return null;
    }
}
