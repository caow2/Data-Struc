package linkedlist;

//Given 2 numbers represented by a SLL where each node contains a digit, add them.
//Digits stored in reverse order - i.e. 7->1->6 is 617
//
//Follow up - Suppose digits are in forward order
public class SumLists {
	public static void main(String[] args) {
		Node<Integer> headA = new Node<Integer>(1);
		headA.append(2);
		Node<Integer> headB = new Node<Integer>(0);
		headB.append(1);
		//21 + 10 = 31
		System.out.println(Node.toString(sumList(headA, headB)));
		//System.out.println(Node.toString(sumList(headB, headA)));
		
		headA = new Node<Integer>(1);
		headB = new Node<Integer>(9);
		System.out.println(Node.toString(sumList(headA, headB)));
		
		System.out.println(Node.toString(sumList(headA, null)));
		
		
		//Follow Up
		System.out.println("Testing Follow Up Question");
		Node<Integer> headC = new Node<Integer>(2);
		headC.append(1);
		Node<Integer> headD = new Node<Integer>(1);
		headD.append(0);
		System.out.println(Node.toString(sumListFollowUp(headC,headD)));
		
		Node<Integer> headE = new Node<Integer>(1);
		Node<Integer> headF = new Node<Integer>(9);
		System.out.println(Node.toString(sumListFollowUp(headE, headF)));
		
		Node<Integer> headG = new Node<Integer>(9);
		headG.append(5);
		Node<Integer> headH = new Node<Integer>(5);
		System.out.println(Node.toString(sumListFollowUp(headG, headH)));
		
		//System.out.println(Node.toString(sumListFollowUp(headG, null))); 
	}
	
	//O(A+B) Time, O(1) Additional Space
	public static Node<Integer> sumList(Node<Integer> A, Node<Integer> B) {
		//check length of each list
		int lenA = 0, lenB = 0;
		Node<Integer> tempA = A, tempB = B;
		while(tempA != null) {
			tempA = tempA.next;
			lenA++;
		}
		while(tempB != null) {
			tempB = tempB.next;
			lenB++;
		}
		
		tempA = A;
		tempB = B;
		if(lenA == 0)
			return B;
		else if (lenB == 0)
			return A;
		else if (lenA < lenB) {	//work with A as longer list
			tempA = B;
			tempB = A;
		}
		
		int carry = 0;
		//traverse thru longer string
		while(tempA != null) {
			tempA.value += carry;
			carry = 0;
			
			if(tempB != null) {
				tempA.value += tempB.value;
				tempB = tempB.next;
			}
			
			if(tempA.value > 9) {
				tempA.value %= 10;
				carry++;
			}
			
			//check last element case
			if(tempA.next == null && carry > 0) {
				tempA.append(carry);
				carry = 0;
			}
			tempA = tempA.next;
		}
		return A;
	}
	
	//Still O(A+B) for Best conceivable runtime, O(A+B) space b/c of recursion
	//Follow up question
	public static Node<Integer> sumListFollowUp(Node<Integer> A, Node<Integer> B) {
		//reverse the LLs then work with already made solution
		if(A == null)
			return B;
		if(B == null)
			return A;
		A = reverseLL(A, A);
		B = reverseLL(B, B);
		Node<Integer> result = sumList(A,B);
		return reverseLL(result, result);
	}

	//returns new head
	public static Node<Integer> reverseLL(Node<Integer> head, Node<Integer> node) {
		if(node == null || node.next == null) //reached end of list - return the last node/new head
			return node;
		Node<Integer> newHead = reverseLL(head, node.next);
		node.next.next = node;
		if(node.equals(head))
			head.next = null;
		return newHead;
	}
}
