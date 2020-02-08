import java.util.Arrays;
import java.util.Scanner;

public class SWEA1486_D4_장훈이의높은선반 {
	private static int[] emp;
	private static boolean[] use;
	private static int N;
	private static int B;
	private static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			B = sc.nextInt();
			emp = new int[N+1];
			for (int i = 0; i < N; i++) {
				emp[i] = sc.nextInt();
			}

			use = new boolean[N];
			min = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void dfs(int cnt, int sum) {
		if (cnt > N) return;
		if (sum >= B) {
			min = Integer.min(min, sum - B);
		}
		dfs(cnt + 1, sum + emp[cnt]);
		dfs(cnt + 1, sum);

	}
}
