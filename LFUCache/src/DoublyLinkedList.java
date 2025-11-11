public class DoublyLinkedList {
    Node head;
    Node tail;
    int size = 0;

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        int freq;


        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public Node add( int key, int value){
        Node node = new Node(key,value);
        addNode(node);
        return node;
    }

    public void addNode(Node node){
        node.prev = null;
        node.next = null;
        if( head == null ){
            head = tail = node;
        }
        else{
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public Node removeTail(){
        if( tail == null ) return null;
        Node node = tail;
        remove(node);
        return node;
    }

    public void remove( Node node){
        if( node.next != null ) node.next.prev = node.prev;
        else tail = node.prev;

        if( node.prev != null ) node.prev.next = node.next;
        else head = node.next;

        node.next = null;
        node.prev = null;
        size--;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
