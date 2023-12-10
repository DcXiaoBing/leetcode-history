import java.util.Random;

class QuickSort {
    Random ran = new Random();
    public void sort(int[] arr) {
        helper(arr, 0, arr.length - 1);
    }

    private void helper(int[] arr, int l, int r) {
        if(l >= r) return ;
        
        int pivotIdx = l + ran.nextInt(r - l + 1);
        int mid = partation(arr, l, r, pivotIdx);
        helper(arr, l, mid - 1); 
        helper(arr, mid + 1, r);
    }

    private int partation(int[] arr, int l, int r, int pIdx) {
        int pivot = arr[pIdx];
        swap(arr, pIdx, r); // swap to right

        for(int i = l; i <= r; i++) {
            if(arr[i] < pivot) swap(arr, l++, i);
        }
        swap(arr, l, r); // swap pivot to its position
        return l;
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}