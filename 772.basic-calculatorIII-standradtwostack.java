/*
 * @lc app=leetcode id=772 lang=java
 *
 * [772] Basic Calculator III
 */

// @lc code=start
class Solution {
    
    // let's try the way with one stack in Basic Calculator II
    // 1-when meet end, ' ' or non-digit, do the calculate
    // but need one more process: add up at end
    
    // use no-stack solution
    // so complicated when met (
    // use the idea that push temp res into stack
    
    // recursive is the best one -- O(n^2) is too slow
    
    // use a two stack solution
    
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Deque<Character> ops = new ArrayDeque<>();
        Deque<Integer> nums = new ArrayDeque<>();
        
        int num = 0;
        
        for(int i = 0, len = s.length(); i < len; i++){
            char cur = s.charAt(i);
            if(cur == ' ') continue;
            if(Character.isDigit(cur)){
                while(i < len && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0'; // !!! AHHHHHH not cur here
                    i++;
                }
                if(i < len)
                    i--; // offset the i++ at end
                nums.push(num);
                num = 0; // get the number one time to avoid troubles
            }else if(cur == '+' || cur == '-' || cur == '*' || cur == '/'){
                if(cur == '-' && nums.isEmpty()) nums.push(0);
                while(!ops.isEmpty() && precedence(ops.peek(), cur)){
                    nums.push(compute(ops, nums));
                }
                ops.push(cur);
            }else if(cur == '('){
                ops.push('(');
                // definatly has pushed nums into stack
                // because we must have met an operator first
                // nums.push(num);
                // num = 0;
                
                // handle negative number
                while(i + 1 < len && s.charAt(i+1) == ' ') i++;
                if(s.charAt(i+1) == '-') nums.push(0);
            }else if(cur == ')'){
                // only has operators that has only same type
                while(ops.peek() != '('){
                    nums.push(compute(ops, nums));
                }
                ops.pop(); // pop out '('
            }
        }
        // System.out.println(ops);
        // System.out.println(nums);
        // deal with rest operations
        while(!ops.isEmpty()){
            // System.out.println(ops);
            // System.out.println(nums);
            nums.push(compute(ops, nums));
        }
        return nums.pop();
    }
    boolean precedence(char top, char cur){
        // true means op at top has lower priority
        // true means we need to pop out top
        // '(' can never be poped out
        // same type should be poped out
        
        // both parenthesis has lowest
        // + -
        // * /
        
        // top can never be ')' because we do not push it into stack
        if(top == '(') return false;
        if((top == '*' || top == '/')) return true;
        if((top == '+' || top == '-') && (cur == '+' || cur == '-')) return true;
        return false;
    }
    int compute(Deque<Character> ops, Deque<Integer> nums){
        int left = 0, right = 0;
        right = nums.pop();
        if(!nums.isEmpty()) left = nums.pop();
        
        switch(ops.pop()){
            case '*': return left * right;
            case '/': return left / right;
            case '+': return left + right;
            case '-': return left - right;
        }
        return -1;
    }
}
// @lc code=end

