package HashMap2;

public class Main {
    public static void main(String []args){
        MyMap<Integer,String> map=new MyMap<Integer,String>();
        for(int i=1;i<=19;i++){
            map.put(i,"sangam_"+i);
        }
        for(int i=1;i<=19;i++){
        String s=map.get(i);
        System.out.println(i+" " +s);
        }
    }
}
