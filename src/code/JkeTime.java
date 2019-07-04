package code;

import java.util.*;

/**
 * @author yang
 * @date 2019/7/4 8:34
 */
public class JkeTime {
    /**
     * 反转链表
     *
     * @param head input
     * @return {@link ListNode}
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, next = head;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 交换相邻元素
     *
     * @param head input
     * @return {@link ListNode}
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /**
     * 判断链表是否有环
     *
     * @param head Input
     * @return true/false
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有效的括号
     *
     * @param s input
     * @return true/false
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (!stack.isEmpty() && stack.peek() == map.get(aChar)) {
                stack.pop();
            } else {
                stack.push(aChar);
            }
        }
        return stack.isEmpty();
    }


    /**
     * 数据流中第k大元素
     */
    class KthLargest {
        PriorityQueue<Integer> minHeap;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.offer(val);
            } else if (minHeap.peek() < val) {
                minHeap.poll();
                minHeap.offer(val);
            }
            return minHeap.peek();
        }
    }

    /**
     * 滑动窗口最大值
     *
     * @param nums input
     * @param k    target
     * @return res
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            //前面小的都出列
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            //新元素入列
            queue.addLast(i);
            //检查是否在窗口内
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            //添加结果
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }

    /**
     * 有效的字母异位词
     *
     * @param s s1
     * @param t s2
     * @return true/false
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i : count) {
            if (i != 0) return false;
        }
        return true;
    }

    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[2];
    }

    /**
     * 三数之和
     *
     * @param nums num
     * @return {@link List}
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = (nums[l] + nums[r] + nums[i]);
                if (sum == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                    res.add(list);
                    while (l < r && nums[r] == nums[--r]) {
                    }
                    while (l < r && nums[l] == nums[++l]) {
                    }
                } else if (sum < 0) {
                    while (l < r && nums[l] == nums[++l]) {
                    }
                } else {
                    while (l < r && nums[r] == nums[--r]) {
                    }
                }
            }
        }
        return res;
    }

    /**
     * 验证二叉搜索树
     *
     * @param root {@link TreeNode}
     * @return true/false
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && pre.val >= cur.val) return false;
            pre = cur;
            cur = cur.right;
        }
        return true;
    }

    private boolean subValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (max != null && root.val >= max) return false;
        if (min != null && root.val <= min) return false;
        return subValid(root.left, min, root.val) && subValid(root.right, root.val, max);
    }

    /**
     * 公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 计算x^n
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        double res = 1d;
        double cur = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                res = res * cur;
            }
            cur = cur * cur;
        }
        return res;
    }

    /**
     * test
     *
     * @param args args
     */
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 6, 8, 1, 2, 6, 7};
        int[] arr2 = {-1, 0, 1, 2, -1, -4};
        JkeTime jkeTime = new JkeTime();
        jkeTime.maxSlidingWindow(arr, 3);
        jkeTime.twoSum(new int[]{3, 2, 4}, 6);
        List<List<Integer>> lists = jkeTime.threeSum(arr2);
    }
}
