/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 */
import java.util.*;
// @lc code=start
class LFUCache {
    // !!! corner case: capacity 0
    // we can manage a frequentList
    // Thus we donnot need to use a huge list

    HashMap<Integer, Integer> keyToVal;
    HashMap<Integer, Integer> fre;
    List<Integer> keys;
    int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        fre = new HashMap<>();
        keyToVal =  new HashMap<>();
        keys = new ArrayList<>();
    }
    
    public int get(int key) {
        if(!keyToVal.containsKey(key))
            return -1;
        
        // add fre
        fre.put(key, fre.get(key) + 1);
        
        // move it to back
        // indicates the using time
        keys.remove((Integer)key);
        keys.add(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        // fre and time
        if(capacity == 0)
            return;

        // exist
        if(keyToVal.containsKey(key)){
            // move to back
            keys.remove((Integer)key);
            keys.add(key);

            // add fre
            fre.put(key, fre.get(key) + 1);
            keyToVal.put(key, value);
            return ;
        }

        // not exist
        if(keys.size() == capacity){
            // need eject
            // search target
            int least_frequent_use = 0;
            int min_fre = Integer.MAX_VALUE;
            for(int i = 0; i < capacity; i++){
                int cur = keys.get(i);
                if(min_fre > fre.get(cur)){
                    min_fre = fre.get(cur);
                    least_frequent_use = cur;
                }
            }

            keys.remove((Integer) least_frequent_use);
            fre.remove(least_frequent_use);
            keyToVal.remove(least_frequent_use);

            
        }
        // add
        keys.add(key);
        fre.put(key, 0);
        keyToVal.put(key, value);   
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

