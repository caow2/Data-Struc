package graph;

//Array based min heap
public class MinHeap {
    int[] heap;
    int size;

    public MinHeap() {
        heap = new int[10];
    }

    public void add(int val) {
        heap[size] = val;
        percUp(size);    //size same as index
        size++;
    }

    public void percUp(int index) {
        int parent = (index - 1) / 2;
        if (heap[parent] <= heap[index]) //Takes care of index = 0 b/c (0 - 1) / 2 is 0 -> heap[0] <= heap[0]
            return;
        swap(parent, index);
        percUp(parent);
    }

    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int getMin() {
        return heap[0];
    }

    public void resize() {
        int[] temp = new int[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    public int removeMin() {
        int min = heap[0];
        heap[0] = heap[--size];
        percDown(0);
        return min;
    }

    public void percDown(int index) {
        int left = (index * 2) + 1, right = (index * 2) + 2;
        if (left >= size) //no children
            return;
        //at least has left child
        int min = -1;
        if (right >= size && heap[left] < heap[index])// no right child
            min = left;
        else {
            min = (heap[left] > heap[right] ? right : left);
        }
        swap(index, min);
        percDown(min);

    }
}
