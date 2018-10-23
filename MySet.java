class MySet<X>
{
    private MyLinkedList<X> set;

    MySet()
    {
        set=new MyLinkedList<X>();
    }

    int size()
    {
        return set.getSize();
    }

    X getElement(int i)
    {
        return set.get(i);
    }

    boolean setContains(X i)
    {
        return set.contains(i);
    }

    void addElement(X element)
    {
        set.add(element);
    }

    MySet<X> union(MySet<X> otherSet)
    {
        MySet<X> result = new MySet<X>();
        int i;
        for(i=0; i<this.size(); i++)
        {
            result.addElement(this.getElement(i));
        }

        for(i=0; i<otherSet.size(); i++)
        {
            if(!result.setContains(otherSet.getElement(i)))
            {
                result.addElement(otherSet.getElement(i));
            }
        }

        return result;
    }

    MySet<X> intersection(MySet<X> otherSet)
    {
        MySet<X> result = new MySet<X>();
        int i;
        for(i=0;i<otherSet.size(); i++)
        {
            if(this.setContains(otherSet.getElement(i)))
            {
                result.addElement(otherSet.getElement(i));
            }
        }
        return result;
    }
}
