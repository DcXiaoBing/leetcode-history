/*
 * @lc app=leetcode id=1041 lang=java
 *
 * [1041] Robot Bounded In Circle
 */

// @lc code=start
class Solution {
    // If it can be a circle, it will happens in at most 4 loops.
    // if do not have G, then must has a circle
    
    // compute face change and movements (use 1,2 as example)
    // 1,2 -> 2,-1 -> -1,-2 -> -2,1
    // if face nort and movements is (x, y)
    // face east, movements is (y, -x)
    // then face south, movements is (-x, -y)
    // face west (-y, x)
    
    // four direction sum is zero
    // two direction is also zero
    
    // So, core idea is has zero movement or change direction
    
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, dir = 0;
        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
        
        for(int i = 0,len = instructions.length(); i < len; i++) {
            char cur = instructions.charAt(i);
            
            switch(cur) {
                case 'G':
                    x += dx[dir];
                    y += dy[dir];
                    break;
                case 'L':
                    dir--;
                    if(dir < 0) dir += 4;
                    break;
                case 'R':
                    dir++;
                    if(dir >= 4) dir -= 4;
                    break;
            }
        }
        
        if(x == 0 && y == 0 || dir != 0) return true;
        return false;
    }
}
// @lc code=end

