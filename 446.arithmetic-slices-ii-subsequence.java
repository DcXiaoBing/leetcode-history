/*
 * @lc app=leetcode id=446 lang=java
 *
 * [446] Arithmetic Slices II - Subsequence
 */

// @lc code=start
class Solution {
  // use hashmap to gather frequence

  // same num:
  // dp[i+1] ? dp[i]

  // ! subsequence has to consider order!!!
//   public int numberOfArithmeticSlices(int[] nums) {
//     HashMap<Long, Integer> fre = new HashMap<>();
//     for (int num : nums) {
//       fre.put((long)num, fre.getOrDefault((long)num, 0) + 1);
//     }

//     int n = nums.length, p = 0, res = 0;
//     Arrays.sort(nums);
//     for (int i = 1; i < n; i++) if (nums[i] != nums[p]) nums[++p] = nums[i];
//     n = ++p;
//     // System.out.println(Arrays.toString(Arrays.copyOf(nums, n)));
//     // System.out.println(fre);

//     for (int i = 0; i < n; i++) {
//       // dp from len
//       // 2^n - len0 - len1 - len2
//       int temp = fre.get((long)nums[i]);
//       res += (int)((long)Math.pow(2, temp) - 1 - temp - temp * (temp - 1) / 2);

//       for (int j = i + 1; j < n; j++) {
//         long diff = (long)nums[j] - nums[i], end = nums[j] + diff, cnt = 1L * fre.get((long)nums[i]) * fre.get((long)nums[j]);
//         // at least 3
//         // System.out.println(nums[i] + " " + nums[j] + " " + end + " " + cnt);
//         if (fre.containsKey(end)) while (fre.containsKey(end)) {
//           cnt *= fre.get(end);
//           end += diff;
//           res += (int)cnt;
//         }
//       }
//       // System.out.println(res);
//       // System.out.println();
//     }
//     return res;
//   }


  // consider all diff
  public int numberOfArithmeticSlices(int[] nums) {
    // at nums[i] <diff, length>
    List<HashMap<Integer, Integer>> maps = new ArrayList<>();
    int n = nums.length, res = 0;
    for (int i = 0; i < n; i++) maps.add(new HashMap<>());

    for (int i = 1; i < n; i++) for (int j = 0; j < i; j++) {
      long temp = 0L + nums[i] - nums[j];
      if (temp < Integer.MIN_VALUE || temp > Integer.MAX_VALUE) continue;

      int diff = (int)temp;
      int cnt = maps.get(j).getOrDefault(diff, 0); // new sequence will end at this index.
      int origin = maps.get(i).getOrDefault(diff, 0); // current sum
      maps.get(i).put(diff, cnt + origin + 1); // 1 is the num itself
      // here is at least 3. At least two number to generate diff.
      // And append current one will make it at least 3 element.
      res += cnt;
    }
    return res;
  }
}
// @lc code=end

