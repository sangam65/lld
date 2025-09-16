package HashMap;

public class Entry<K,V> {
    private K key;
    private V value;
    @SuppressWarnings("rawtypes")
    private Entry next;
    public Entry(K key,V value){
        this.key=key;
        this.value=value;

        this.next=null;
    }

    @SuppressWarnings("rawtypes")
    public void setNext(Entry next){
        this.next=next;
    }
    @SuppressWarnings("rawtypes")
    public Entry getNext(){
        return next;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
}
