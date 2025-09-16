package HashMap;

public class HashMap<K,V> {
    public static final int initialCapacity=1<<4;
    public static final int maxCapacity=1<<16;
    @SuppressWarnings("rawtypes")
    Entry hashTable[];
    
    public HashMap(){
     
        hashTable=new Entry[initialCapacity];
    }
    public HashMap(int capacity){
        int mx=findCapacity(capacity);
        hashTable=new Entry[mx];
    }
    int findCapacity(int mx){
        if(mx<=0){
            return 1;
        }
        return mx;

        // ret
    }

    int calculateHashValue(K key){
        int len=hashTable.length;
        int hashCode=key.hashCode();
        return hashCode %len;
    }
    public void put(K key ,V value){

    }
    @SuppressWarnings("unchecked")
    public V getValue(K key){
        int hashValue=calculateHashValue(key);
        if(hashTable[hashValue]==null){
            return null;
        }
        @SuppressWarnings("rawtypes")
        Entry entry=hashTable[hashValue];
        while(entry!=null){
            if(entry.getKey().equals(key)){
                return (V) entry.getValue();
            }
            entry=entry.getNext();
        }
        return null;
    }
   

    


}
