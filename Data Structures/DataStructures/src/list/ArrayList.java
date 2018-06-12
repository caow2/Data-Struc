package list;

public class ArrayList<E> {
	protected E[] list;
	private int size = 0;
	
	public ArrayList(int size) {
		list = (E[]) new Object[size];
	}
	
	public ArrayList() {
		this(10);
	}
	
	public E get(int index) {
		//assume valid index
		return list[index];
	}
	public E remove(int index) {
		E elem = list[index];
		for(int pos = index; pos < list.length - 1; pos++) {
			//shift to the left
			list[pos] = list[pos + 1];
		}
		size--;
		return elem;
	}
	
	public int indexOf(Object obj) {
		try {
			E elem = (E) obj;
			
			for(int pos = 0; pos < size; pos++) {
				if(obj.equals(list[pos]))
					return pos;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public boolean contains(Object obj) {
		if(indexOf(obj) > -1)
			return true;
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public void add(E elem, int index) {
		if(size == list.length)
			resize();
		
		//shift all the elements from the index to the right by 1
		for(int pos = size; pos > index; pos--) {
			list[pos] = list[pos - 1];
		}
		
		list[index] = elem;
		size++;
	}
	
	public void add(E elem) {
		add(elem, size);
	}
	
	//Assume already know need to resize
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] newList = (E[]) new Object[size() * 2];
		
		for(int pos = 0; pos < list.length; pos++) {
			newList[pos] = list[pos];
		}
		
		list = newList;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(int pos = 0; pos < size; pos++) {
			String str = list[pos].toString();
			if(pos == size - 1) //last element
				sb.append(str);
			else //not last element
				sb.append(str + ", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
