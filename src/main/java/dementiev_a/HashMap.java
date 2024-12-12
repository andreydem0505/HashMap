package dementiev_a;

import java.util.*;

public class HashMap<K, V> implements Map<K, V>, Iterable<Entry<K, V>> {
    private MapEntry<K, V>[] table;
    private int size;
    private final int MAX_BUCKET_SIZE = 10;

    public HashMap(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        table = new MapEntry[capacity];
        size = 0;
    }

    public HashMap() {
        this(64);
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public boolean containsKey(Object k) {
        MapEntry<K, V> entry = table[Math.abs(k.hashCode()) % table.length];
        while (entry != null) {
            if (entry.getKey().equals(k)) return true;
            entry = entry.getNext();
        }
        return false;
    }

    @Override
    public boolean containsValue(Object v) {
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                if (entry.getValue() == v || entry.getValue().equals(v)) return true;
                entry = entry.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(Object k) {
        MapEntry<K, V> entry = table[Math.abs(k.hashCode()) % table.length];
        while (entry != null) {
            if (entry.getKey().equals(k)) return entry.getValue();
            entry = entry.getNext();
        }
        return null;
    }

    @Override
    public V getOrDefault(Object k, V defaultValue) {
        V val = get(k);
        return val == null ? defaultValue : val;
    }

    @Override
    public V put(K k, V v) {
        int index = Math.abs(k.hashCode()) % table.length;
        MapEntry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new MapEntry<>(k, v);
            size++;
            return null;
        }
        int counter = 1;
        while (true) {
            if (entry.getKey().equals(k)) {
                V lastValue = entry.getValue();
                entry.setValue(v);
                return lastValue;
            }
            if (entry.getNext() == null) break;
            entry = entry.getNext();
            counter++;
        }
        entry.setNext(new MapEntry<>(k, v));
        counter++;
        size++;
        if (counter > MAX_BUCKET_SIZE) {
            HashMap<K, V> newMap = new HashMap<>(table.length * 2);
            newMap.putAll(this);
            table = new MapEntry[table.length * 2];
            clear();
            putAll(newMap);
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                set.add(entry.getKey());
                entry = entry.getNext();
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new LinkedList<>();
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                list.add(entry.getValue());
                entry = entry.getNext();
            }
        }
        return list;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    @Override
    public V remove(Object k) {
        int index = Math.abs(k.hashCode()) % table.length;
        MapEntry<K, V> entry = table[index];
        if (entry == null) return null;

        if (entry.getKey().equals(k)) {
            table[index] = entry.getNext();
            entry.setNext(null);
            size--;
            return entry.getValue();
        }

        MapEntry<K, V> last = entry;
        entry = entry.getNext();

        while (entry != null) {
            if (entry.getKey().equals(k)) {
                last.setNext(entry.getNext());
                entry.setNext(null);
                size--;
                return entry.getValue();
            }
            last = entry;
            entry = entry.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator<>(table);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashMap<?, ?> hashMap)) return false;
        Set<K> keys = keySet();
        if (!keys.equals(hashMap.keySet())) return false;
        for (K key : keys) {
            if (!get(key).equals(hashMap.get(key))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(table));
    }
}
