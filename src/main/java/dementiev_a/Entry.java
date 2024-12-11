package dementiev_a;

public interface Entry<K, V> {
    boolean equals(Object obj);
    K getKey();
    V getValue();
    V setValue(V v);
    int hashCode();
}
