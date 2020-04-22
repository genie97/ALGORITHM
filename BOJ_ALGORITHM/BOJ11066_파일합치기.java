import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066_파일합치기 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] sum = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// Ex) N = 4
			// Ex) 40 30 30 50
			// 누적합 40 70 100 150
			for (int i = 1; i <= N; i++) {
				sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
			}
			int[][] chain = new int[N + 1][N + 1];
			for (int len = 2; len <= N; len++) { // 합치는 길이 (연속 2~N까지)
				for (int i = 1; i <= N - len + 1; i++) { // 1 ~ 길이만큼 확인 (len=2일때, [1,2] / [2,3] / [3,4]
					int j = i + len - 1;
					chain[i][j] = Integer.MAX_VALUE;

					for (int k = i; k < j; k++) {
						chain[i][j] = Math.min(chain[i][j], chain[i][k] + chain[k + 1][j] + sum[j] - sum[i - 1]);
					}
				}
			}
			sb.append(chain[1][N]).append('\n'); // 1~N까지의 길이
		}
		System.out.println(sb);
	}

}
