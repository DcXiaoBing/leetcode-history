import java.util.*;

/*
 * @lc app=leetcode id=381 lang=java
 *
 * [381] Insert Delete GetRandom O(1) - Duplicates allowed
 */

// @lc code=start
class RandomizedCollection {

    // To perform getRandom in O(1), we cannot combine same element

    HashMap<Integer, HashSet<Integer>> valToidx;
    ArrayList<Integer> vals;
    Random ran;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        valToidx = new HashMap<>();
        vals = new ArrayList<>();
        ran = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = false;
        HashSet<Integer> curSet = valToidx.get(val);
        if(curSet == null || curSet.size() == 0){
            flag = true;
            curSet = new HashSet<>();
            valToidx.put(val, curSet);
        }

        curSet.add(vals.size());
        vals.add(val);
        return flag;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        HashSet<Integer> curSet = valToidx.get(val);
        if(curSet == null || curSet.size() == 0)
            return false;

        if(vals.get(vals.size() - 1) == val){
            curSet.remove(vals.size() - 1);
            vals.remove(vals.size() - 1);
            return true;
        }

        int curIndex = curSet.iterator().next();

        // need explict conversion to use remove(index)
        HashSet<Integer> lastSet = valToidx.get(vals.get(vals.size() - 1));
        
        lastSet.remove(vals.size() - 1);
        lastSet.add(curIndex);
        curSet.remove(curIndex);

        Collections.swap(vals, curIndex, vals.size() - 1); // !! swap after get lastSet
        vals.remove((int)vals.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return vals.get(ran.nextInt(vals.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end

