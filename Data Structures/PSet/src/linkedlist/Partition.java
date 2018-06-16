package linkedlist;

//Partition SLL around value x s.t. all nodes < x come before all nodes >= x
public class Partition {
	public static void main(String[] args) {
		Node<Integer> head = new Node<Integer>(3);
		Node<Integer> temp = head;
		temp.append(5);
		temp = temp.next;
		temp.append(8);
		temp = temp.next;
		temp.append(5);
		temp = temp.next;
		temp.append(10);
		temp = temp.next;
		temp.append(2);
		temp = temp.next;
		temp.append(1);
		temp = temp.next;
		
		System.out.println(Node.toString(head));
		Node<Integer> partitioned = partition(head, 5);
		System.out.println(Node.toString(partitioned));
	}
	
	public static Node<Integer> partition(Node<Integer> head, int pval) {
		if(head == null)
			return head;
		
		Node<Integer> temp = head.next, prev = head;
		while(temp != null) {
			if(temp.value < pval) {
				prev.next = temp.next;
				temp.next = head;
				head = temp;
				temp = prev.next;
			}
			else{
				prev = temp;
				temp = temp.next;
			}
		}
		return head;
	}
}
