import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A는 B와 친구다. 
 * B는 C와 친구다. 
 * C는 D와 친구다. 
 * D는 E와 친구다. 
 * A>B>C>D>E가 있는 지 판단하면 됨!  
 * N: 사람수 (5 ≤ N ≤ 2000) 
 * M: 친구 관계의 수 (1 ≤ M ≤ 2000) 
 * a와 b가 친구관계이다
 */
public class BOJ13023_ABCDE {
	static int N, M;
	static ArrayList[] list;
	static boolean flag;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				dfs(i, 0);
			}
		}
	
		System.out.println(flag ? 1 : 0);
	}

	static void dfs(int v, int depth) {
		if (flag)
			return;
		if (depth == 4) {
			flag = true;
			return;
		}
		visit[v] = true;
		for (int i = 0; i < list[v].size(); i++) {
			int u = (int) list[v].get(i);
			if (visit[u])
				continue;
			dfs(u, depth + 1);
		}
		visit[v] = false;
	}
}
