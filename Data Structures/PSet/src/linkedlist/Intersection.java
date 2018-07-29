package linkedlist;

//Given 2 SLL, determine if the two lists intersect
//SLL of Integers, can be of diff lengths, can be null
//If no intersecting node - return null
// 5->4->9->0->1->3->4
// 3->7->2->1->3->4
public class Intersection {
    public static void main(String[] args) {
        Node<Integer> A = new Node<Integer>(5), tempA = A;
        tempA.append(4);
        tempA = tempA.next;
        tempA.append(9);
        tempA = tempA.next;
        tempA.append(0);
        tempA = tempA.next;
        tempA.append(1);
        tempA = tempA.next;
        tempA.append(3);
        tempA = tempA.next;
        tempA.append(4);
        tempA = tempA.next;

        Node<Integer> B = new Node<Integer>(2), tempB = B;
        tempB.append(1);
        tempB = tempB.next;
        tempB.append(3);
        tempB = tempB.next;
        tempB.append(4);
        tempB = tempB.next;

        System.out.println(intersection(A, B));

        Node<Integer> C = new Node<Integer>(null);
        System.out.println(intersection(B, C));
    }

    /*
     * BCR is O(A + B) - might have to check both lists in case the intersecting node is the last one
     * or there is no intersecting node
     */

    public static Node<Integer> intersection(Node<Integer> A, Node<Integer> B) {
        if (A == null)
            return B;
        if (B == null)
            return A;

        //find length of both
        int lenA = 0, lenB = 0;
        Node<Integer> tempA = A, tempB = B;
        while (tempA != null) {
            tempA = tempA.next;
            lenA++;
        }
        while (tempB != null) {
            tempB = tempB.next;
            lenB++;
        }

        tempA = A;
        tempB = B;
        //let A be the longer list
        if (lenA < lenB) {
            tempA = B;
            tempB = A;
        }

        //traverse longer list until same start point as the shorter list
        int count = Math.abs(lenA - lenB);
        for (int pos = 0; pos < count; pos++) {
            tempA = tempA.next;
        }

        //step thru both at same time
        boolean same = false;
        Node<Integer> inter = null;
        while (tempA != null) {
            if (tempA.value.equals(tempB.value)) {
                if (!same) {
                    same = true;
                    inter = tempA;
                }
            } else if (same) {
                same = false;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return (same ? inter : null);
    }

}
