class MergeSort{
    public void sort(int[] arr) {
        for(int len = 1; len <= arr.length / 2; len *= 2) {
            for(int i = 0; i < arr.length; i += 2 * len) helper(i, i + len, len, arr);
            // System.out.println(Arrays.toString(arr));
        }
    }
    private void helper(int l, int r, int len, int[] arr) {
        int temp[] = new int[len + Math.min(arr.length - r, len)];
        int pL = 0, pR = 0, p = 0;
        
        System.out.println(l + " " + r + " " + len);
        while(p < temp.length && l + pL < r && pR < Math.min(arr.length - r, len)) {
            if(arr[l + pL] < arr[r + pR]) temp[p++] = arr[l + pL++];
            else temp[p++] = arr[r + pR++];
            // System.out.println(Arrays.toString(temp));
        }

        while(pL < len) temp[p++] = arr[l + pL++];
        while(pR < Math.min(len, arr.length - r)) temp[p++] = arr[r + pR++];
        for(int i = l; i < Math.min(r + len, arr.length); i++) arr[i] = temp[i - l];
        // System.out.println(Arrays.toString(temp));
    }
}