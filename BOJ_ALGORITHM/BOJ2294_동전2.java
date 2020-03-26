import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ2294_동전2 {
	static int[] dp = new int[10001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] coin = new int[N];
		for (int i = 1; i <= K; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= K; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coin.length; j++) {
				if (i - coin[j] < 0 || dp[i - coin[j]] == Integer.MAX_VALUE)
					continue;
				dp[i] = Math.min(dp[i], dp[i - coin[j]] + 1);
			}

		}

		System.out.println(dp[K]);
	}
}
