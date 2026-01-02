import java.util.List;

class Node{
    int data;
    Node prev;
    Node next;
    Node( int val ){
        this.data = val;
        prev = null;
        next = null;
    }
}

public class DoublyLinkedList {
    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Node addNode(Node newNode){
        //Node newNode = new Node( val );
        if( head == null ) {
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        return newNode;
    }

    public Node removeNode(Node node){
        if (node == null) return null;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        if (head == null) tail = null;
        if (tail == null) head = null;

        node.next = null;
        node.prev = null;

        return node;
    }
}
