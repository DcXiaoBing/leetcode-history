import java.util.*;

/*
 * @lc app=leetcode id=381 lang=java
 *
 * [381] Insert Delete GetRandom O(1) - Duplicates allowed
 */

// @lc code=start
class RandomizedCollection {

    // To reach the O(1) for getRandom, we cannot combine same elements
    // remove can choose one to remove
    // HashMap stores a list of index

    // To remove a index in O(1) time, so it has to be hashset

    /** Initialize your data structure here. */

    HashMap<Integer, HashSet<Integer>> valueToIndex;
    ArrayList<Integer> values;
    Random ran;

    public RandomizedCollection() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
        ran = new Random();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not
     * already contain the specified element.
     */
    public boolean insert(int val) {
        HashSet<Integer> cur = valueToIndex.get(val);
        boolean flag = false;
        if (cur == null) {
            cur = new HashSet<>();
            valueToIndex.put(val, cur);
            flag = true;
        }
        cur.add(values.size());
        values.add(val);

        return flag;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained
     * the specified element.
     */
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val))
            return false;

        // System.out.println(val);
        // perform swap
        HashSet<Integer> curSet = valueToIndex.get(val), lastSet;

        int lastElement = values.get(values.size() - 1);

        // last is the target, just remove last one
        if(lastElement == val){
            // System.out.println("here");
            curSet.remove(values.size() - 1);
            values.remove(values.size() - 1);
            // System.out.println("here1");

            // also need a delete
            if(curSet.size() == 0)
                valueToIndex.remove(val);
            return true;
        }

        int index = curSet.iterator().next();
        lastSet = valueToIndex.get(lastElement);

        // update set
        lastSet.remove(values.size() - 1); // size has changed
        lastSet.add(index);
        curSet.remove(index);

        // delete
        if (curSet.size() == 0) {
            valueToIndex.remove(val);
        }

        // delete in list
        Collections.swap(values, index, values.size() - 1);
        values.remove(values.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return values.get(ran.nextInt(values.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection(); boolean param_1 =
 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
 * obj.getRandom();
 */
// @lc code=end
