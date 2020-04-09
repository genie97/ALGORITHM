import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1197_최소스패닝트리 {
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int[] parents;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<Edge> list = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Edge(u, v, c));
		}
		parents = new int[V + 1];
		rank = new int[V + 1];

		for (int i = 1; i < parents.length; i++) {
			makeSet(i);
		}

		Collections.sort(list);
		int cnt = 0;
		long res = 0;

		for (int i = 0; i < list.size(); i++) {
			int v = list.get(i).v;
			int u = list.get(i).u;
			int c = list.get(i).cost;

			int pu = findParent(u);
			int pv = findParent(v);
			if (pu == pv) // 부모가 동일하면 넘기기
				continue;
			res += c;
			union(pu, pv);
			cnt++;
			if (cnt == V - 1)
				break;
		}
		System.out.println(res);
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py]) {
				rank[px]++;
			}
		}
	}

	static int findParent(int x) {
		if (parents[x] == x)
			return x;
		else
			return parents[x] = findParent(parents[x]);
	}

	static void union(int px, int py) {
		link(px, py);
	}

}
