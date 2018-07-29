package stackqueue;

import java.util.Stack;

//Design a stack that supports a min function to return the minimum element 
//They should all operate in O(1) time
public class StackMin {
    /*
     * Need a way to keep track of the minimum element with respect to each element that is pushed onto the stack/order of insertion
     * The idea is to have another data structure that keeps track of this - an array, another stack, etc.
     *
     * push and pop are O(1)
     * min would also be O(1) since you have another Stack just keeping track of the minimum elements at each step
     * Every push and pop requires a corresponding action on the minStack.
     */

    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(2);
        m.push(1);
        m.push(3);
        m.push(0);
        m.push(4);
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());    //Empty Stack Exception
    }

    //should support push, pop, peek, min, isEmpty
    static class MinStack {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minstack = new Stack<Integer>();

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int i) {
            stack.push(i);
            if (minstack.isEmpty() || i < minstack.peek())
                minstack.push(i);
            else
                minstack.push(minstack.peek());
        }

        public int pop() {
            minstack.pop();
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public int min() {
            return minstack.peek();
        }
    }

}
