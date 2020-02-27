import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2758_로또 {

	public static long[][] dp;
	public static int N;
	public static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dp = new long[N + 1][M + 1]; // M개 중 N개 뽑기
			dp[1][1] = 1;

			// i개 중에 1개 뽑는 경우는 i가지
			for (int i = 1; i <= M; i++) {
				dp[1][i] = i;
			}
			
			for (int i = 2; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (i > j)
						continue;
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
				}
			}
			System.out.println(dp[N][M]);
		}
	}
}
