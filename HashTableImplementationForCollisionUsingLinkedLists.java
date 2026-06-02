public class HashTable<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int capacity;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        buckets = (Node<K, V>[]) new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {

        int index = hash(key);

        Node<K, V> current = buckets[index];

        while (current != null) {

            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);

        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public V get(K key) {

        int index = hash(key);

        Node<K, V> current = buckets[index];

        while (current != null) {

            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public void remove(K key) {

        int index = hash(key);

        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {

            if (current.key.equals(key)) {

                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                return;
            }

            prev = current;
            current = current.next;
        }
    }
}

// | Operation | Average | Worst |
// | --------- | ------- | ----- |
// | Put       | O(1)    | O(n)  |
// | Get       | O(1)    | O(n)  |
// | Remove    | O(1)    | O(n)  |
