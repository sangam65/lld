package LRU;

public class Element {
    private int key;
    private int value;
    
    private Element prev;
    private Element next;
    public Element(int key,int value){
        this.key=key;
        this.value=value;
        this.prev=null;
        this.next=null;
    }
    public Element getPrev() {
        return prev;
    }
    public void setPrev(Element prev) {
        this.prev = prev;
    }
    public Element getNext() {
        return next;
    }
    public void setNext(Element next) {
        this.next = next;
    }
    public int getKey() {
        return key;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }


    
}
