import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA1251_D4_하나로 {
	public static class Edge {
		int u, v;
		double cost;

		public Edge(int u, int v, double cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}

	public static ArrayList<Edge> island;
	public static int[] x;
	public static int[] y;
	public static int N;
	public static double E;
	public static int[] p; // 부모 노드
	public static int[] r; // 깊이 (랭크)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			x = new int[N];
			y = new int[N];
			island = new ArrayList<>();
			p = new int[N];
			r = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // x좌표
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " "); // y좌표
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine()); // 세율

			// 가능한 edge 만들기
			makeEdge();
			// cost가 작은 순으로 정렬
			Collections.sort(island, new Comparator<Edge>() {
				@Override
				public int compare(Edge e1, Edge e2) {
					if (e1.cost < e2.cost)
						return -1;
					else if (e1.cost > e2.cost)
						return 1;
					return 0;
				}
			});

			// parent 지정
			for (int i = 0; i < N; i++) {
				makeSet(i);
			}
			double totalCost = 0;
			int edge = 0;
			// 간선 선택
			for (int i = 0; i < island.size(); i++) {
				Edge e = island.get(i);
				int pu = findParent(e.u);
				int pv = findParent(e.v);
				// 같은 그룹이 아님
				if (pu != pv) {
					union(pu, pv);
					edge++;
					totalCost += island.get(i).cost;
					if (edge == N - 1)
						break;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(totalCost)).append("\n");
		}
		System.out.println(sb);
	}

	public static void makeSet(int x) {
		for (int i = 0; i < p.length; i++) {
			p[x] = x; // 부모 저장
		}
	}

	public static int findParent(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = findParent(p[x]);
			return p[x];
		}
	}

	public static void union(int u, int v) {
		link(u, v);
	}

	public static void link(int u, int v) {
		if (r[u] > r[v]) {
			p[v] = u;
		} else {
			p[u] = v;
			if (r[u] == r[v]) {
				r[v]++;
			}
		}
	}

	public static void makeEdge() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double c = calDis(i, j) * E;
				island.add(new Edge(i, j, c));
			}
		}
	}

	public static double calDis(int u, int v) {
		int x1 = Math.abs(x[u] - x[v]);
		int y1 = Math.abs(y[u] - y[v]);
		return Math.pow((double) x1, 2.0) + Math.pow((double) y1, 2.0);
	}
}
