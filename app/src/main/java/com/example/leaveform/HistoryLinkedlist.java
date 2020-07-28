package com.example.leaveform;

class HistoryLinkedlist
{
    static class Node
    {
        private History history;
        private Node link;
    }

    Node head;
    int count=0;

    public void insertItem(History item)
    {
        if(count==0)
        {
            head= new Node();
            head.history=item;
            head.link=null;
            count++;
        }
        else
        {
            Node n= new Node();
            n.history=item;
            n.link=head;
            head=n;
        }
    }
    public History getHistory(int n)
    {
        Node z=head;
        for(int i=0;i<n;i++)
        {
            z=z.link;
        }
        return z.history;
    }

}
