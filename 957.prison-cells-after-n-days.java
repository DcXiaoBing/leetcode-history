import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=957 lang=java
 *
 * [957] Prison Cells After N Days
 */

// @lc code=start
class Solution {
    // can use string as record
    // but bit map is so brillient
    public int[] prisonAfterNDays(int[] cells, int N) {
        // build origin bitmap
        int bitmap = 0, len = cells.length;
        for(int i = 0; i < cells.length; i++) bitmap |= cells[i] << (len - 1 - i);

        // do first conversion
        bitmap = ((1 << len) - 1) & (~(((bitmap << 1) ^ (bitmap >> 1))));
        // set bit at start and end to zero
        bitmap &= ~(1 + (1 << (len - 1)));
        // System.out.println(Integer.toBinaryString(bitmap));

        int first = bitmap;
        List<Integer> list = new ArrayList<>();
        list.add(first);
        // getArray(bitmap, cells);
        // System.out.println(Arrays.toString(cells));
        // System.out.println(Integer.toBinaryString(bitmap));
        for(int i = 1; i < N; i++) {
            bitmap = ((1 << len) - 1) & (~(((bitmap << 1) ^ (bitmap >> 1))));
            bitmap &= ~(1 + (1 << (len - 1)));
            if(bitmap == first) {
                getArray(list.get((N - 1) % list.size()), cells);
                return cells;
            }
            list.add(bitmap);
            // System.out.println(Integer.toBinaryString(bitmap));
            // getArray(bitmap, cells);
            // System.out.println(Arrays.toString(cells));
        }
        
        getArray(bitmap, cells);
        return cells;
    }
    private void getArray(int bitmap, int[] cells) {
        for(int i = 0; i < cells.length; i++){
            cells[i] = 0;
            if(((bitmap >> (cells.length - 1 - i)) & 1) != 0) cells[i] = 1;
            // System.out.println((Integer.toBinaryString(bitmap >> (cells.length - 1 - i))));
            // System.out.println((Integer.toBinaryString(((bitmap >> (cells.length - 1 - i)) & 1))));
        }
        // System.out.println(Integer.toBinaryString(bitmap));
        // System.out.println(Arrays.toString(cells));
    }
}
// @lc code=end

