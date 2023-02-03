/**
 * LinkedListDeque
 */
public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    /* Invariants
     * The first node (if exists) is at sentinel.next
     * The last node (if exists) is at sentinel.prev
     * first.prev and last.next all point to sentinel
     */
    private Node sentinel;
    private int size;
    
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        Node first = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }
    public void addLast(T item) {
        Node last = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node curNode = sentinel.next;
        while (curNode != sentinel) {
            System.out.print(curNode.item);
            System.out.print(" ");
            curNode = curNode.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        Node victim = sentinel.next;
        sentinel.next = victim.next;
        sentinel.next.prev = sentinel;
        size--;
        return victim.item;
    }
    public T removeLast() {
        Node victim = sentinel.prev;
        sentinel.prev = victim.prev;
        sentinel.prev.next = sentinel;
        size--;
        return victim.item;
    }
    public T get(int index) {
        Node curNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.item;
    }
    private Node getRecursiveHelper(int index, Node node) {
        if (index == 0) {
            return node;
        }
        return getRecursiveHelper(index - 1, node.next);
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next).item;
    }
}