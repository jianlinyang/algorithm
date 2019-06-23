package newcode;

import java.util.*;

/**
 * @author yang
 * @date 2019/6/19 23:27
 * 剑指offer
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
     * 16.树的子结构
     *
     * @param root1 A
     * @param root2 B
     * @return 判断B是不是A的子结构
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubtree(TreeNode r1, TreeNode r2) {
        if (r2 == null) return true;
        if (r1 == null) return false;
        if (r1.val != r2.val) return false;
        return isSubtree(r1.left, r2.left) && isSubtree(r1.right, r2.right);
    }

    /**
     * 17.二叉树的镜像
     *
     * @param root input
     */
    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 18.顺时针打印矩阵
     *
     * @param matrix input
     * @return {@link ArrayList}
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList();
        int i = 0, j = matrix[0].length, k = 0, l = matrix.length;
        while (i < j && k < l) {
            print(matrix, i++, j--, k++, l--, res);
        }
        return res;
    }

    private void print(int[][] matrix, int stRow, int enRow, int srCol, int enCol, ArrayList<Integer> list) {
        for (int i = stRow; i < enRow; i++) {
            list.add(matrix[srCol][i]);
        }
        for (int i = srCol + 1; i < enCol; i++) {
            list.add(matrix[i][enRow - 1]);
        }
        if (srCol < enCol - 1) {
            for (int i = enRow - 2; i >= stRow; i--) {
                list.add(matrix[enCol - 1][i]);
            }
        }
        if (stRow < enRow - 1) {
            for (int i = enCol - 2; i >= srCol + 1; i--) {
                list.add(matrix[i][stRow]);
            }
        }
    }

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 19.包含min 的栈
     *
     * @param node input
     */
    public void push_(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop_() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    /**
     * 20.栈的压入弹出顺序
     *
     * @param pushA 压入顺序
     * @param popA  弹出顺序
     * @return 是否存在
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        LinkedList<Integer> stack = new LinkedList<>();
        int i = 0, j = 0;
        while (i < popA.length) {
            stack.push(pushA[i]);
            i++;
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21.从上往下打印二叉树
     *
     * @param root input
     * @return {@link ArrayList}
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            res.add(poll.val);
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        return res;
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
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {4, 5, 3, 2, 1};

        solution1.IsPopOrder(arr2, arr3);
    }
}
