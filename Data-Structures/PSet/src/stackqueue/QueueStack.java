package stackqueue;

import java.util.Stack;

//Implement a Queue using 2 stacks
public class QueueStack {
    //Since a Queue is FIFO and maintains the order of elements,
    //2 stacks should be able to imitate a queue.
    //A single stack reverses the order of elements, and if you push the elements popped from a stack into another stack,
    //The original ordering can be obtained
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < 10; i++) {
            q.add(i);
        }

        while (!q.isEmpty()) {
            System.out.println(q.remove());
        }

    }

    public static class Queue<E> {
        Stack<E> first = new Stack<E>(), second = new Stack<E>();

        public void add(E item) {
            first.push(item);
            second.push(first.pop());
        }

        public E remove() {
            return second.pop();
        }

        public boolean isEmpty() {
            return second.isEmpty();
        }

        public E peek() {
            return second.peek();
        }
    }
}
