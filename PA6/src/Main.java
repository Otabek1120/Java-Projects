// Stack implementation in Java

public class Main {
    public static void main(String[] args) {
        ListQueue x = new ListQueue();
        x.enqueue(1); x.enqueue(2); x.enqueue(3); x.enqueue(4); x.enqueue(5);
        
        
        
        
        ListQueue y = new ListQueue();
        y.enqueue(1); y.enqueue(2); y.enqueue(3); y.enqueue(4); 
        System.out.println(x.equals(y));
    }
}