import java.util.Arrays;

/*
 * @lc app=leetcode id=564 lang=java
 *
 * [564] Find the Closest Palindrome
 */

// @lc code=start
class Solution {
    // 1. copy right to left, because significant digit causes more difference
    // 2. modify 1 digit in left will make whole number increase or decrease
    public String nearestPalindromic(String n) {
        Long num = Long.parseLong(n);
        long highLimit = num + 1;
        long lowLimit = num - 1;

        Long h = getHighPalindrome(highLimit);
        Long l = getLowPalindrome(lowLimit);
        // System.out.println(l + " " + h);
        if(Math.abs(h - num) < Math.abs(l - num)) return h.toString();
        else return l.toString();
    }

    char[] getDigit(Long num){
        return num.toString().toCharArray();
    }

    Long getHighPalindrome(long limit){
        char[] originDigit = getDigit(limit);
        char[] targetDigit = originDigit.clone();

        // change to standard
        for(int i = 0; i < targetDigit.length / 2; i++){
            targetDigit[targetDigit.length - 1 - i] = targetDigit[i];
        }

        // generate higher
        // first half must be same, so differ starts at mid + 1
        for(int i = 0; i < targetDigit.length; i++){
            if(originDigit[i] < targetDigit[i])
                return Long.valueOf(new String(targetDigit));
            else if(originDigit[i] > targetDigit[i]){
                // this number is small, need increase
                // but cannnot add here
                // need to increase from central digit

                // len = 9, mid at 5th, idx = 4
                for(int j = (targetDigit.length-1) / 2; j >= 0; j--){
                    targetDigit[j]++;

                    // too big, need to make a carray, so loop continue
                    if(targetDigit[j] > '9'){
                        targetDigit[j] = '0';
                    }else{
                        // has increased, go on
                        break;
                    }
                }
                // make palindrome again
                for(int j = 0; j < targetDigit.length / 2; j++){
                    targetDigit[targetDigit.length - 1 - j] = targetDigit[j];
                }
                // System.out.println(Arrays.toString(targetDigit));
                return Long.valueOf(new String(targetDigit));
            }
        }
        return Long.valueOf(new String(targetDigit));
    }
    Long getLowPalindrome(long limit){
        char[] originDigit = getDigit(limit);
        char[] targetDigit = originDigit.clone();

        // change to standard
        for(int i = 0; i < targetDigit.length / 2; i++){
            targetDigit[targetDigit.length - 1 - i] = targetDigit[i];
        }

        // generate lower
        // first half must be same, so differ starts at mid + 1
        for(int i = 0; i < targetDigit.length; i++){
            if(originDigit[i] > targetDigit[i])
                return Long.valueOf(new String(targetDigit));
            else if(originDigit[i] < targetDigit[i]){
                // this number is small, need increase
                // but cannnot add here
                // need to increase from central digit

                // len = 9, mid at 5th, idx = 4
                for(int j = (targetDigit.length-1) / 2; j >= 0; j--){
                    targetDigit[j]--;

                    // too big, need to make a carray, so loop continue
                    if(targetDigit[j] < '0'){
                        targetDigit[j] = '0';
                    }else{
                        // has increased, go on
                        break;
                    }
                }
                if(targetDigit[0] == '0'){
                    char[] temp = new char[targetDigit.length - 1];
                    Arrays.fill(temp, '9');
                    return Long.valueOf(new String(temp));
                }
                // make palindrome again
                for(int j = 0; j < targetDigit.length / 2; j++){
                    targetDigit[targetDigit.length - 1 - j] = targetDigit[j];
                }
                return Long.valueOf(new String(targetDigit));
            }
        }
        return Long.valueOf(new String(targetDigit));
    }
}
// @lc code=end

