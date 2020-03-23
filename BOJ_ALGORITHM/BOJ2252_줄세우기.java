import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252_줄세우기 {
	static int N, M;
	static int[] inDegree;
	static ArrayList<ArrayList<Integer>> adj;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N + 1];
		// 인접행렬 초기화
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj.get(from).add(to);
			inDegree[to]++;
		}
		topologySort();
	}

	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				q.add(i);
		}

		for (int i = 1; i <= N; i++) {
			if (q.isEmpty())
				break;
			int v = q.poll();
			sb.append(v).append(' ');
			for (int j = 0; j < adj.get(v).size(); j++) {
				int u = adj.get(v).get(j);
				if (--inDegree[u] == 0) {
					q.add(u);
				}
			}
		}
		System.out.println(sb);
	}

}
