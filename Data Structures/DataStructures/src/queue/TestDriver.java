package queue;

public class TestDriver {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedListQueue<Integer>();
        q.push(1);
        q.push(2);
        System.out.println(q);
        System.out.println(q.pop());
        System.out.println(q.peek());
        System.out.println(q.pop());
        System.out.println(q.pop()); //exception here - empty queue
    }
}
