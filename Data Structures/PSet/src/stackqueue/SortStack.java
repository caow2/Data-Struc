package stackqueue;
import java.util.Random;
import java.util.Stack;
//Write a program to sort a stack s.t. smallest items are on top
//Can use temporary stack, but cannot use additional data strucs like array
public class SortStack {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			s.push(r.nextInt(50));
		}
		System.out.println(s);
		sortStack(s);
		System.out.println(s);
		int size = s.size();
		for(int i = 0; i < size; i++) {
			System.out.println(s.pop());
		}
	}
	/*
	 * With an array, you could pop all the elems of the stack and throw them in an array
	 * then sort the array -> O(nlogn) time with O(n) space
	 * 
	 * Considering a approach with 2 stacks, you could pop each element off the stack, push them onto another stack
	 * For each element taken out of the first stack, make sure it is sorted properly into the second stack such that
	 * the largest elements are on top - pop elements and push them onto other stack until you find a value that is smaller
	 * than the current item, push it onto the 2nd stack, then push back the elements larger than it
	 * O(n^2) time and O(n) space. 
	 * 
	 * The n^2 time stems from that for elems i=0 ... n, at each step there may be 0, 1, 2, ..., n-1 comparisons/shifts
	 */
	
	public static void sortStack(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<Integer>();
		int size = stack.size();
		for(int i = 0; i < size; i++) {
			Integer item = stack.pop();
			
			int counter = 0;
			while(!temp.isEmpty() && temp.peek() > item) {
				stack.push(temp.pop());
				counter++;
			}
			
			temp.push(item);
			
			while(counter > 0) {
				temp.push(stack.pop());
				counter--;
			}
		}
		
		if(!stack.isEmpty())
			System.out.println("Error");

		
		while(! temp.isEmpty()) {
			stack.push(temp.pop());
		}
	}
	
	
}
