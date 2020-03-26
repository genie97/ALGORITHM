import java.util.Scanner;

public class BOJ14916_거스름돈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[100001];
		int[] coin = { 2, 5 };
		dp[0] = dp[1] = Integer.MAX_VALUE;
		dp[2] = dp[5] = 1;
		for (int i = 3; i <= n; i++) {
			if (i == 5)
				continue;
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coin.length; j++) {
				if (i - coin[j] < 0 || dp[i - coin[j]] == Integer.MAX_VALUE)
					continue;
				int d = dp[i - coin[j]] + dp[coin[j]];
				dp[i] = Math.min(dp[i], d);
			}
		}
		
		System.out.println(dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
	}
}
