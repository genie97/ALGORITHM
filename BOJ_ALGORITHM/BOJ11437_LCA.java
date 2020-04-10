import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11437_LCA {
	static int[][] ans; // 정점 개수만큼 공통조상 배열 만들기
	static int[] depth;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}

		StringTokenizer st = null;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.get(u).add(v);
			list.get(v).add(u);
		}
		ans = new int[N + 1][17];
		depth = new int[N + 1];
	
		ans[1][0] = 0; // root의 2^0 조상
		dfs(1, 0, 0); // ans배열에 부모정점 채우기
		
		
		for (int i = 1; i <= 16; i++) { // DP배열 생성
			for (int j = 1; j <= N; j++) {
				ans[j][i] = ans[ans[j][i - 1]][i - 1];
			}
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			sb.append(LCA(v1, v2)).append('\n');
		}
		System.out.println(sb);
	}

	static int LCA(int v1, int v2) {
		// 깊이가 다를 때는 동일하게 갱신하기
		if (depth[v1] != depth[v2]) {
			if (depth[v1] > depth[v2]) {
				int tmp = v1;
				v1 = v2;
				v2 = tmp;
			}
			
			// 같은 레벨의 정점으로 끌어올려주기
			for (int i = 16; i >= 0; i--) {
				if (depth[v2] - depth[v1] >= (1 << i)) {
					v2 = ans[v2][i];
				}
			}
		}

		if (v1 == v2)
			return v1;

		if (v1 != v2) {
			for (int i = 16; i >= 0; i--) {
				if (ans[v1][i] != ans[v2][i]) {
					v1 = ans[v1][i];
					v2 = ans[v2][i];
				}
			}
		}

		return ans[v1][0];
	}

	// 현재 정점의 깊이와 부모 체크
	static void dfs(int nv, int dep, int p) {
		depth[nv] = dep;

		for (int i = 0; i < list.get(nv).size(); i++) {
			if (p == list.get(nv).get(i))
				continue;
			ans[list.get(nv).get(i)][0] = nv;
			dfs(list.get(nv).get(i), dep + 1, nv);
		}

	}
}
