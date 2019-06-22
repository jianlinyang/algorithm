package newcode;

import java.util.*;

/**
 * @author yang
 * @date 2019/6/19 23:27
 * 剑指offer 1-22
 */
public class Solution {
    /**
     * 1二维数组中的查找
     *
     * @param target 查找对象
     * @param array  被查找数组
     * @return true/false
     */
    public boolean find(int target, int[][] array) {
        int deep = array.length - 1;
        int length = array[0].length - 1;
        int i = 0;
        while (i <= length && deep >= 0) {
            if (target == array[deep][i]) {
                return true;
            }
            if (target > array[deep][i]) {
                i++;
            } else {
                deep--;
            }
        }
        return false;
    }

    /**
     * 2.替换空格
     *
     * @param str 输入字符串
     * @return result
     */
    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (' ' == str.charAt(i)) {
                str.replace(i, i + 1, "%20");
                length = length + 2;
            }
            i++;
        }
        return str.toString();
    }


    /**
     * 3.从头到尾打印链表
     *
     * @param listNode {@link ListNode}
     * @return arr
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * 4.重建二叉树
     */
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 5.两个栈实现队列
     *
     * @param node node
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 6.旋转数组的最小数字
     *
     * @param array input
     * @return int
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) return 0;
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[right] >= array[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }

    /**
     * 7.斐波那楔数列
     * 0 1 1 2 3 5
     *
     * @param n 第n项
     * @return int
     */
    public int Fibonacci(int n) {
        if (n <= 1) return n;
        int i = 0, j = 1;
        while (n > 1) {
            j = i + j;
            i = j - i;
            n--;
        }
        return j;
    }

    /**
     * 8.跳台阶
     *
     * @param target n阶
     * @return 跳法
     */
    public int JumpFloor(int target) {
        if (target <= 2) return target;
        int i = 1, j = 1;
        while (target >= 2) {
            j = i + j;
            i = j - i;
            target--;
        }
        return j;
    }

    /**
     * 9.变态跳台阶
     * 动态规划
     *
     * @param target 阶
     * @return int
     */
    public int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 0; i < target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target - 1];
    }

    /**
     * 10.二进制中1的个数
     *
     * @param n input
     * @return num
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 11.数的整数次方
     *
     * @param base     底数
     * @param exponent 指数
     * @return res
     */
    public double Power(double base, int exponent) {
        boolean flag = false;
        double res = 1D;
        if (exponent < 0) {
            exponent = -exponent;
            flag = true;
        }
        while (exponent > 0) {
            res = res * base;
            exponent--;
        }
        return flag ? 1 / res : res;
    }

    /**
     * 12.调整数组顺序使奇数位于偶数前面
     *
     * @param array input
     */
    public void reOrderArray(int[] array) {
        int even = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 0) {//偶数
                even = i;
                break;
            }
        }
        int i = even;
        while (i < array.length) {
            if ((array[i] & 1) != 0) {
                swap(array, even, i);
                even++;
            }
            i++;
        }
    }

    private void swap(int[] array, int s, int e) {
        int tmp = array[e];
        for (int i = e; i > s; i--) {
            array[i] = array[i - 1];
        }
        array[s] = tmp;
    }

    /**
     * 13.链表中第K个节点
     *
     * @param head {@link ListNode}
     * @param k    倒数第k节点
     * @return {@link ListNode}
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) return null;
        int count = 0;
        LinkedList<ListNode> listNodes = new LinkedList<>();
        while (head != null) {
            listNodes.push(head);
            count++;
            head = head.next;
        }
        if (count < k) return null;
        while (k > 1) {
            listNodes.pop();
            k--;
        }
        return listNodes.pop();
    }

    /**
     * 14.反转链表
     *
     * @param head {@link ListNode}
     * @return {@link ListNode}
     */
    public ListNode ReverseList(ListNode head) {
        ListNode tmp;
        ListNode pre = null;
        while (head != null) {
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    /**
     * 15.合并两个排序的链表
     *
     * @param list1 {@link ListNode}
     * @param list2 {@link ListNode}
     * @return {@link ListNode}
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode head = res;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                res.next = list1;
                res = res.next;
                list1 = list1.next;
            } else {
                res.next = list2;
                res = res.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) res.next = list2;
        if (list2 == null) res.next = list1;
        return head.next;
    }

    /**
     * 测试
     *
     * @param args args
     */
    public static void main(String[] args) {
        Solution solution1 = new Solution();
        int[][] arr1 = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
        String s = " a b";
        ListNode listNode = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        int[] arr2 = {6, 1, 2, 3, 4, 5};
        solution1.reOrderArray(arr2);
        System.out.println(arr2);
    }
}
