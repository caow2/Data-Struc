package queue;

public class LinkedListQueue<E> extends Queue<E>{
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
	private Node<E> tail;
	
	public LinkedListQueue() {
		head = new Node<E>(null, null, null);
		tail = new Node<E>(null, null, null);
		head.next = tail;
		tail.prev = head;
	}
	
	@Override
	public void push(E item) {
		Node<E> elem = new Node<E>(head, head.next, item);
		elem.next.prev = elem;
		head.next = elem;
		size++;
	}

	@Override
	public E peek() {
		return tail.prev.value;
	}

	@Override
	public E pop() {
		Node<E> temp = tail.prev;
		tail.prev = temp.prev;
		temp.prev.next = tail;
		size--;
		return temp.value;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> temp = tail.prev;
		while(temp != head) {
			sb.append(temp.value.toString());
			if(temp.prev != head)
				sb.append(",");
			temp = temp.prev;
		}
		sb.append("]");
		return sb.toString();
	}
	
}
