              if (s == null || s.length() == 0)
            return res;
        helper(s, 0, new LinkedList<>());

        return res;
    }

    void helper(String s, int curIndex, LinkedList<String> curL) {

        // base case
        if (curIndex == arrayS.length) {
            res.add(new LinkedList<>(curL));
            return;
        }

        // compute
        for (int i = curIndex; i < arrayS.length; i++) {
            if (isPalindrom(curIndex, i)) {
                curL.add(s.substring(curIndex, i + 1));
                helper(s, i + 1, curL);
                curL.removeLast();
            }
        }
    }

    boolean isPalindrom(int start, int end) {
        while (start < end) {
            if (arrayS[start] != arrayS[end])
                return false;

            start++;
            end--;
        }
        return true;
    }
}
// @lc code=end
