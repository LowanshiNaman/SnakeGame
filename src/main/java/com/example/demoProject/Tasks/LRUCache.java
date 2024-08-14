package com.example.demoProject.Tasks;

import java.util.HashMap;

public class LRUCache {


    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (cache.size() >= capacity) {
                cache.remove(removeTail().key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            cache.put(key, newNode);
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);

        // Printing the current cache state
        System.out.println("Get 1: " + lruCache.get(1));
        // Cache state: {2=2, 3=3, 1=1}

        // Adding a new key-value pair should evict the least recently used key (key 2)
        lruCache.put(4, 4);
        // Cache: {3=3, 1=1, 4=4}
        System.out.println("Get 2: " + lruCache.get(2));

        lruCache.put(1, 10);
        System.out.println("Get 1: " + lruCache.get(1));

        // Adding another key-value pair, which should evict the least recently used key (key 3)
        lruCache.put(5, 5);
        // Cache: {4=4, 1=10, 5=5}
        System.out.println("Get 3: " + lruCache.get(3));

        // Print the cache state after all operations
        System.out.println("Get 4: " + lruCache.get(4));
        System.out.println("Get 5: " + lruCache.get(5));
    }

}
