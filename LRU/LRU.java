package LRU;

import java.util.HashMap;

public class LRU {
    private  HashMap<Integer,Element> cache;
    private final int capacity;
    private final Element first;
    private final Element last;
  
    public LRU(int capacity){
        this.capacity=capacity;
        
        cache=new HashMap<Integer,Element>();
        this.first=new Element(-1,-1);
        this.last=new Element(-1,-1);
        this.first.setNext(last);
        this.last.setPrev(first);



        

    }

    public void addElement(int key,int value){
        if(cache.containsKey(key)){
            Element curElement=cache.get(key);
            curElement.setValue(value);
            
            Element curFirst=first.getNext();
            if(curFirst.getKey()==curElement.getKey()){
            return;
            }
             Element curPrev=curElement.getPrev();
            Element curNext=curElement.getNext();

            curPrev.setNext(curNext);
            curNext.setPrev(curPrev);

         
            curFirst.setPrev(curElement);
            curElement.setNext(curFirst);

            curElement.setPrev(first);
            first.setNext(curElement);



        }
        else{
            int size=cache.size();
            if(size==capacity){
                removeElement();

            }
            Element curFirst=first.getNext();
            Element curEle=new Element(key,value);
            first.setNext(curEle);
            curFirst.setPrev(curEle);
            curEle.setNext(curFirst);
            curEle.setPrev(first);
            cache.put(key,curEle);

        }
    }
    private void removeElement(){
        
        Element prev=last.getPrev();
                prev.setNext(last);
                last.setPrev(prev);
        cache.remove(prev.getKey());
    }
    public int getElement(int key){
        if(!cache.containsKey(key)){
            return -1;
        }
        int value=cache.get(key).getValue();
        addElement(key,value);
        return value;
    }
}
