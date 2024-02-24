package com.company;
/*
*Your task, should you choose to accept it, is to implement a LRU cache.
LRU caches are often used to implement caches which you do not want to grow
indefinitely. The cache has a max size, and when a new key is inserted that
would make it grow larger than the max size, the key which has not been
accessed for the longest time is removed to make space.
It should support these operations:
 get(key): get the value of the key if the key exists in the cache
 put(key, value): update or insert the value if the key is not already
present. When the cache reached its capacity, it should invalidate the least
recently used item before inserting a new item.
What happens if you call get(key) on a key which doesn&#39;t exist in the cache is up
to you to define.
Example usage:
cache = new LruCache(2) // capacity = 2

cache.put(1, &quot;1&quot;)
cache.put(2, &quot;2&quot;)
cache.get(1) // returns &quot;1&quot;
cache.put(3, &quot;3&quot;) // evicts key 2, because the key 1 was retrieved
cache.get(2) // returns null, because 2 was just evicted
cache.put(4, &quot;4&quot;) // evicts key 1,
cache.get(1) // returns null, because it was evicted earlier
cache.get(3) // returns &quot;3&quot;
cache.get(4) // returns &quot;4&quot;
 */

import java.util.Map;
import java.util.HashMap;

public class LRUCache {
    public NewNode head;
    public NewNode tail;
    int capacity;
    Map<Integer, NewNode>  keyMap = new HashMap<>();
    public LRUCache(int capacity){
        this.capacity = capacity;
        head = new NewNode();
        tail = new NewNode();
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key){
        if(keyMap.containsKey(key)){
            NewNode r = keyMap.get(key);
            removeNode(r);
            addToHead(r);
            return keyMap.get(key).value;
        }
        else
            return -1;
    }
    public void put( int key, int value){
        NewNode r = null;
        if(keyMap.containsKey(key)){
            r = keyMap.get(key);
            removeNode(r);
            keyMap.remove(key);
            r.value = value;
        }
        else{
            r = new NewNode(key, value);
        }
        addToHead(r);
        keyMap.put(key, r);
        while(keyMap.size() > capacity){
            removeFromTail();
        }

    }

    public void addToHead(NewNode r){
        r.next = head.next;
        head.next.prev = r;
        head.next = r;
        r.prev = head;

    }
    public void removeNode(NewNode r){
        r.prev.next = r.next;
        r.next.prev = r.prev;
    }

    public void removeFromTail(){
        NewNode temp = tail.prev;
        temp.prev.next = tail;
        tail.prev = temp.prev;
        removeNode(temp);
        keyMap.remove(temp.key);
    }

    public static void testLRUCache(){
        LRUCache lru = new LRUCache(2);
        lru.put(1,1);
        lru.put(2,2);
        System.out.println("Size of map is : " + lru.keyMap.size());
        lru.get(2);
        System.out.println("Map has is : " + lru.keyMap);
        lru.put(3,3);
        System.out.println("Map has is : " + lru.keyMap);
    }

}

class NewNode{
    public NewNode next = null;
    public NewNode prev = null;
    int key;
    int value;
    public NewNode(){
        value = 0;
        key = 0;
    }
    public NewNode(int value , int key){
        this.value = value;
        this.key = key;
    }
}