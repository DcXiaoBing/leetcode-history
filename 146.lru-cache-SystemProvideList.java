import java.util.*;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start
class LRUCache {

    // Use a LinkedList to keep the reference in order of called
    // use hashmap to do the query of transfering number to reference

    // remove first leads to index change
    // so memorize index won't work

    HashMap<Integer, Integer> keyToValue;
    LinkedList<Integer> keys;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToValue = new HashMap<>();
        keys = new LinkedList<>();
    }

    public int get(int key) {
        if (keyToValue.containsKey(key)) {
            keys.remove(new Integer(key)); // remove(int) is considered as index
            keys.add(key);
            return keyToValue.get(key);
        } else
            return -1;
    }

    public void put(int key, int value) {
        if (keyToValue.containsKey(key)) {
            // put them to the back
            // Iterator<Integer> temp = keys.iterator();
            // while (temp.hasNext()) {
            // if (temp.next() == key) {
            // temp.remove();
            // break;
            // }
            // }
            keys.remove(new Integer(key));
            keys.add(key);
            keyToValue.put(key, value); // may update value
        } else {
            if (keys.size() >= capacity) {
                // need eject
                int leastUseKey = keys.removeFirst();
                keyToValue.remove(leastUseKey);
            }

            // put new key
            keys.add(key);
            keyToValue.put(key, value);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
// @lc code=end
