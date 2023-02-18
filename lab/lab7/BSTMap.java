import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;

    private class Node {
        K key;
        V value;
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    public BSTMap() {
        clear();
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    
 
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, Node node) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getHelper(key, node.left);
        } else if (cmp > 0) {
            return getHelper(key, node.right);
        } else {
            return node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    private Node putHelper(K key, V value, Node node) {
        if (node == null) {
            node = new Node(key, value);
            return node;
        }
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putHelper(key, value, node.left);
        } else if (cmp > 0) {
            node.right = putHelper(key, value, node.right);
        } else {
            node.value = value;
        }
        return node;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
