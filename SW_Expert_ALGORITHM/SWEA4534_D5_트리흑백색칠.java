import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4534_D5_트리흑백색칠 {
	public static long[][] dp;
	public static ArrayList<ArrayList<Integer>> list;
	public static int ans;
	public static int N;
	public static final int MOD = 1000000007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			list = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<Integer>());
			}
			StringTokenizer st;
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				list.get(v1).add(v2);
				list.get(v2).add(v1);
			}
			dp = new long[N+1][2];
			for (int i = 0; i < N + 1; i++) {
				dp[i][0] = -1;
				dp[i][1] = -1;
			}

			ans = 0;
			long ans = 0;
			ans += (dfs(-1, 1, 0) % MOD); // black : 0 white : 1
			ans += (dfs(-1, 1, 1) % MOD);
			sb.append("#").append(tc).append(" ").append(ans % MOD).append("\n");
		}
		System.out.println(sb);
	}

	public static long dfs(int p, int v, int color) {
		int cnt = 0; 
		for (int i = 0; i < list.get(v).size(); i++) {
			int u = list.get(v).get(i);
			if (p == v) 
				continue;
			cnt++;
		}
		if (cnt == 0) 
			return 1;
		if (dp[v][color] != -1) 
			return dp[v][color] % MOD;

	
		if (color == 0) {
			long caseN = 1;

			for (int i = 0; i < list.get(v).size(); i++) {
				long sum = 0;
				int u = list.get(v).get(i);
				if (u != p) {
					sum += (dfs(v, u, 1) % MOD);
					sum %= MOD;
					caseN *= sum;
					caseN %= MOD;
				}
			}
			dp[v][color] = caseN;
		}
		if (color == 1) {
			long caseN = 1;

			for (int i = 0; i < list.get(v).size(); i++) {
				long sum = 0;
				int u = list.get(v).get(i);
				if (u != p) {
					sum += ((dfs(v, u, 0) % MOD) + (dfs(v, u, 1) % MOD)) % MOD;
					sum %= MOD;
					caseN *= sum;
					caseN %= MOD;
				}
			}
			dp[v][color] = caseN;
		}
		return dp[v][color];
	}
}
