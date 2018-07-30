package list;

public class TestDriver {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();

        list.add("Apple");
        list.add("Bpple");
        list.add("C");
        list.add("Insert", 3);
        list.removeLast();
        System.out.println(list);
    }
}
