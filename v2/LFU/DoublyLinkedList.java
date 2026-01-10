package LFU;

public class DoublyLinkedList {

    Node head, tail;
    int size = 0;

    DoublyLinkedList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    Node removeTail() {
        if (size == 0) return null;
        Node node = tail.prev;
        remove(node);
        return node;
    }

    boolean isEmpty() {
        return size == 0;
    }
}