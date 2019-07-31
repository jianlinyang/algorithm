package code;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author yang
 * @date 2019/6/19 23:27
 * 剑指offer
 */
public class NewCode {
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
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inIndex + 1);
        return root;
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

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
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 0) {//偶数
                int even = i;
                while (even < array.length) {
                    if ((array[even] & 1) != 0) {//奇数
                        swap2(array, i, even);
                        break;
                    }
                    even++;
                }
            }
        }
    }

    private void swap2(int[] array, int s, int e) {
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
    public ArrayList<Integer> printMatri(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++)
                res.add(matrix[r1][i]);
            for (int i = r1 + 1; i <= r2; i++)
                res.add(matrix[i][c2]);
            if (r1 != r2)
                for (int i = c2 - 1; i >= c1; i--)
                    res.add(matrix[r2][i]);
            if (c1 != c2)
                for (int i = r2 - 1; i > r1; i--)
                    res.add(matrix[i][c1]);
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
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
     * 22.二叉搜索树的后序遍历序列
     *
     * @param sequence input
     * @return true/false
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int first, int last) {
        if (last - first <= 1) return true;
        int curIndex = first;
        int rootVal = sequence[last];
        while (curIndex < last && sequence[curIndex] <= rootVal)
            curIndex++;
        for (int i = curIndex; i < last; i++) {
            if (sequence[i] < rootVal) return false;
        }
        return verify(sequence, first, curIndex - 1) &&
                verify(sequence, curIndex, last - 1);
    }

    /**
     * 23.二叉树中和为某一路径
     *
     * @param root   二叉树
     * @param target 目标
     * @return {@link ArrayList}
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        backTracking(root, target);
        return res;
    }

    private void backTracking(TreeNode root, int target) {
        if (root == null) return;
        stack.push(root.val);
        if (target == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<>(stack));
        }
        target = target - root.val;
        backTracking(root.left, target);
        backTracking(root.right, target);
        stack.pop();
    }

    /**
     * 24.复杂链表复制
     *
     * @param pHead input
     * @return {@link RandomListNode}
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode randomListNode = new RandomListNode(cur.label);
            randomListNode.next = cur.next;
            cur.next = randomListNode;
            cur = randomListNode.next;
        }

        cur = pHead;
        while (cur != null) {
            if (cur.random != null) {
                RandomListNode random = cur.random;
                cur.next.random = random.next;
            }
            cur = cur.next.next;
        }

        cur = pHead;

        RandomListNode res = cur.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return res;
    }

    private TreeNode pre = null;
    private TreeNode head = null;

    /**
     * 25.二叉搜索树与双向链表
     *
     * @param pRootOfTree input
     * @return {@link TreeNode}
     */


    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return head;
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        node.left = pre;
        if (pre != null) pre.right = node;
        pre = node;
        if (head == null) head = node;
        inOrder(node.right);
    }


    private ArrayList<String> ret = new ArrayList<>();

    /**
     * 26.字符串的排列
     *
     * @param str input
     * @return {@link ArrayList}
     */
    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0)
            return ret;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return ret;
    }

    private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == chars.length) {
            ret.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i])
                continue;
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) /* 保证不重复 */
                continue;
            hasUsed[i] = true;
            s.append(chars[i]);
            backtracking(chars, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }
    }

    /**
     * 27.数组中次数超过一半的数字
     *
     * @param array input
     * @return res
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        int majority = array[0];
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            cnt = array[i] == majority ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = array[i];
                cnt = 1;
            }
        }
        cnt = 0;
        for (int i : array) {
            if (i == majority) {
                cnt++;
            }
        }
        return cnt > array.length / 2 ? majority : 0;
    }

    /**
     * 29.连续数组的最大子串和
     *
     * @param array input
     * @return res
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return -1;
        int res = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i] + max, array[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 30.整数中1个数
     *
     * @param n int
     * @return res
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 0)
            return 0;
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long diviver = i * 10;
            count += (n / diviver) * i + Math.min(Math.max(n % diviver - i + 1, 0), i);
        }
        return count;
    }

    /**
     * 31.把数组排成最小的数
     *
     * @param numbers input
     * @return res
     */
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int length = numbers.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = numbers[i] + "";
        }
        Arrays.sort(strings, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String res = "";
        for (String string : strings) {
            res += string;
        }
        return res;
    }

    /**
     * 32.丑数
     * 动态规划
     *
     * @param index input
     * @return res
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 6) {
            return index;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[index];
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) i2++;
            if (dp[i] == next3) i3++;
            if (dp[i] == next5) i5++;
        }
        return dp[index - 1];
    }

    private long cnt = 0;
    private int[] tmp;  // 在这里声明辅助数组，而不是在 merge() 递归函数中声明

    /**
     * 33.数组中逆序对
     *
     * @param nums input
     * @return res
     */
    public int InversePairs(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1)
            return;
        int m = l + (h - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, h);
        merge(nums, l, m, h);
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m)
                tmp[k] = nums[j++];
            else if (j > h)
                tmp[k] = nums[i++];
            else if (nums[i] < nums[j])
                tmp[k] = nums[i++];
            else {
                tmp[k] = nums[j++];
                this.cnt += m - i + 1;  // nums[i] >= nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        for (k = l; k <= h; k++)
            nums[k] = tmp[k];
    }

    /**
     * 34.两链表公共节点
     *
     * @param pHead1 {@link ListNode}
     * @param pHead2 {@link ListNode}
     * @return {@link ListNode}
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }


    /**
     * 35.数字在排序数组中出现次数
     *
     * @param nums input
     * @param K    target
     * @return res
     */
    public int GetNumberOfK(int[] nums, int K) {
        int first = binarySearch(nums, K);
        int last = binarySearch(nums, K + 1);
        return (first == nums.length || nums[first] != K) ? 0 : last - first;
    }

    private int binarySearch(int[] nums, int k) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    /**
     * 36.二叉树深度
     *
     * @param root input
     * @return res
     */
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    /**
     * 37.平衡二叉树
     *
     * @param root input
     * @return res
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private boolean isBalanced = true;

    private int height(TreeNode root) {
        if (root == null || !isBalanced)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1)
            isBalanced = false;
        return 1 + Math.max(left, right);
    }

    /**
     * 38.只出现一次的两个数
     *
     * @param nums
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;
        for (int num : nums) {
            if ((num & diff) == 0)
                num1[0] ^= num;
            else
                num2[0] ^= num;
        }
    }

    /**
     * 39.和为s的连续正数序列
     *
     * @param sum target
     * @return res
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(i);
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }

    //下一个二叉树节点
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }

    /**
     * 判断镜像
     *
     * @param pRoot
     * @return
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return sub(pRoot.left, pRoot.right);
    }

    private boolean sub(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        if (l.val != r.val) return false;
        return sub(l.left, r.right) && sub(l.right, r.left);
    }

    /**
     * 40.和为s的两个数字
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int l = 0, r = array.length - 1;
        while (l < r) {
            int cur = l + r;
            if (cur == sum) {
                return new ArrayList<>(Arrays.asList(array[l], array[r]));
            }
            if (cur < sum) {
                l++;
            } else {
                r--;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 机器人运动范围
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return countingSteps(threshold, rows, cols, 0, 0, visited);
    }

    public int countingSteps(int limit, int rows, int cols, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || visited[r][c] || bitSum(r) + bitSum(c) > limit) return 0;
        visited[r][c] = true;
        return countingSteps(limit, rows, cols, r - 1, c, visited)
                + countingSteps(limit, rows, cols, r, c - 1, visited)
                + countingSteps(limit, rows, cols, r + 1, c, visited)
                + countingSteps(limit, rows, cols, r, c + 1, visited)
                + 1;
    }

    public int bitSum(int t) {
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;

    }

    /**
     * 乘积数组
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            //计算下三角连乘
            for (int i = 1; i < length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;
            //计算上三角
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }

    private ListNode h = null;
    private ListNode t = null;
    private ListNode n = null;

    public ListNode reverse(ListNode root, int k) {
        subRev(root, k);
        ListNode res = h;
        while (t.next != null) {
            ListNode tmp = t;
            subRev(n, k);
            tmp.next = h;
        }
        return res;
    }

    private void subRev(ListNode root, int k) {
        t = root;
        ListNode pre = root;
        root = root.next;
        while (k > 1) {
            k--;
            if (root == null) {
                h = pre;
                t.next = null;
                return;
            }
            n = root.next;
            root.next = pre;
            pre = root;
            root = n;
        }
        h = pre;
    }

    public int match(String s, String m) {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < m.length(); i++) {
            list.add(m.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (list.contains(c)) {
                list.remove((c));
                if (list.isEmpty()) {
                    return (i - m.length()+1);
                }
            }else {
                int j = i;
                while (list.size() != m.length()) {
                    list.add(s.charAt(--j));
                }
            }
        }
        return -1;
    }

    /**
     * 测试
     *
     * @param args args
     */
    public static void main(String[] args) {
        NewCode solution1 = new NewCode();
        int[][] arr1 = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
        String s = "abcdsadasf";
        String m = "cdas";
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        System.out.println(solution1.match(s, m));
    }
}
