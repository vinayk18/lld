package LFU;

public class Node {
    int key;
    int val;
    int freq;
    Node prev;
    Node next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}