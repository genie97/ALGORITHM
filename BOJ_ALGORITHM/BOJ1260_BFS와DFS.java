import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1260_BFS와DFS {
	static int N, M, V;
	static int[][] map;
	static boolean[] visited;
	static Queue<Integer> q;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}

		visited = new boolean[N + 1];
		visited[V] = true;
		DFS(V);
		System.out.println();
		visited = new boolean[N + 1];
		q = new LinkedList<>();
		q.offer(V);
		visited[V] = true;
		BFS(V);
	}

	public static void DFS(int V) {
		System.out.print(V + " ");
		for (int i = 1; i <= N; i++) {
			if (map[V][i] == 1 && !visited[i]) {
				visited[i] = true;
				DFS(i);
			}
		}
	}

	public static void BFS(int V) {
		while (!q.isEmpty()) {
			int sv = q.poll();
			System.out.print(sv + " ");
			for (int i = 1; i <= N; i++) {
				if (map[sv][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}

}
