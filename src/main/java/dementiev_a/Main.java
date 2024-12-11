package dementiev_a;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println(map.size());
        for (Entry<String, Integer> entry : map) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        map.remove("B");
        System.out.println("remove B");
        System.out.println(map.size());
        for (Entry<String, Integer> entry : map) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}