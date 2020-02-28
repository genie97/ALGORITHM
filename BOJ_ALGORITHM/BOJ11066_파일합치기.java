import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066_파일합치기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] sum = new int[N + 1];
			int[][] dp = new int[N + 1][N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			// 누적합
			for (int i = 1; i <= N; i++) {
				sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
			}
			
			
			for (int l = 2; l <= N; l++) { // 압축하려고 하는 길이 (2~N개까지 존재)
				for (int i = 1; i <= N - l + 1; i++) {
					int j = i + l - 1;
					dp[i][j] = Integer.MAX_VALUE;

					for (int k = i; k < j; k++) {
						int c = dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1];
						dp[i][j] = Math.min(dp[i][j], c);
					}
				}
			}
			System.out.println(dp[1][N]);
		}
	}
}
