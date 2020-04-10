import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753_최단경로 {
	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int V, E;
	static ArrayList<ArrayList<Edge>> adj;
	static int[] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		// 인접리스트 초기화
		adj = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj.get(from).add(new Edge(to, cost));
		}

		D = new int[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE); // 처음에는 길이 없다고 생각하고 무한대로 초기화

		Dijkstra(start);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append(D[i] == Integer.MAX_VALUE ? "INF" : D[i]).append('\n');
		}
		System.out.println(sb);
	}

	static void Dijkstra(int st) {
		boolean[] visit = new boolean[V + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		D[st] = 0; // 시작점은 0
		pq.add(new Edge(st, 0));

		while (!pq.isEmpty()) {
			int nv = pq.poll().to; // start와 연결된 새로운 정점 번호

			if (visit[nv]) // 이전에 방문했던 정점이라면 제외
				continue;

			visit[nv] = true;

			for (Edge e : adj.get(nv)) {
				if (D[e.to] > D[nv] + e.cost) { // 원래 저장된 값과 현 정점에서 가는 cost를 더한걸 비교해서 최소값 갱신
					D[e.to] = D[nv] + e.cost;
					pq.add(new Edge(e.to, D[e.to]));
				}
			}
		}
	}
}
