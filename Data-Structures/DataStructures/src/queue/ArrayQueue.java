package queue;

public class ArrayQueue<E> extends Queue<E> {
    E[] list;

    public ArrayQueue() {
        list = (E[]) new Object[10];
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

    @Override
    public void push(E item) {
        if (list.length == size)
            resize();
        list[size++] = item;
        size++;
    }

    @Override
    public E peek() {
        return list[size--];
    }

    @Override
    public E pop() {
        E item = list[0];
        size--;
        for (int i = 1; i < size; i++) {
            list[i - 1] = list[i];
        }
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder('[');
        for (int i = 0; i < size; i++) {
            sb.append(list[i]);
            if (i < size - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

}
