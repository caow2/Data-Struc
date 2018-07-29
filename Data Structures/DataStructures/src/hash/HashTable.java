package hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> {
    private class Entry<K, V> {
        K key;
        V val;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private ArrayList<LinkedList<Entry<K, V>>> list;
    private int size = 0, capacity;

    public HashTable(int capacity) {
        list = new ArrayList<LinkedList<Entry<K, V>>>(capacity);
        this.capacity = capacity;
        initList(list);
    }

    public HashTable() {
        this(10);
    }

    public void put(K key, V val) {
        Entry<K, V> entry = new Entry<K, V>(key, val);
        int index = hashToIndex(key.hashCode(), capacity);

        list.get(index).add(entry);
        size++;

        if (capacity > 10 && size > 2 * capacity) {
            resize(2 * capacity);
        }
    }

    public int hashToIndex(int hashCode, int listSize) {
        return Math.abs(hashCode) % listSize;
    }

    public V get(K key) {
        int index = hashToIndex(key.hashCode(), capacity);
        for (Entry<K, V> entry : list.get(index)) {
            if (entry.key.equals(key))
                return entry.val;
        }
        return null;    //not found
    }

    public void remove(K key) {
        int index = hashToIndex(key.hashCode(), capacity);
        Iterator<Entry<K, V>> it = list.get(index).iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (entry.key.equals(key)) {
                it.remove();
                size--;
            }
        }

        if (capacity > 10 && size < (1 / 2) * capacity)
            resize((1 / 2) * capacity);
    }

    //occurs when number of elements in hashtable is 2*capacity or less than 1/2 * capacity
    public void resize(int newCap) {
        ArrayList<LinkedList<Entry<K, V>>> temp = new ArrayList<LinkedList<Entry<K, V>>>(newCap);
        capacity = newCap;
        initList(temp);
        //move all elements
        for (LinkedList<Entry<K, V>> l : list) {
            for (Entry<K, V> e : l) {
                int index = hashToIndex(e.key.hashCode(), newCap);
                temp.get(index).add(e);
            }
        }
        list = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int counter = 0;
        for (int i = 0; i < capacity; i++) {
            if (list.get(i).isEmpty())
                continue;
            Iterator<Entry<K, V>> it = list.get(i).iterator();
            while (it.hasNext()) {
                Entry<K, V> e = it.next();
                //check if last elem
                sb.append("{ " + e.key + " : " + e.val + " }");

                if (it.hasNext())
                    sb.append(",");

                counter++;
            }

            if (counter < size)
                sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    public void initList(ArrayList<LinkedList<Entry<K, V>>> list) {
        for (int i = 0; i < capacity; i++) {
            list.add(new LinkedList<Entry<K, V>>());
        }
    }

    public int size() {
        return size;
    }
}
