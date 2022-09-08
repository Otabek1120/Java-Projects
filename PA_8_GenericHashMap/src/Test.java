import java.util.HashMap;
public class Test  {
    public static void main(String[] args) {
        MyHashMap x = new MyHashMap();
        x.put("One", 1);
        x.put("Two", 2);
        x.put("One", 1);
        x.put("Three", 3);
        x.printTable();
    }
}
