package LRU;

import LRU.DoublyLinkedList.*;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    private Map<Integer, Node> cache;
    private DoublyLinkedList lru;
    private final int capacity;

    public LRU(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        lru = new DoublyLinkedList();
    }

    public void put( int key, int value ){
        if ( capacity == 0 )
            return;

        Node node = cache.get(key);

        if(node == null){
            node = new Node(key,value);
            lru.addToHead( node );
            cache.put( key, node );
            evict();
        } else {
            node.val = value;
            lru.removeNode(node);
            lru.addToHead(node);
        }
    }

    public Integer get( int key ){
        Node result = cache.get(key);
        if( result == null )
            return null;
        lru.removeNode(result);
        lru.addToHead(result);
        return result.val;
    }

    public void evict(){
        if( cache.size() > capacity ){
            Node removed  = lru.removeTail();
            if( removed != null )
                cache.remove(removed.key);
        }
    }
}
