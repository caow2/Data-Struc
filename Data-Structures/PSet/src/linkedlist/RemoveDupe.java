package linkedlist;

import java.util.HashSet;

//Remove all duplicates from unsorted linked list
public class RemoveDupe {
    public static void main(String[] args) {
        // [0, 1, 2, 0, 2] -> [0, 1, 2]
        Node<Integer> head = new Node<Integer>(0);
        Node<Integer> temp = head;
        temp.next = new Node<Integer>(1);
        temp = temp.next;
        temp.next = new Node<Integer>(2);
        temp = temp.next;
        temp.next = new Node<Integer>(0);
        temp = temp.next;
        temp.next = new Node<Integer>(2);
        temp = temp.next;

        System.out.println(Node.toString(removeDupe(head)));
    }
	
	/*
 	BCR is O(N) since need to check entire list from head to tail
 	
 	Keep a reference to the previous Node for removal
 	
 	Brute force - for each node, traverse thru the list from the next node onward to the tail and remove all dupes
 	O(N^2) Time, O(1) Space
 	
 	Cache Seen Nodes - as we traverse thru the list, add the values we've already seen to a HashSet
 	If in set, remove from the list. Otherwise, add to the set.
 	O(N) Time, O(N) Space
 	
 	Solution w/ no temp buffers aside from anything that takes O(1) space - sort the list based on values
 	Traverse thru the list and at each new element, have a loop that goes to the next new element and set
 	the elem.next to the next new element until you reach the end of the list
 	O(NlogN + N) ~ O(NlogN) Time, O(1) Space
	*/

    //Cached Approach
    public static Node<Integer> removeDupe(Node<Integer> head) {
        if (head == null)
            return head;

        HashSet<Integer> set = new HashSet<Integer>();
        Node<Integer> temp = head, prev = head;
        while (temp != null) {
            if (set.contains(temp.value))
                prev.next = temp.next;
            else {
                set.add(temp.value);
                prev = temp;
            }
            temp = temp.next;
        }
        return head;
    }
}
