package practice;

import code.TreeNode;

import java.util.*;

/**
 * @author yang
 * @date 2019/7/8 21:11
 */
public class TreeProblem {
    public static void pre(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
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
    public static void after(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            if (pop.left != null) stack1.push(pop.left);
            if (pop.right != null) stack1.push(pop.right);
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }

    /**
     * 二叉搜索树第K个节点
     *
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = pRoot;
        int count = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                count++;
                TreeNode pop = stack.pop();
                if (count == k) return pop;
                cur = pop.right;
            }
        }
        return null;
    }

    //序列化
    String Serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                stringBuilder.append(poll.val + ",");
                queue.offer(poll.left);
                queue.offer(poll.right);
            } else {
                stringBuilder.append("#,");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] split = str.split(",");
        TreeNode[] treeNodes = new TreeNode[split.length];
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("#")) treeNodes[i] = new TreeNode(Integer.valueOf(split[i]));
        }
        for (int i = 0, j = 1; j < split.length; i++) {
            if (treeNodes[i] != null) {
                treeNodes[i].left = treeNodes[j++];
                treeNodes[i].right = treeNodes[j++];
            }
        }
        return treeNodes[0];
    }

    //层次遍历
    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    tmp.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            if (tmp.size() != 0) res.add(tmp);
        }
        return res;
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t == null) continue;
            ret.add(t.val);
            queue.add(t.left);
            queue.add(t.right);
        }
        return ret;
    }

    //深度
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            res++;
        }
        return res - 1;
    }

    //之字遍历
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean reverse = false;
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    tmp.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            if (tmp.size() != 0) {
                if (reverse) {
                    Collections.reverse(tmp);
                }
                res.add(tmp);
            }
            reverse = !reverse;
        }
        return res;
    }

    /**
     * 重建二叉树
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


    //一下递归方法

    /**
     * 对称二叉树
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return subSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean subSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return subSymmetrical(left.left, right.right) && subSymmetrical(right.left, left.right);
    }

    /**
     * 1树的子结构
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
     * 二叉树的镜像
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
     * 二叉搜索树的后序遍历序列
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
     * 二叉树中和为某一路径
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
     * 二叉搜索树转双向链表
     *
     * @param pRootOfTree input
     * @return {@link TreeNode}
     */

    private TreeNode pre = null;
    private TreeNode head = null;

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

    /**
     * 平衡二叉树
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


    public static void main(String[] args) {
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
//        Day2_2.pre(treeNode1);
        TreeProblem treeProblem = new TreeProblem();
        treeProblem.isValidBST(treeNode1);
//        TreeProblem.after(treeNode1);

    }
}
