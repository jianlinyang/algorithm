package code;

import java.util.PriorityQueue;

/**
 * @author yang
 * @date 2019/7/9 12:54
 */
public class MidNum {
    private PriorityQueue<Integer> left = new PriorityQueue<>((x, y) -> y - x);
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    private boolean isLeft = true;

    public void Insert(Integer num) {
        if (isLeft) {
            if (left.isEmpty() || num < right.peek()) {
                left.offer(num);
            } else {
                right.offer(num);
                left.offer(right.poll());
            }
        } else {
            if (num < left.peek()) {
                left.offer(num);
                right.offer(left.poll());
            } else {
                right.offer(num);
            }
        }
        isLeft = !isLeft;
    }

    public Double GetMedian() {
        if (left.isEmpty()) return -1d;
        if (isLeft) {
            return (left.peek() + right.peek()) / 2.0d;
        } else {
            return left.peek() / 1.0d;
        }
    }
}
