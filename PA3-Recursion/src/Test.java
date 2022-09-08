



import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
		Queue<Integer> q2 = new LinkedList<Integer>();
		q2.add(34); q2.add(15); q2.add(0);
		doubleElements(q2);
		System.out.println(q2);
    }

    public static void doubleElements(Queue<Integer> q) {
		int queueSize = q.size();
		douIntegersHelper(q, queueSize);
	}

	public static Queue<Integer> douIntegersHelper(Queue<Integer> queue, int queueSize) {
		if (queueSize == 0) {
			return queue;
		} else {
			int firtElem = queue.remove();
			queue.add(firtElem * 2);
			return douIntegersHelper(queue, queueSize - 1);
		}
	}
}