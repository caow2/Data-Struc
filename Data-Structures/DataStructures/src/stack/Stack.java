package stack;

//Array based stack - push to and pop from end of list to prevent constant shifting
//does not account for stack empty exception
public class Stack<E> {
    E[] list;
    int size;

    public Stack() {
        list = (E[]) new Object[10];
        size = 0;
    }

    public void resize() {
        //use when size == list.length or size < 1/4 of list capacity
        int cap = list.length;
        if (size < list.length / 4)
            cap /= 2;
        else
            cap *= 2; //full list
        E[] newList = (E[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    public E pop() {
        E item = list[size - 1];
        size--;
        if (size < list.length / 4)
            resize();
        return item;
    }

    public void push(E item) {
        if (size == list.length)
            resize();
        list[size++] = item;
    }

    public E peek() {
        return list[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = size - 1; i >= 0; i--) {
            sb.append(list[i].toString());
            if (i > 0)
                sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }
}
