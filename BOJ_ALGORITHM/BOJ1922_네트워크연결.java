import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922_네트워크연결 {
	static class Edge implements Comparable<Edge> {
		int v, c;

		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		ArrayList<ArrayList<Edge>> list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(v1).add(new Edge(v2, c));
			list.get(v2).add(new Edge(v1, c));
		}

		boolean[] visit = new boolean[N + 1];

		// 프림
		int res = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 비용이 가장 작은 간선
		Deque<Integer> dq = new ArrayDeque<>(); // 정점 방문체크

		ArrayList<Edge> tmpList;

		dq.add(1); // 1번 방문처리
		while (!dq.isEmpty()) {
			int cur = dq.poll();
			visit[cur] = true;
			tmpList = list.get(cur); // 현 정점에 인접한 edge 정보 저장

			for (Edge tmp : tmpList) {
				if (!visit[tmp.v]) {
					pq.add(tmp);
				}
			}

			while (!pq.isEmpty()) {
				Edge curEdge = pq.poll();
				if (!visit[curEdge.v]) {
					visit[curEdge.v] = true;
					res += curEdge.c;
					dq.add(curEdge.v);
					break;
				}
			}
		}
		
		System.out.println(res);
	}

}
