import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17244_아맞다우산 {
	static int N, M;
	static char[][] map;
	static int sx, sy;
	static int things; // 물건 개수
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M][N];

		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					sx = i;
					sy = j;
				} else if (map[i][j] == 'X') {
					map[i][j] = (char) (things + '0'); // 물건 0~4까지 번호 매기기
					things++;
				}
			}
		}
		int res = bfs();
		System.out.println(res);
	}

	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visit = new boolean[M][N][1 << things]; // 해당위치, 물건 몇개 찾았는지 (순열)
		int t = 0;
		q.add(new int[] { sx, sy, t });
		visit[sx][sy][t] = true;
		int move = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int x = q.peek()[0];
				int y = q.peek()[1];
				t = q.peek()[2];
				q.poll();

				if (map[x][y] == 'E' && t == (1 << things) - 1) { // 물건을 다 찾아서 도착
					return move;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int nt = t;
					if (nx < 0 || ny < 0 || nx >= M || ny >= N)
						continue;
					if (visit[nx][ny][nt] || map[nx][ny] == '#')
						continue;
					if ('0' <= map[nx][ny] && map[nx][ny] <= '4') {
						nt |= (1 << (map[nx][ny] - '0'));
					}
					visit[nx][ny][nt] = true;
					q.add(new int[] { nx, ny, nt });
				}
			}
			move++;
		}
		return move;
	}
}
