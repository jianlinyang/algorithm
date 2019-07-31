package code;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yang
 * @date 2019/7/9 13:35
 */
public class TopK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (nums.length == 0 || k > nums.length) return res;
        find(nums, k - 1);
        for (int i = 0; i < k; i++) {
            res.add(nums[i]);
        }
        return res;
    }
    private void find(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = quickSort(nums, l, h);
            if (j == k) break;
            if (j > k)
                h = j - 1;
            else
                l = j + 1;
        }
    }
    private int quickSort(int[] nums, int l, int r) {
        int i = l, j = r;
        int tmp = nums[l];
        while (i < j) {
            while (i < j && nums[j] >= tmp) j--;
            while (i < j && nums[i] <= tmp) i++;
            if (i < j) swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 5, 1, 9, 7, 8, 6};
        TopK topK = new TopK();
        ArrayList<Integer> k = topK.GetLeastNumbers_Solution(nums, 1);
        for (Integer integer : k) {
            System.out.println(integer);
        }
    }
}
