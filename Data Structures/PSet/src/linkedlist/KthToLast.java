package linkedlist;

//Find kth to last element of singly linked list
public class KthToLast {
    public static int counter = 0;

    public static void main(String[] args) {
        Node<String> head = new Node<String>("First");
        Node<String> temp = head;
        temp.next = new Node<String>("Last - 4");
        temp = temp.next;
        temp.next = new Node<String>("Last - 3 ");
        temp = temp.next;
        temp.next = new Node<String>("Last - 2");
        temp = temp.next;
        temp.next = new Node<String>("Last - 1");
        temp = temp.next;
        temp.next = new Node<String>("Last");
        temp = temp.next;

        System.out.println(kthToLast(head, 2));
        System.out.println(kthToLastR(head, 2));
    }
	
	/*
	 	BCR is O(N) because need to traverse list at least once -> don't have reference to tail of list
	 	
	 	Brute Force -> Get length of list by traversing once. Calculate index of kth to last element
	 	(length - 1 - k) and iterate thru list again. O(2N) ~ O(N) Time, O(1) Space
	 	
	 	Recursive Approach - recurse until tail, then propagate +1 back up the runtime stack.
	 	Check the current sum from propagating each time before you propagate 1.
	 	If sum is k, return the current element.
	 	Assume k is within bounds - positive and less than length of entire linked list
	 	O(N) Time, O(N) Space
	 */

    //Brute Force
    public static String kthToLast(Node<String> head, int k) {
        if (head == null)
            return "";

        int size = 0;
        Node<String> temp = head;

        while (temp != null) {
            size++;
            temp = temp.next;
        }

        temp = head;
        for (int i = 0; i < size - 1 - k; i++) {
            temp = temp.next;
        }

        return temp.value;
    }

    //Recursive
    public static String kthToLastR(Node<String> head, int k) {
        if (head == null || head.next == null)
            return "";

        String value = kthToLastR(head.next, k);
        counter++;
        //recurse to tail
        if (counter == k)
            value += head.value;

        return value;

    }

}
