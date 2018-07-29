package linkedlist;

//Delete a node in middle (between first and last node of a singly linked list), given access to only the head of the list
public class DeleteMiddleNode {
    public static void main(String[] args) {
        Node<String> head = new Node<String>("a");
        Node<String> temp = head;
        temp.next = new Node<String>("b");
        temp = temp.next;
        temp.next = new Node<String>("c");
        temp = temp.next;
        temp.next = new Node<String>("d");
        temp = temp.next;
        temp.next = new Node<String>("e");
        temp = temp.next;
        temp.next = new Node<String>("f");
        temp = temp.next;
        deleteMiddle(head);
        deleteMiddle(head);

        System.out.println(Node.toString(head));
        System.out.println(Node.toString(null));

        Node<String> test = new Node<String>("1");
        test.next = new Node<String>("2");

        deleteMiddle(test);
        System.out.print(Node.toString(test));
    }
	
	/*
	 	BCR O(N) if we want to delete near middle, O(1) if it just has to be somewhere in between first and last node
	 	(Theoretically we can just take the first element after first and check if its the tail node)
	 	
	 	Assume Delete near middle
	 	May get null input for head
	 	Singly Linked List
	 	
	 	Brute Force - Go through list once, get the length. If length > 2, traverse through list and keep a counter of the
	 	position and the previous element. When reached position of length / 2, set the prev.next = curr.next
	 	O(N) time, O(1) space
	 	
	 	Two pointer/Runner Approach - Check if length > 2. Have two references, A and B, and a reference to the previous
	 	element. For every step A takes, B takes 2 steps. If B reaches the end of the list, set prev.next = A.next
	 	O(N) time, O(1) space. Slightly faster than BF.
	 */

    //2 pointer approach
    public static void deleteMiddle(Node<String> head) {
        if (head == null || head.next == null || head.next.next == null)
            return; //nothing to delete -> < 3 elements

        Node<String> A = head, B = head, prev = null;

        //traverse to end of list
        while (B != null && B.next != null) {
            prev = A;
            A = A.next;
            B = B.next.next;
        }

        prev.next = A.next;
    }
}
