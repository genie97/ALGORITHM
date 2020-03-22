import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068_트리 {
	static ArrayList<ArrayList<Integer>> adj;
	static int ans;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int root = 0;
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1)
				root = i;
			else {
				adj.get(p).add(i);
				adj.get(i).add(p);
			}
		}
		int del = Integer.parseInt(br.readLine());
		visit = new boolean[N];
		visit[del] = true;
		ans = 0;
		if (del != root)
			bfs(root);
		System.out.println(ans);
	}

	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(v);
		visit[v] = true;
		while (!q.isEmpty()) {
			v = q.poll();
			boolean flag = false;
			for (int i = 0; i < adj.get(v).size(); i++) {
				int u = adj.get(v).get(i);
				if (visit[u])
					continue;
				flag = true;
				q.add(u);
				visit[u] = true;
			}
			if (!flag) {
				ans++;
			}
		}
	}
}
