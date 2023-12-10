/*
 * @lc app=leetcode id=691 lang=java
 *
 * [691] Stickers to Spell Word
 */

// @lc code=start
class Solution {
    
    // dfs + brach cut
    // brute force works from solution!
    // When a sticker dominates another, we shouldn't include the dominated sticker in our sticker collection. [Here, we say a sticker A dominates B if A.count(letter) >= B.count(letter) for all letters.]
    // this is what I havn't come up
    // The way in answer computes "domain" in a clever way
    
    // !!! answer's solution is a lot more fast
    // it just try to use each row as much as possible
    
    // dp? treemap?
    // build treemap in every recurrsion?
    public int minStickers(String[] stickers, String target) {
        min = target.length() + 1;
        
        int[] fre = new int[26];
        for(int i = 0, len = target.length(); i < len; i++) fre[target.charAt(i) - 'a']++;
        
        // get non-zero fre index
        int idx[] = new int[26], p = 0;
        for(int i = 0; i < 26; i++) if(fre[i] > 0) idx[i] = p++; else idx[i] = -1;
        
        // compact the into a compress array
        int targetFre[] = new int[p];
        p = 0;
        for(int n : fre) if(n > 0) targetFre[p++] = n;
        
        // get fre of letters contained
        int[][] stickerFre = new int[stickers.length][p];
        for(int i = 0; i < stickers.length; i++){
            for(char c : stickers[i].toCharArray()){
                int tempIdx = idx[c - 'a'];
                if(tempIdx >= 0) stickerFre[i][tempIdx]++;
            }
        }
        // for(int[] row : stickerFre)
            // System.out.println(Arrays.toString(row));
        
        // compute whether each word is domained
        int domained = 0;
        for(int i = 0; i < stickers.length; i++){
            for(int j = domained; j < stickers.length; j++){
                boolean flag = true;
                for(int k = 0; k < p; k++){
                    if(stickerFre[i][k] > stickerFre[j][k]){
                        flag = false;
                        break;
                    }
                }
                
                // record this domained array, put it to head part
                if(flag){
                    int[] temp = stickerFre[i];
                    stickerFre[i] = stickerFre[domained];
                    stickerFre[domained] = temp;
                    domained++;
                    break;
                }
            }
        }
        
        dfs(stickerFre, 0, domained, targetFre, 0);
        return min == target.length() + 1 ? -1 : min;
    }
    
    int min = Integer.MAX_VALUE;
    private void dfs(int[][] stickers, int curIdx, int domained, int[] fre, int total){
        if(total >= min) return ;
        
        // it's okay if some letter has more than enough
        if(curIdx == stickers.length){
            for(int n : fre) if(n > 0) return ; // don't have enough letters
            min = total; // has checked min > total
            return ;
        }
        
        int maxUse = 0;
        for(int i = 0; i < fre.length; i++){
            if(fre[i] > 0 && stickers[curIdx][i] > 0){
                maxUse = Math.max(maxUse, (fre[i] - 1) / stickers[curIdx][i] + 1); // a very clever way to handle count
            }
        }
        
        for(int i = 0; i < fre.length; i++)
            fre[i] -= maxUse * stickers[curIdx][i]; // get enough letters
        
        dfs(stickers, curIdx + 1, domained, fre, total + maxUse);
        
        while(maxUse > 0){
            for(int i = 0; i < fre.length; i++)
                fre[i] += stickers[curIdx][i];
            maxUse--;
            dfs(stickers, curIdx + 1, domained, fre, total + maxUse);
        }
    }
    
    private boolean check(int[] fre){
        for(int i = 0; i < fre.length; i++) if(fre[i] != 0) return false;
        return true;
    }
}
// @lc code=end

