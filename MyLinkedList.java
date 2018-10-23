class MyLinkedList<X>
{
    class node<X>
{
    private X data;
    private node<X> next;

    node(X o)
    {
        this.data=o;
        this.next=null;
    }

    public X getData()
    {
        return this.data;
    }

    public node<X> getNext()
    {
        return this.next;
    }

    public void setNext(node<X> newest)
    {
        this.next=newest;
    }
}


    private node<X> head;
    private int size;

    void MyLinkedList()
    {
        this.head=null;
        this.size=0;
    }

    public int getSize()
    {
        return this.size;
    }

//MyLinkedList<X>.contains - returns true if element present else false.
    public boolean contains(X o)
    {
        node<X> marker=this.head;
        while(marker!=null)
        {
            if(marker.getData()==o)
            {
                return true;
            }
            marker=marker.getNext();
        }
        return false;
    }

    public void add(X o)
    {
        node<X> newest = new node<X>(o);
        node<X> marker=this.head;
        if(marker==null)
        {
            this.head=newest;
        }
        else
        {
            while(marker.getNext()!=null)
            {
                marker=marker.getNext();
            }
            marker.setNext(newest);
        }
        this.size++;
    }

//MyLinkedList<X>.remove - return true if element present and removed else return false.
    public boolean remove(X o)
    {
        if(!this.contains(o))
        {
            return false;
        }
        else
        {
            node<X> element_to_be_deleted=null;
            node<X> marker=this.head;
            while(marker!=null)
            {
                if(marker.getData()==o)
                {
                    element_to_be_deleted = marker;
                    break;
                }
                marker=marker.getNext();
            }
            marker=this.head;
            while(marker!=null)
            {
                if(this.head==element_to_be_deleted)
                {
                    this.head=this.head.getNext();
                    element_to_be_deleted=null;
                    break;
                }
                else if(marker.getNext()==element_to_be_deleted)
                {
                    marker.setNext(element_to_be_deleted.getNext());
                    element_to_be_deleted=null;
                    break;
                }
            }
            this.size--;
            return true;
        }
    }

//MyLinkedList<X>.get - return data element if present, else return null.
    public X get(int i)
    {
        node<X> marker = this.head;
        if(i==0)
        {
            return marker.getData(); //WARNING: CHECK FOR ERRORS HERE
        }
        else if(i<this.size)
        {
            while(i>0)
            {
                marker=marker.getNext();
                i--;
            }
            return marker.getData(); //WARNING: CHECK FOR ERRORS HERE
        }

        return null;
    }

/*    public MyLinkedList<X> getUnion(MyLinkedList<X> b)
    {
        int i;
        MyLinkedList<X> result = new MyLinkedList<X>();
        for(i=0; i<this.getSize(); i++)
        {
            result.add(this.get(i));
        }
        for(i=0; i<b.getSize(); i++)
        {
            if(!result.contains(b.get(i)))
            {
                result.add(b.get(i));
            }
        }
        return result;
    }
*/
}
