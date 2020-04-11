import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1328_고층빌딩 {
	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		long[][][] dp = new long[N + 1][L + 1][R + 1];
		dp[1][1][1] = 1;
		for (int i = 2; i <= N; i++) {
			for (int l = 1; l <= L; l++) {
				for (int r = 1; r <= R; r++) {
					dp[i][l][r] = (dp[i-1][l-1][r] + dp[i-1][l][r-1] + (i-2) * dp[i-1][l][r]) % MOD;
				}
			}
		}
		System.out.println(dp[N][L][R]);
	}

}
