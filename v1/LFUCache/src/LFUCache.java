import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    Map < Integer , DoublyLinkedList.Node > nodeMap = new HashMap<>();
    Map<Integer,DoublyLinkedList> freqMap = new HashMap<>();
    int capacity;
    int minFreq = 0;
    int size = 0;

    LFUCache( int capacity ){
        this.capacity = capacity;
    }

    public int get( int key ){
        if (!nodeMap.containsKey(key)) return -1;

        DoublyLinkedList.Node result = nodeMap.get(key);
        updateFrequency( result );
        return result.value;
    }

    private void updateFrequency(DoublyLinkedList.Node node){
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);

        if( oldList != null) {
            oldList.remove(node);
            if (oldList.isEmpty()) {
                freqMap.remove(oldFreq);
            }
            if( oldFreq == minFreq ) minFreq = oldFreq+1;
        }
        node.freq = oldFreq + 1;
        freqMap.computeIfAbsent(node.freq, f ->new DoublyLinkedList()).addNode(node);
    }

    public void put( int key, int value ){
        if( capacity == 0 ) return;

        if( nodeMap.containsKey(key)){
            DoublyLinkedList.Node node = nodeMap.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        if( size == capacity ) {
            DoublyLinkedList list = freqMap.get(minFreq);
            if (list != null) {
                DoublyLinkedList.Node toRemove = list.removeTail();
                if (toRemove != null) {
                    nodeMap.remove(toRemove.key);
                    size--;
                }
                if (list.isEmpty())
                    freqMap.remove(minFreq);
            }
        }

        DoublyLinkedList.Node newNode = new DoublyLinkedList.Node(key, value);
        newNode.freq = 1;
        freqMap.computeIfAbsent(1, f-> new DoublyLinkedList()).addNode(newNode);
        nodeMap.put(key,newNode);
        minFreq = 1;
        size++;
    }
}
