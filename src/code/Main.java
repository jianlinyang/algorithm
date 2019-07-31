package code;

public class Main {

    public int solution(int index) {
        int i7 = 1, i3 = 1, i5 = 1;
        int[] dp = new int[index];
        for (int i = 0; i < index; i++) {
            int n3 = i3 * 3;
            int n5 = i5 * 5;
            int n7 = i7 * 7;
            dp[i] = Math.min(n7, Math.min(n3, n5));
            if (dp[i] == n3) i3++;
            if (dp[i] == n5) i5++;
            if (dp[i] == n7) i7++;
        }
        return dp[index - 1];
    }

    public static void main(String[] args) {
        int[] ints = {4, 5, 1, 6, 2, 7, 3, 8};
        Main main = new Main();
        System.out.println(main.solution(1000));
    }
}