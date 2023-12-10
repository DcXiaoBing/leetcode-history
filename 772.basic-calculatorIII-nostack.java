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
    
    // use a global pointer with no stack
    // res = res - pre + pre * num
    // 这个语句太牛逼了
    // DO MATH WHEN GET A NUMBER: MEET A NUMBER OR PARENTHESIS
    
    int i = 0;
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int num = 0, pre = 0, res = 0;
        char sign = '+';
        for(int len = s.length(); i < len; i++){
            char cur = s.charAt(i);
            if(cur == ' ') continue;
            if(cur == '('){
                i++;
                num = calculate(s); 
                // do math when we get a number
                // if we met a * /,  cancel pre operatin, do the new one
                if(sign=='+'){
                    res=res+num;
                    pre=num;
                }else if(sign=='-'){
                    res=res-num;
                    pre=-num;
                }else if(sign=='*'){
                    res=res-pre+pre*num;
                    pre=pre*num;
                }else if(sign=='/'){
                    res=res-pre+pre/num;
                    pre=pre/num;
                }
                num = 0;
            }else if(Character.isDigit(cur)){
                while(i < len && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                
                i--; // offset the i++ at end
                
                // do math when we get a number
                // if we met a * /,  cancel pre operatin, do the new one
                if(sign=='+'){
                    res=res+num;
                    pre=num;
                }else if(sign=='-'){
                    res=res-num;
                    pre=-num;
                }else if(sign=='*'){
                    res=res-pre+pre*num;
                    pre=pre*num;
                }else if(sign=='/'){
                    res=res-pre+pre/num;
                    pre=pre/num;
                }
                num = 0;
            }else if(cur == ')'){
                break;
            }else{
                sign = cur;
            }
        }
        return res;
    }
    
    int compute(char op, int left, int right){
        switch(op){
            case '*': return left * right;
            case '/': return left / right;
            case '+': return left + right;
            case '-': return left - right;
        }
        return -1;
    }
}
// @lc code=end

