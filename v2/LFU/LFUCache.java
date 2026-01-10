package LFU;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    private int capacity;
    Map<Integer,Node> lfuCache;
    Map<Integer,DoublyLinkedList> freqMap;
    int minFreq = 0;

    public LFUCache( int capacity ) {
        this.capacity = capacity;
        this.lfuCache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public Integer get( int key ){
        Node node = lfuCache.get(key);
        if( node == null ) return null;
        updateFrequency(node);
        return node.val;
    }

    public void put( int key , int value ){
        if( capacity == 0 )
            return;

        Node node = lfuCache.get(key);
        if( node != null ){
            node.val = value;
            updateFrequency(node);
            return;
        }

        if( lfuCache.size() == capacity ){
            DoublyLinkedList minList = freqMap.get(minFreq);
                Node removed = minList.removeTail();
                lfuCache.remove(removed.key);
        }

        node = new Node(key,value);
        lfuCache.put(key,node);
        freqMap.computeIfAbsent(1,
                k -> new DoublyLinkedList()).addToHead(node);

        minFreq = 1;
    }

    public void updateFrequency( Node node ){
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);

        oldList.remove(node);

        if (oldList.isEmpty()) {
            freqMap.remove(oldFreq);
            if (oldFreq == minFreq) {
                minFreq++;
            }
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList())
                .addToHead(node);
    }
}
