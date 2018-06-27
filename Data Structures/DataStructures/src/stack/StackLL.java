package stack;

//LinkedList based stack - no need to resize
//just push new elems to head of list and pop from there
//does not count for empty stack exceptions
public class StackLL<E> {
	private class Node<E> {
		Node<E> prev;
		E value;
		Node<E> next;
		
		public Node(Node<E> prev, Node<E> next, E elem){
			this.prev = prev;
			this.value = elem;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private int size;
	
	public StackLL() {
		head = null;
		size = 0;
	}
	
	public void push(E item) {
		Node<E> listItem = new Node<E>(null, head, item);
		head = listItem;
		size++;
	}
	
	public E pop() {
		Node<E> listItem = head;
		head = head.next;
		size--;
		return listItem.value;
	}
	
	public E peek() {
		return head.value;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> temp = head;
		while(temp != null) {
			sb.append(temp.value.toString());
			if(temp.next != null)
				sb.append(",");
			temp = temp.next;
		}
		sb.append("]");
		return sb.toString();
	}
}
