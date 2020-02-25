import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724_연결요소의개수 {

	public static int[] p;
	public static ArrayList<ArrayList<Integer>> edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edge = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			edge.add(new ArrayList<Integer>());
		}

		p = new int[N + 1];
		initParent();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 무방향 그래프
			edge.get(u).add(v);
			edge.get(v).add(u);
		}
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (p[i] == i) {
				bfs(i);
				ans++;
			}
		}
		System.out.println(ans);
	}

	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(v);
		while (!q.isEmpty()) {
			int cv = q.poll();
			for (int i = 0; i < edge.get(cv).size(); i++) {
				int nv = edge.get(cv).get(i);
				if (p[nv] == v)
					continue;
				p[nv] = v;
				q.add(nv);
			}

		}
	}

	public static void initParent() {
		for (int i = 1; i < p.length; i++) {
			p[i] = i;
		}
	}
}
