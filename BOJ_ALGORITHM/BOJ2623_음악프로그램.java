import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623_음악프로그램 {
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
			int n = Integer.parseInt(st.nextToken());
			int cur = 0, next = 0;
			for (int j = 0; j < n - 1; j++) {
				if (cur == 0) {
					cur = Integer.parseInt(st.nextToken());
				}
				next = Integer.parseInt(st.nextToken());
				adj.get(cur).add(next);
				inDegree[next]++;
				cur = next;
			}
		}
		topologySort();
	}

	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				q.add(i);
		}
		int vertex = 0;
		for (int i = 1; i <= N; i++) {
			if (q.isEmpty())
				break;
			int v = q.poll();
			vertex++;
			sb.append(v).append('\n');
			for (int j = 0; j < adj.get(v).size(); j++) {
				int u = adj.get(v).get(j);
				if (--inDegree[u] == 0) {
					q.add(u);
				}
			}
		}
		System.out.println((vertex == N) ? sb : 0);
	}

}
