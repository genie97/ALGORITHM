import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_D3_햄버거다이어트_DP {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[] T = new int[N + 1];
			int[] K = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				T[i] = Integer.parseInt(st.nextToken()); // 맛
				K[i] = Integer.parseInt(st.nextToken()); // 칼로리
			}
			int[][] value = new int[K.length][L + 1];

			for (int i = 1; i < K.length; i++) {
				for (int j = 0; j < K[i]; j++) {
					value[i][j] = value[i - 1][j];
				}
				for (int j = K[i]; j <= L; j++) {
					int now = value[i - 1][j - K[i]] + T[i];
					int pre = value[i - 1][j];
					value[i][j] = now >= pre ? now : pre;
				}
			}
			sb.append(value[N][L]).append('\n');
		}
		System.out.println(sb);
	}
}
