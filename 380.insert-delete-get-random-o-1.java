import java.util.*;

/*
 * @lc app=leetcode id=380 lang=java
 *
 * [380] Insert Delete GetRandom O(1)
 */

// @lc code=start
class RandomizedSet {

    /** Initialize your data structure here. */

    // Use arraylist to get random value
    // use hashmap to perform insert and lookup index
    // delete the element at tail takes constant time
    // so if want to delete, swap it to tail

    ArrayList<Integer> values;
    HashMap<Integer, Integer> valToIndex;
    Random ran;
    public RandomizedSet() {
        values = new ArrayList<>();
        valToIndex = new HashMap<>();
        ran = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(valToIndex.containsKey(val))
            return false;

        valToIndex.put(val, values.size());
        values.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        
        // Do not have this element
        if(!valToIndex.containsKey(val))
            return false;

        // !!! when it only has one element

        // better to add a Explicit conversion
        // Because it could converse to an Integer and cause bug
        // if(values.size() == 1){
        //     values.remove((int)0);
        //     valToIndex.remove(val);
        //     return true;
        // }


        // get Index
        int index = valToIndex.get(val);

        // swap and update hashmap and list
        int lastVal = values.get(values.size() - 1);
        valToIndex.put(lastVal, index);
        // values.set(index, lastVal);
        
        Collections.swap(values, index, values.size() - 1);

        // delete in list
        values.remove(values.size() - 1);
        valToIndex.remove(val);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        // System.out.println(values.size());
        int index = ran.nextInt(values.size());
        return values.get(index);
    }   
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end

