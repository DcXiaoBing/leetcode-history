import java.util.*;

/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Deque<Character> stack = new ArrayDeque<>();

        boolean res = true;
        for (int i = 0, len = s.length(); i < len; i++) {
            char cur = s.charAt(i);

            switch (cur) {
            case '(':
            case '[':
            case '{':
                stack.push(cur);
                break;

            case ')':
            case ']':
            case '}':
                // always check empty before peek
                if (stack.isEmpty() || !isMatch(cur, stack.peek()))
                    return false;
                else
                    stack.pop();
            }
        }
        return res && stack.isEmpty();
    }

    boolean isMatch(char cur, char stack) {
        switch (cur) {
        case ')':
            return stack == '(';
        case ']':
            return stack == '[';
        case '}':
            return stack == '{';

        }

        return false;
    }
}
// @lc code=end
