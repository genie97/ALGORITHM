import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA7396_D5_종구의딸이름짓기 {
	public static int N;
	public static int M;
	public static char[][] map;
	public static boolean[][] visit;
	public static int[] dx = { 1, 0 };
	public static int[] dy = { 0, 1 };
	public static StringBuilder sb = new StringBuilder();

	public static class Name {
		int x, y;
		String s;

		public Name(int x, int y, String s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			sb.append("#").append(tc).append(" ");
			visit = new boolean[N][M];
			bfs();
		}
		System.out.println(sb);
	}

	public static void bfs() {
		PriorityQueue<Name> q = new PriorityQueue<>(new Comparator<Name>() {
			@Override
			public int compare(Name o1, Name o2) {
				return o1.s.compareTo(o2.s);
			}
		});
		q.add(new Name(0, 0, map[0][0] + ""));
		visit[0][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				Name n = q.poll();

				if (n.x == N - 1 && n.y == M - 1) {
					sb.append(n.s).append("\n");
					return;
				}

				for (int i = 0; i < 2; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if (nx >= N || ny >= M)
						continue;
					if (visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					q.add(new Name(nx, ny, n.s + map[nx][ny]));
				}
			}
		}
	}
}
