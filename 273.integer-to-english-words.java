/*
 * @lc app=leetcode id=273 lang=java
 *
 * [273] Integer to English Words
 */

// @lc code=start
class Solution {
    String[] digitToString = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tensToString = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] numToString = new String[]{"Thousand", "Million", "Billion"};

    // Three digit
    // Two digit

    // Another way is to scan from end to start
    // that's my first way

    public String numberToWords(int num) {
        // corner case 0
        if(num == 0)
            return "Zero";
        
        StringBuilder res = new StringBuilder();
        int curBase = (int)1e9;

        // 1e9 / 1e9 = 1
        while(curBase > 1){
            if(num >= curBase){
                res.append(" " + getThreeDigitString(num / curBase));
                // System.out.println(num + ", " + curBase);
                res.append(" " + numToString[(int)Math.log10(num) / 3 - 1]);
                num -= num / curBase * curBase;
            }
            
            curBase /= (int)1e3;
        }
        if(num != 0)
            res.append(" " + getThreeDigitString(num));
        

        res.deleteCharAt(0); // a leading space
        return res.toString();
    }
    
    String getTwoDigitString(int num){
        // System.out.println(num);
        if(num < 20)
            return digitToString[num];

        if(num % 10 == 0)
            return tensToString[num / 10];
        else
            return tensToString[num / 10] + " " + digitToString[num % 10];
    }

    String getThreeDigitString(int num){
        System.out.println(num);
        if(num < 100)
            return getTwoDigitString(num);

        String res = digitToString[num / 100] + " " + "Hundred";
        if(num % 100 == 0)
            return res;
        else
            return res + " " + getTwoDigitString(num % 100);
    }
}
// @lc code=end

