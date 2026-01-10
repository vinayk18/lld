package LRU;

public class DoublyLinkedList {

    static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    Node head, tail;

    DoublyLinkedList(){
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public void addToHead( Node node ){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public Node removeTail(){
        if( head.next == tail )
            return null;

        Node result = tail.prev;

        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        result.prev = null;
        result.next = null;

        return result;
    }
}
