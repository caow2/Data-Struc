package linkedlist;

import java.util.HashSet;
import java.util.Set;

//Given a circular linked list, implement an algo to return the node at beginning of loop
//the loop node may be anywhere in the linked list
//if no loop - return null
public class LoopDetection {
	public static void main(String[] args) {
		Node<Character> A = new Node<Character>('A'), tempA = A;
		tempA.append('B');
		tempA = tempA.next;
		Node<Character> loopNode = new Node<Character>('C');
		tempA.next = loopNode;
		tempA = tempA.next;
		tempA.append('D');
		tempA = tempA.next;
		tempA.append('E');
		tempA = tempA.next;
		tempA.append('C');
		tempA = tempA.next;
		
		System.out.println(loopDetect(A)); //should be null;
		
		tempA.next = loopNode;
		System.out.println(loopDetect(A)); //should be C;
	}
	
	public static Node<Character> loopDetect(Node<Character> head) {
		//use a HashSet to track nodes that have been seen already
		if(head == null)
			return head;
		
		Set<Node<Character>> set = new HashSet<Node<Character>>();
		Node<Character> temp = head;
		while(temp != null) {
			if(set.contains(temp))
				return temp;
			else
				set.add(temp);
			temp = temp.next;
		}
		return null;
	}
}
