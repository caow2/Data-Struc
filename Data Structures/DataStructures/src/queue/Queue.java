package queue;

public abstract class Queue<E> {
	protected int size = 0;
	
	public abstract void push(E item);
	public abstract E peek();
	public abstract E pop();
	public abstract String toString();
	public boolean isEmpty() {
		return size == 0;
	}
}
