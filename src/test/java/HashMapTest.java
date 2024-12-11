import dementiev_a.HashMap;
import dementiev_a.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class HashMapTest {
    @Test
    public void testNewlyCreatedIsEmpty() {
        Assertions.assertTrue(new HashMap<String, Integer>().isEmpty());
    }

    @Test
    public void testCreatingWithIncorrectArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new HashMap<String, Integer>(-10)
        );
    }

    @Test
    public void testCreatingWithNullArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new HashMap<String, Integer>(0)
        );
    }

    @Test
    public void testCreatingWithCorrectArgument() {
        Assertions.assertTrue(new HashMap<String, Integer>(10).isEmpty());
    }

    @Test
    public void testGettingNonExistent() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertNull(map.get("a"));
    }

    @Test
    public void testPuttingAndGetting() {
        Map<String, Integer> map = new HashMap<>();
        Integer result = map.put("a", 1);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(1, map.get("a"));
        Assertions.assertNull(result);
    }

    @Test
    public void testPuttingNullKey() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertThrows(NullPointerException.class, () -> map.put(null, 1));
    }

    @Test
    public void testGettingNullKey() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    public void testPuttingAndGettingMultipleElements() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        map.put("coffee", 2);
        map.put("tea", 3);
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(1, map.get("milk"));
        Assertions.assertEquals(2, map.get("coffee"));
        Assertions.assertEquals(3, map.get("tea"));
    }

    @Test
    public void testRewriting() {
        Map<String, Integer> map = new HashMap<>();
        Integer result = map.put("a", 1);
        Assertions.assertNull(result);
        result = map.put("a", 2);
        Assertions.assertEquals(1, result);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(2, map.get("a"));
    }

    @Test
    public void testRemovingOnlyElement() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("a", 2);
        int removed = map.remove("a");
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertNull(map.get("a"));
        Assertions.assertEquals(2, removed);
    }

    @Test
    public void testRemoving() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        int removed = map.remove("a");
        Assertions.assertEquals(3, map.size());
        Assertions.assertNull(map.get("a"));
        Assertions.assertEquals(1, removed);
        Assertions.assertEquals(2, map.get("b"));
        Assertions.assertEquals(3, map.get("c"));
        Assertions.assertEquals(4, map.get("d"));
    }

    @Test
    public void testRemovingNullKey() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertThrows(NullPointerException.class, () -> map.remove(null));
    }

    @Test
    public void testRemovingFromOneLinkedList() {
        Map<Integer, Integer> map = new HashMap<>(10);
        map.put(1, 1);
        map.put(11, 2);
        map.put(21, 3);
        int removed = map.remove(11);
        Assertions.assertEquals(2, removed);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(1, map.get(1));
        Assertions.assertEquals(3, map.get(21));
        removed = map.remove(1);
        Assertions.assertEquals(1, removed);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(3, map.get(21));
        removed = map.remove(21);
        Assertions.assertEquals(3, removed);
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    public void testRemovingNonExistent() {
        Map<String, Integer> map = new HashMap<>();
        Integer removed = map.remove("a");
        Assertions.assertNull(removed);
    }

    @Test
    public void testClearing() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        map.put("coffee", 2);
        map.put("tea", 3);
        map.clear();
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    public void testEquals() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("milk", 1);
        map1.put("coffee", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("milk", 1);
        map2.put("coffee", 2);
        Assertions.assertEquals(map1, map2);
    }

    @Test
    public void testUnequalsWithDifferentValues() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("milk", 1);
        map1.put("coffee", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("milk", 1);
        map2.put("coffee", 3);
        Assertions.assertNotEquals(map1, map2);
    }

    @Test
    public void testUnequalsWithDifferentKeys() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("milk", 1);
        map1.put("coffee", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("milk", 1);
        map2.put("tea", 2);
        Assertions.assertNotEquals(map1, map2);
    }

    @Test
    public void testUnequalsWithDifferentCapacity() {
        Map<String, Integer> map1 = new HashMap<>(10);
        map1.put("milk", 1);
        map1.put("coffee", 2);
        Map<String, Integer> map2 = new HashMap<>(20);
        map2.put("milk", 1);
        map2.put("coffee", 2);
        Assertions.assertNotEquals(map1, map2);
    }

    @Test
    public void testGetOrDefaultWithExistingKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        Assertions.assertEquals(1, map.getOrDefault("milk", 4));
    }

    @Test
    public void testGetOrDefaultWithNonExistingKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        Assertions.assertEquals(4, map.getOrDefault("coffee", 4));
    }

    @Test
    public void testContainsKeyWithExistingKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        Assertions.assertTrue(map.containsKey("milk"));
    }

    @Test
    public void testContainsKeyWithNonExistingKey() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertFalse(map.containsKey("milk"));
    }

    @Test
    public void testContainsKeyNull() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertThrows(NullPointerException.class, () -> map.containsKey(null));
    }

    @Test
    public void testContainsValueWithExistingValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", 1);
        Assertions.assertTrue(map.containsValue(1));
    }

    @Test
    public void testContainsValueWithNonExistingValue() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertFalse(map.containsValue(1));
    }

    @Test
    public void testContainsValueNull() {
        Map<String, Integer> map = new HashMap<>();
        map.put("milk", null);
        Assertions.assertTrue(map.containsValue(null));
    }

    @Test
    public void testKeySet() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertTrue(map.keySet().isEmpty());
        map.put("milk", 1);
        map.put("coffee", 2);
        map.put("coffee", 5);
        map.put("tea", 3);
        Assertions.assertEquals(Set.of("milk", "coffee", "tea"), map.keySet());
    }

    @Test
    public void testValues() {
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertTrue(map.values().isEmpty());
        map.put("milk", 1);
        map.put("coffee", 2);
        map.put("cola", 2);
        map.put("tea", 3);
        Assertions.assertEquals(List.of(1, 2, 2, 3), map.values().stream().sorted().toList());
    }

    @Test
    public void testPutAll() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("milk", 1);
        map1.put("coffee", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("milk", 5);
        map2.put("tea", 3);
        map1.putAll(map2);
        Assertions.assertEquals(3, map1.size());
        Assertions.assertEquals(5, map1.get("milk"));
        Assertions.assertEquals(2, map1.get("coffee"));
        Assertions.assertEquals(3, map1.get("tea"));
    }
}
