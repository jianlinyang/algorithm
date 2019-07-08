package practice;

/**
 * @author yang
 * @date 2019/7/7 9:15
 */
public class Day1 {
    public void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        int tmp = nums[l];
        while (i < j) {
            while (i < j && nums[j] >= tmp) {
                j--;
            }
            while (i < j && nums[i] <= tmp) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        nums[l] = nums[i];
        nums[i] = tmp;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    /**
     * 数组逆序对
     *
     * @param array
     * @return
     */
    private int[] tmp;
    private long count = 0;

    public int InversePairs(int[] array) {
        tmp = new int[array.length];
        sort(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }

    private void sort(int[] array, int l, int r) {
        if (r <= l) return;
        int m = l + (r - l) / 2;
        sort(array, l, m);
        sort(array, m + 1, r);
        merge(array, l, m, r);
    }

    private void merge(int[] array, int l, int m, int r) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= r) {
            if (i > m) {
                tmp[k] = array[j++];
            } else if (j > r) {
                tmp[k] = array[i++];
            } else if (array[i] <= array[j]) {
                tmp[k] = array[i++];
            } else {
                tmp[k] = array[j++];
                count += m - i + 1;
            }
            k++;
        }
        for (k = l; k <= r; k++) {
            array[k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 5, 1, 9, 7, 8, 6};
        Day1 day1 = new Day1();
        day1.quickSort(nums, 0, nums.length - 1);
//        day1.InversePairs(nums);
    }
}
