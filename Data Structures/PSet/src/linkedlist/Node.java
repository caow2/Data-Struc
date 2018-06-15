package linkedlist;

//basic node to represent linked list
public class Node<E> {
	Node<E> next;
	E value;
	
	public Node(E value) {
		this.value = value;
	}
	
	public void append(E value) {
		Node<E> temp = new Node<E>(value);
		next = temp;
	}
	
	public String toString() {
		return value.toString();
	}
	
	public static String toString(Node head) {
		StringBuilder sb = new StringBuilder("[");
		Node<Integer> temp = head;
		while(temp != null) {
			sb.append(temp);
			temp = temp.next;
			
			if(temp != null)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
