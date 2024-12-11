package dementiev_a;

import java.util.Iterator;

public class HashMapIterator<K, V> implements Iterator<Entry<K, V>> {
    private final MapEntry<K, V>[] table;
    private int index;
    private MapEntry<K, V> current;
    
    public HashMapIterator(MapEntry<K, V>[] table) {
        this.table = table;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                index = i;
                current = table[i];
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Entry<K, V> next() {
        MapEntry<K, V> last = current;
        if (current.getNext() != null) {
            current = current.getNext();
        } else {
            do index++;
            while (index < table.length - 1 && table[index] == null);
            if (index == table.length)
                current = null;
            else
                current = table[index];
        }
        return last;
    }
}
