import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707_이분그래프 {
	static ArrayList<ArrayList<Integer>> adj;
	static int V, E;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
        for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); 
			E = Integer.parseInt(st.nextToken()); 

            adj = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				adj.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				adj.get(to).add(from);
				adj.get(from).add(to);
			}

			System.out.println(bfs() ? "YES" : "NO");
		}
	}

    public static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		int[] visit = new int[V + 1]; 

        for (int i = 1; i <= V; i++) {
			if (visit[i] != 0) 
				continue;
			q.add(i);
			visit[i] = 1; 

            while (!q.isEmpty()) {
				int nv = q.poll();
				for (int j = 0; j < adj.get(nv).size(); j++) {
					int u = adj.get(nv).get(j);
					if (visit[u] == 0) { 
						q.add(u);
						
						if (visit[nv] != 1) {
							visit[u] = 1;
						} else {
							visit[u] = 2;
						}
					} else if (visit[nv] == visit[u]) { 
						return false;
					}
				}
			}
		}
		return true;
	}
}
