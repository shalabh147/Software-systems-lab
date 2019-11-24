/** @file LinkList.java
@author Team Dominatrix
@details Illustrates doxygen-style comments for documenting a Java
program file and the functions in that file.
Because this is a java file all it contains are classes.  
*/

/**
    A class implementing a Deque using a doubly linked list.
    \n Deque (DeQueue) stands for Double-ended Queue. \n
    It is just like a queue but does not support FIFO structure.
    \n Insertion and deletion can be done from both side( FRONT & REAR).
    \n Implementation uses the Node class. Data members are a couple of nodes
    to store the data at the front and rear of the deque.
    Member functions have been documentated.
    @tparam T type of the elements that can be stored in the deque
*/
public class LinkList<T> {
 
    private Node<T> front;
    private Node<T> rear;
     
    /**
    Insert item at the front of the linked list 
    @param item element to be inserted
    @return void
    */ 
    public void insertFront(T item){
        //add element at the beginning of the queue
        System.out.println("adding at front: "+item);
        Node<T> nd = new Node<T>();
        nd.setValue(item);
        nd.setNext(front);
        if(front != null) front.setPrev(nd);
        if(front == null) rear = nd;
        front = nd;
    }
     
    /**
    Insert item at the rear of the linked list 
    @param item element to be inserted
    @return void
    */ 
    public void insertRear(T item){
        //add element at the end of the queue
        System.out.println("adding at rear: "+item);
        Node<T> nd = new Node<T>();
        nd.setValue(item);
        nd.setPrev(rear);
        if(rear != null) rear.setNext(nd);
        if(rear == null) front = nd;
         
        rear = nd;
    }
    
    /**
    Removes the item at the front of the linked list 
    @return void
    */ 
    public void removeFront(){
        if(front == null){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        }
        //remove an item from the beginning of the queue
        Node<T> tmpFront = front.getNext();
        if(tmpFront != null) tmpFront.setPrev(null);
        if(tmpFront == null) rear = null;
        System.out.println("removed from front: "+front.getValue());
        front = tmpFront;
    }
    
    /**
    Removes the item at the rear of the linked list 
    @return void
    */ 
    public void removeRear(){
        if(rear == null){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        }
        //remove an item from the beginning of the queue
        Node<T> tmpRear = rear.getPrev();
        if(tmpRear != null) tmpRear.setNext(null);
        if(tmpRear == null) front = null;
        System.out.println("removed from rear: "+rear.getValue());
        rear = tmpRear;
    }
    /**
    The main function. \n Contains several calls to member of LinkList class to an 
    instance created inside the function
    */ 
    public static void main(String a[]){
        LinkList<Integer> deque = new LinkList<Integer>();
        deque.insertFront(34);
        deque.insertFront(67);
        deque.insertFront(29);
        deque.insertFront(765);
        deque.removeFront();
        deque.removeFront();
        deque.removeFront();
        deque.insertRear(43);
        deque.insertRear(83);
        deque.insertRear(84);
        deque.insertRear(546);
        deque.insertRear(356);
        deque.removeRear();
        deque.removeRear();
        deque.removeRear();
        deque.removeRear();
        deque.removeFront();
        deque.removeFront();
        deque.removeFront();
    }
}

/**
@class
A class called node which is used to implement the LinkList class.
Idea is that a node is an object which has a next node and a previous node and stores a value.
Public member functions have been documented.

@tparam T type of the elements that can be stored in the node 
*/
class Node<T>{
     
    private Node<T> prev;
    private Node<T> next;
    private T value;
    
    /**
    Returns the previous node of the node on which this function is applied 
    */ 
    public Node<T> getPrev() {
        return prev;
    }

    /**
    Sets the previous node of the node on which this function is applied  
    @return void
    */ 
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
    Returns the next node of the node on which this function is applied 
    */
    public Node<T> getNext() {
        return next;
    }

    /**
    Sets the next node of the node on which this function is applied 
    @return void
    */ 
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /*
    Returns the value stored in the node
    */
    public T getValue() {
        return value;
    }

    /*
    Gives a value to the node
    @return void
    */
    public void setValue(T value) {
        this.value = value;
    }
}