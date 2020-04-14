import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1251_D4_하나로_프림 {
	static class Edge implements Comparable<Edge> {
		int v;
		long c;

		public Edge(int v, long c) {
			super();
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.c, o.c);
		}
	}

	static int[] x, y;
	static ArrayList<ArrayList<Edge>> list;
	static int N;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x = new int[N];
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			y = new int[N];
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			double E = Double.parseDouble(br.readLine());

			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(new ArrayList<>());
			}

			makeEdge();
			visit = new boolean[N];
			long res = prim();
			sb.append(Math.round(res * E)).append('\n');
		}
		System.out.println(sb);
	}

	static long prim() {
		long res = 0L;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Deque<Integer> dq = new ArrayDeque<>();
		dq.add(0); // 처음 시작 지정

		ArrayList<Edge> tmpList = new ArrayList<>();
		while (!dq.isEmpty()) {
			int cur = dq.poll();
			visit[cur] = true; // 현재 정점 방문처리
			tmpList = list.get(cur); // 현재 정점의 인접 정점 가져오기
			for (Edge tmp : tmpList) { // 방문 안한 정점만 pq에 넣기
				if (!visit[tmp.v]) {
					pq.add(tmp);
				}
			}
			while (!pq.isEmpty()) {
				Edge curEdge = pq.poll(); // 현재 정점 edge
				if (!visit[curEdge.v]) { // 현재 정점이 방문 전
					visit[curEdge.v] = true;
					res+=curEdge.c;
					dq.add(curEdge.v); // 현 정점 갱신
					break;
				}
			}
		}
		return res;
	}

	static void makeEdge() {
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				long dist = cal(x[i], x[j], y[i], y[j]);
				list.get(i).add(new Edge(j, dist));
				list.get(j).add(new Edge(i, dist));
			}
		}
	}

	static long cal(int x1, int x2, int y1, int y2) {
		return (long) (Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}

}
