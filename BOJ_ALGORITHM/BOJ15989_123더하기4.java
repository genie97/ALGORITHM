import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15989_123더하기4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			// 1,2,3 더하기 조합을 오름차순으로 표현
			// 끝자리가 1,2,3 중에 하나로 끝난다
			int[][] dp = new int[N + 3][3];
			dp[1][0] = 1; // N = 1, (1)
			dp[2][0] = dp[2][1] = 1; // N = 2, (1,1) (2)
			dp[3][0] = dp[3][1] = dp[3][2] = 1; // N = 3, (1,1,1) (1, 2) (3)

			for (int i = 4; i <= N; i++) {
				//끝자리를 1로 하려면 1만 사용가능
				dp[i][0] = dp[i - 1][0];
				//끝자리를 2로 하려면 1,2 사용가능
				dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
				//끝자리를 1로 하려면 1,2,3 사용가능
				dp[i][2] = dp[i - 3][0] + dp[i - 3][1] + dp[i - 3][2];
			}
			int ans = 0;
			for (int i = 0; i < 3; i++) {
				ans += dp[N][i];
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
