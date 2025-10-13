package HashMap;



import HashMap.exception.KeyNotFoundException;

public class MyMap<K,V> {
    private final static int capacity=1<<4;
    private final static int maxCapacity=1<<16;
    
    
    @SuppressWarnings("rawtypes")
    private Entry hashTable[];
    private int size;
    public MyMap(){
        this.hashTable=new Entry[capacity];
        this.size=0;
    }
    public MyMap(int sz){
        int mx=maximumCapacity(sz);
        this.hashTable=new Entry[mx];
    }
    private int maximumCapacity(int size){
        if(size<=0) 
        return size;
        if(size>maxCapacity)
        return maxCapacity;
        return size;
    }


    private int calculateHash(K key){
        int hashCode=key.hashCode();
        int hashTableSize=hashTable.length;
        return hashCode%hashTableSize;

    }

    @SuppressWarnings("unchecked")
    public void put(K key,V value){
        
        @SuppressWarnings("rawtypes")
        Entry entry=containsKey(key);
        if(entry!=null){
            entry.setValue(value);


        }
        else{
            
            @SuppressWarnings("rawtypes")
            Entry newEntry=new Entry(key,value);
            int hashCode=calculateHash(key);
            
            @SuppressWarnings("rawtypes")
            Entry oldEntry=hashTable[hashCode];
            
            if(oldEntry!=null){
                
                @SuppressWarnings("rawtypes")
                Entry prev=null;
                while(oldEntry!=null){
                    prev=oldEntry;
                    oldEntry=oldEntry.getNext();

                }
                prev.setNext(newEntry);
            }
            else{
                hashTable[hashCode]=newEntry;
            }
            size++;
        

        }
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public V get(K key) throws KeyNotFoundException{
        
        Entry entry=containsKey(key);
        if(entry!=null)
            return (V)entry.getValue();
        throw new KeyNotFoundException("Key not found");

    }
    
    @SuppressWarnings("unchecked")
    private Entry<K,V> containsKey(K key){
        int hash=calculateHash(key);
        @SuppressWarnings("rawtypes")
        Entry entry=hashTable[hash];
        while(entry!=null){
            if(entry.getKey().equals(key)){
                return entry;
            }
            entry=entry.getNext();
        }


        throw new KeyNotFoundException("Key not found");
    }

    

    @SuppressWarnings("unchecked")
    public void remove(K key){
        @SuppressWarnings("rawtypes")
        Entry entry=containsKey(key);
        @SuppressWarnings("rawtypes")
        Entry prevEntry=null;
        int hashCode=calculateHash(key);
        @SuppressWarnings("rawtypes")
        Entry iter=hashTable[hashCode];
        while(iter!=null){
            if(iter==entry){
                break;
            }
            prevEntry=iter;
            iter=iter.getNext();
        }
        if(prevEntry==null){
            hashTable[hashCode]=entry.getNext();
        }
        else{
            prevEntry.setNext(entry.getNext());
        }
        size--;

        
    }
    public int capacity(){
        return hashTable.length;
    }
    
    @SuppressWarnings("hiding")
    private class Entry<K,V> {
        private final K key;
        private V value;
        private Entry<K,V> next;
        public Entry(K key,V value){
            this.key=key;
            this.value=value;
            this.next=null;
        }
        public V getValue(){
            return value;
        }
        public K getKey(){
            return key;
        }
        public void setNext( Entry<K,V> next){
            this.next=next;
        }

        public Entry<K,V> getNext(){
            return next;
        }
        public void setValue(V value){
            this.value=value;
        }   
    }
   
    public int size(){
        return size;
    }



    
}
