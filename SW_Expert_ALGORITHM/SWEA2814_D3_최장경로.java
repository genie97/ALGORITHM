import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2814_D3_최장경로 {
	public static boolean[] visit;
	public static ArrayList<ArrayList<Integer>> list;
	public static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			list = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				list.get(v1).add(v2);
				list.get(v2).add(v1);
			}
			ans = Integer.MIN_VALUE;
			for (int i = 1; i <= N; i++) {
				visit = new boolean[N + 1];
				dfs(i, 1);
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int v, int cnt) {
		visit[v] = true;
		if (ans < cnt) {
			ans = cnt;
		}
		for (int i = 0; i < list.get(v).size(); i++) {
			int u = list.get(v).get(i);
			if (visit[u])
				continue;
			dfs(u, cnt + 1);
		}
		visit[v] = false;
	}
}
