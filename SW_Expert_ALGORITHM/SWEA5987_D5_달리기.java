import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5987_D5_달리기 {
	static int N, M;
	static long[] order, dp;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			order = new long[N + 1];
			dp = new long[1 << (N + 1)];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				order[second] |= (1 << first);
			}
			sb.append('#').append(tc).append(' ').append(dfs(0)).append('\n');
		}
		System.out.println(sb);
	}

	public static long dfs(int idx) {
		if (idx == (1 << (N + 1)) - 2) // 모든 경우를 다 본경우
			return 1;
		if (dp[idx] != 0) // memoizaiton이 되어 있는 경우
			return dp[idx];

		for (int i = 1; i <= N; i++) {
			if ((idx & (1 << i)) > 0) // 이미 선택된 경우
				continue;
			if ((idx & order[i]) != order[i]) // 순서 확인 
				continue;
			dp[idx] += dfs(idx | (1 << i));
		}
		return dp[idx];
	}
}
