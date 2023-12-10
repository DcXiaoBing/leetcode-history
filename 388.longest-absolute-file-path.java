import java.util.*;

/*
 * @lc app=leetcode id=388 lang=java
 *
 * [388] Longest Absolute File Path
 */

// @lc code=start
class Solution {
    
    // basic idea is dfs
    // \t means next level of a tree
    // \n switch in same level
    // good place to use recurrsion
    
    // has to be a file, use contains(".")
    public int lengthLongestPath(String input) {
        
        // \n is the finish of current entry
        // so split by \n will help us get each entry
        
        
        String lines[] = input.split("\n");
        
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0, cur = 0;
        
        for(String line : lines){
            int depth = 0;
            while(line.charAt(depth) == '\t') depth++;
            
            // previous entry is deeper, pop out these length
            while(stack.size() > depth) stack.pop();
            
            if(stack.isEmpty()){
                // depth means the count of \t
                // +1 to represent the backlash for next entry
                cur = line.length() - depth + 1; 
            }else{
                cur = stack.peek() + line.length() - depth + 1;
            }
            // minus one to remove the count of last backlast which are prepared for next level
            if(line.contains(".")) max = Math.max(max, cur - 1);
            stack.push(cur);
        }
        
        return max;
    }
}
// @lc code=end

