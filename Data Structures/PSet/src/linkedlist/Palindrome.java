package linkedlist;

//Implement function to see if SLL is a palindrome - doesn't take empty string
public class Palindrome {
    public static void main(String[] args) {
        Node<Character> A = new Node<Character>('a');
        Node<Character> temp = A;
        temp.append('b');
        temp = temp.next;
        temp.append('c');
        temp = temp.next;
        temp.append('b');
        temp = temp.next;
        temp.append('a');
        System.out.println(palindrome(A));

        Node<Character> B = new Node<Character>(' ');
        System.out.println(palindrome(B));
    }

    //Best conceivable runtime is O(N) -> need to check whole LL
    //Brute force - get full length of list, check front and back of list and propagate down to middle
    //O(N^2) to keep checking the end of the list

    //could reverse the linked list - and check the two simultaneously
    //also possible to reverse in place
    public static boolean palindrome(Node<Character> head) {
        if (head == null)
            return false;

        //reverse only second half the list - reverse does reversing by reference so will modify original head
        Node<Character> A = head, B = head.next;
        int lenA = 1;
        while (B != null && B.next != null) {
            A = A.next;
            B = B.next.next;
            lenA++;
        }

        Node<Character> reversed = reverseLL(A.next, A.next);
        A.next = null; //second half of LL already modified
        Node<Character> temp = head;
        if (lenA % 2 == 1) // odd length - there is an extra character in the middle
            lenA -= 1;
        int counter = 0;
        while (counter < lenA) {
            if (!temp.value.equals(reversed.value)) //not the same
                return false;
            temp = temp.next;
            reversed = reversed.next;
            counter++;
        }
        return true;
    }

    public static Node<Character> reverseLL(Node<Character> head, Node<Character> node) {
        if (node == null || node.next == null) //reached end of the list
            return node;

        //traverse to end of the list
        Node<Character> newHead = reverseLL(head, node.next);

        node.next.next = node;

        if (node == head)
            node.next = null;

        return newHead;
    }
}
