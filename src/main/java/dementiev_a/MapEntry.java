package dementiev_a;

import java.util.Objects;

public class MapEntry<K, V> implements Entry<K, V> {
    private final K key;
    private V value;
    private MapEntry<K, V> next;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        V lastValue = value;
        value = v;
        return lastValue;
    }

    public MapEntry<K, V> getNext() {
        return next;
    }

    public void setNext(MapEntry<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapEntry<?, ?> mapEntry)) return false;
        return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value) && Objects.equals(next, mapEntry.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, next);
    }
}
