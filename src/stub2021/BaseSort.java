package stub2021;

import code.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YangJianlin
 * @date 2021/3/16 17:05
 */
public class BaseSort {
    private static int[] tmp = null;

    public static void main(String[] args) {
//        backtraceTest();
        treeProblemTest();
    }

    private static void treeProblemTest() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
//        preOrder(treeNode1);
        levelOrder(treeNode1);
    }

    private static void levelOrder(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.print(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    private static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            preOrder(treeNode.left);
            System.out.println(treeNode.val);
            preOrder(treeNode.right);
        }
    }

    private static void backtraceTest() {
        char[] c = {'a', 'b', 'c', 'd'};
        boolean[] hasUsed = new boolean[c.length];
        StringBuilder s = new StringBuilder();
        backtrace(c, hasUsed, s);
    }

    private static void backtrace(char[] c, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == c.length) {
            System.out.println(s.toString());
            return;
        }
        for (int i = 0; i < c.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            hasUsed[i] = true;
            s.append(c[i]);
            backtrace(c, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        if (tmp == null || tmp.length != arr.length) {
            tmp = new int[arr.length];
        }
        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int l = left;
        int r = middle + 1;
        int index = left;
        while (l <= middle || r <= right) {
            if (l > middle) {
                tmp[index] = arr[r];
                r++;
            } else if (r > right) {
                tmp[index] = arr[l];
                l++;
            } else if (arr[l] <= arr[r]) {
                tmp[index] = arr[l];
                l++;
            } else {
                tmp[index] = arr[r];
                r++;
            }
            index++;
        }
        for (index = left; index <= right; index++) {
            arr[index] = tmp[index];
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int base = arr[l];
        while (l < r) {
            while (arr[r] >= base && l < r) {
                r--;
            }
            while (arr[l] <= base && l < r) {
                l++;
            }
            if (l < r) {
                swap(arr, l, r);
            }
        }
        swap(arr, left, r);
        quickSort(arr, left, r - 1);
        quickSort(arr, r + 1, right);
    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
