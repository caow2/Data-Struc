package stackqueue;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;

//Implement a data struc SetOfStacks that starts a new stack when previous stack exceeds some threshold
public class StackOfPlates {

    public static void main(String[] args) {
        StackSet<Integer> s = new StackSet<Integer>(3);
        for (int i = 0; i < 15; i++) {
            s.push(i);
        }
        System.out.println(s);
        System.out.println("Pop Test");
        for (int i = 0; i < 4; i++) {
            System.out.println(s.pop());
        }
        System.out.println(s);
        System.out.println("Pop At Test");
        for (int i = 0; i < 4; i++) {
            System.out.println(s.popAt(1));
            System.out.println(s);
        }

    }

    /*
     * Can have an List of Stacks - dynamically expanding w/ ArrayList or LinkedList
     * Adding N stacks will be O(N^2) due to resize, but add amortized will be O(1)
     * add/remove will be O(1) - just add and remove from the end of the list
     *
     * When the furthermost stack is empty - remove it
     *
     * Can also have a Stack of Stacks if we are only concerned with the topmost stack and don't need to
     * access the middle of the stack - once a stack elem hits capacity, push a new stack onto the stack
     * push/pop are O(1)
     *
     * Follow-up -> implement popAt which performs a pop operation at a sub stack - doable with Lists
     * If ArrayList -> will need to shift if stack is empty - Worst Case O(N)
     * If LinkedList -> will need to go thru LL each time to access the substack - Worst Case O(N)
     */

    public static class StackSet<E> {
        //ArrayList based approach
        ArrayList<Stack<E>> list = new ArrayList<Stack<E>>();
        int threshold; //stack threshold

        public StackSet(int threshold) {
            this.threshold = threshold;
            list.add(new Stack<E>());
        }

        public void push(E item) {
            Stack<E> stack = list.get(list.size() - 1);
            if (stack.size() >= threshold) {
                stack = new Stack<E>();
                list.add(stack);
            }
            stack.push(item);
        }

        public E pop() {
            //assume that when a stack is empty, it will be removed from the list
            Stack<E> stack = list.get(list.size() - 1);
            E item = stack.pop();
            if (stack.isEmpty())
                list.remove(list.size() - 1);
            return item;
        }

        public boolean isEmpty() {
            return list.size() == 0; //assumes that when a stack is empty it will be removed from the list
        }

        public E peek() {
            return list.get(list.size() - 1).peek();
        }

        //Follow up question - pop from a specific index - assume valid input for index
        //popAt may be O(N) because when a stack is empty, it will be removed and the rest of the stacks after it
        //will be shifted by one index due to ArrayList remove
        public E popAt(int index) {
            try {
                Stack<E> stack = list.get(index);
                E item = stack.pop();
                if (stack.isEmpty())
                    list.remove(index);
                return item;
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append("[");
                Stack<E> s = list.get(i);
                Iterator<E> it = s.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().toString());
                    if (it.hasNext())
                        sb.append(",");
                }
                sb.append("]");
                if (i != list.size() - 1)
                    sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
