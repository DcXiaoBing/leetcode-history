import java.util.LinkedList;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */

// @lc code=start
class MinStack {

    /** initialize your data structure here. */
    LinkedList<Integer> stack;
    int min;

    public MinStack() {
        stack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (stack.isEmpty() || x <= min) {

            stack.add(min);
            stack.add(x);
            min = x;

        } else {
            stack.add(x);
        }
    }

    public void pop() {

        // x is before min
        // only change min when cur min is ejected
        int temp = stack.removeLast();
        if (temp == min) {
            min = stack.removeLast();
        }
    }

    public int top() {
        return stack.getLast();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */
// @lc code=end
