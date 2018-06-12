package list;

//Doubly Linked List
public class LinkedList<E> {
	//Node for LinkedLists
	private class Node<E> {
		Node prev;
		E value;
		Node next;
		
		public Node(Node prev, Node next, E elem){
			this.prev = prev;
			this.value = elem;
			this.next = next;
		}
	}
	
	int size = 0;
	Node<E> head = new Node(null, null, null);
	Node<E> tail = new Node(null, null, null);
	
	public LinkedList() {
		head.next = tail;
		tail.prev = head;
	}
	
	public void add(E elem) {
		Node<E> n = new Node<E>(tail.prev, tail, elem);
		
		//adjust the previous last element
		tail.prev.next = n;
		tail.prev = n;
		size++;
	}
	
	public E get(int index) {
		Node<E> temp = head.next;
		
		for(int pos = 0; pos < index; pos++) {
			temp = temp.next;
		}
		
		return temp.value;
	}
	
	public int indexOf(E elem) {
		Node<E> temp = head.next;
		
		for(int pos = 0; pos < size; pos++) {
			if(temp.value == elem)
				return pos;
			temp = temp.next;
		}
		
		return -1;
	}
	
	public void add(E elem, int index) {
		Node<E> n = new Node<E>(null, null, elem);
		
		Node<E> temp = head.next;
		for(int pos = 0; pos < index; pos++) {
			temp = temp.next;
		}
		
		//At correct index now -> n inserted where temp was
		n.prev = temp.prev;
		n.next = temp;
		temp.prev.next = n;
		temp.prev = n;
		
		size++;
	}
	
	public E removeFirst() {
		return remove(0);
	}
	
	public E removeLast() {
		if(tail.prev == head)
			return null; //no elems in list
		
		Node<E> temp = tail.prev;
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		
		return temp.value;
	}
	
	//look thru all nodes until found or reached tail
	public E remove(E elem) {
		Node<E> temp = head;
		E removed = null;
		while(temp.next != tail) {
			if(elem == temp.value) {
				removed = temp.value;		
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				size--;
			}
		}
		return removed;
	}
	
	public E remove(int index) {
		//assume valid index - index > -1 && index < size
		Node<E> temp = head.next;
		E removed = null;
		for(int pos = 0; pos < size; pos++) {
			if(pos == index) {
				removed = temp.value;
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				size--;
			}
			else
				temp = temp.next;
		}
		return removed;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> temp = head.next;
		for(int pos = 0; pos < size; pos++) {
			String str = temp.value.toString();
			if(pos == size - 1) //last element
				sb.append(str);
			else //not last element
				sb.append(str + ", ");
			temp = temp.next;
		}
		sb.append("]");
		return sb.toString();
	}
}
