package stack;

public class TestDriver {
    public static void main(String[] args) {
        StackLL<Integer> stack = new StackLL<Integer>();
        //Stack<Integer> stack = new Stack<Integer>();
        System.out.println(stack.isEmpty());
        stack.push(1);
        System.out.println(stack);
        stack.push(2);
        System.out.println(stack);
        stack.push(3);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop()); //error - stack empty
    }
}
