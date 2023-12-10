/*
 * @lc app=leetcode id=394 lang=java
 *
 * [394] Decode String
 */

// @lc code=start
class Solution {
    char[] arrayS;
    public String decodeString(String s) {
        // deal with corener input
        if(s == null || s.length() == 0)
            return s;
        arrayS = s.toCharArray();
        return helper(0, s.length() - 1);
    }
    String helper(int start, int end){
        // start and end is the range of a string in square bracket
        // start and end are both inclusive

        StringBuilder res = new StringBuilder();
        int count = 0;
        for(int i = start; i <= end; i++){
            
            if('0' <= arrayS[i] && arrayS[i] <= '9'){
                count = count * 10 + arrayS[i] - '0';
            }else if(arrayS[i] == '['){
                // numbers has '[' after, so it's time to set 0
                int tempS = i, tempCounter = 0;
                
                // find its range
                do{
                    if(arrayS[i] == '[') tempCounter++;
                    else if(arrayS[i] == ']') tempCounter--;
                    i++;
                }while(tempCounter != 0);

                // now i points to the character after ']'
                i--; // make it back
                String tempSub = helper(tempS + 1, i-1);
                while(count > 0){
                    if((count & 1) == 1)
                        res.append(tempSub);

                    tempSub = tempSub + tempSub;
                    count >>= 1;
                }
            }else{
                // no repeat, just letters
                res.append(arrayS[i]);
            }

        }

        // System.out.println(res);
        return res.toString();
    }
}
// @lc code=end

