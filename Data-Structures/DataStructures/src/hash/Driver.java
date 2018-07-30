package hash;

public class Driver {
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<String, Integer>();

        table.put("A", 1);
        System.out.println(table.size());
        table.put("B", 2);
        System.out.println(table.size());
        table.put("Z", 26);
        System.out.println(table.size());
        table.remove("C");
        System.out.println(table.size());
        table.remove("B");
        System.out.println(table.size());
        table.put("Apple", 123123);
        System.out.println(table.get("A"));
        System.out.println(table.toString());
    }
}
