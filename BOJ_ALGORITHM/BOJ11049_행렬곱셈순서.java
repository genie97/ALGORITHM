import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049_행렬곱셈순서 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 행렬 개수
		StringTokenizer st;
		int[] mSize = new int[2 * N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			mSize[i] = Integer.parseInt(st.nextToken());
			mSize[i + 1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][N + 1];
		for (int l = 2; l <= N; l++) {
			for (int i = 1; i <= N - l + 1; i++) {
				int j = i + l - 1;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int m = dp[i][k] + dp[k+1][j] + mSize[i-1] * mSize[j] * mSize[k];
					dp[i][j] = Math.min(dp[i][j], m);
				}
			}
		}
		System.out.println(dp[1][N]);
	}
}
