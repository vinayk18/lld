import java.util.HashMap;

public class LRUCache {

    private DoublyLinkedList cache = new DoublyLinkedList();
    private HashMap<Integer,Node> map = new HashMap<>();
    private final int CAPACTITY;
    int size;


    LRUCache( int capacity ){
        this.CAPACTITY = capacity;
        size = 0;
    }

    public void put( int val ){
        if (map.containsKey(val)) {
            Node existing = map.get(val);
            cache.removeNode(existing);
            cache.addNode(existing);
            return;
        }

        if( !map.containsKey(val)){
            Node node = new Node(val);
            cache.addNode(node);
            map.put(val,node );
            size+=1;
        }
        if( size > CAPACTITY ){
            Node last = cache.getTail();
            if (last != null) {
                cache.removeNode(last);
                map.remove(last.data);
                size--;
            }
        }
    }

    public Node get( int val ){
        if(!map.containsKey(val)) return null;
        Node result = map.get(val);
        cache.removeNode(result);
        cache.addNode(result);
        return result;
    }

    public void print(){
        Node cur = cache.getHead();
        while( cur != null){
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }
}
