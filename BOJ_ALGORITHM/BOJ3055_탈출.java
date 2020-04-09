import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_탈출 {
	static class Pos {
		int x;
		int y;
		int cnt;
		boolean isWater;

		public Pos(int x, int y, int cnt, boolean isWater) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isWater = isWater;
		}
	}

	static Queue<Pos> queue;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		queue = new LinkedList<>();
		Pos hedgehog = null;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S')
					hedgehog = new Pos(i, j, 0, false);
				else if (map[i][j] == '*')
					queue.add(new Pos(i, j, 0, true));
			}
		}
		queue.add(hedgehog);
		int cnt = bfs();
		System.out.println(cnt == -1 ? "KAKTUS" : cnt);
	}

	static int bfs() {
		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = p.x + dx[dir];
				int ny = p.y + dy[dir];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue;
				if (p.isWater) {
					if (map[nx][ny] == 'S' || map[nx][ny] == '.') { // 고슴도치 있는 자리 혹은 빈 곳
						map[nx][ny] = '*';
						queue.add(new Pos(nx, ny, p.cnt + 1, true));
					}
				} else {
					if (map[nx][ny] == 'D') { // 물이 아닌데 D에 도착했다면 break
						return p.cnt + 1;
					} else if (map[nx][ny] == '.') { // 빈 곳 만 이동가능
						map[nx][ny] = 'S';
						queue.add(new Pos(nx, ny, p.cnt + 1, false));
					}
				}
			}
		}
		return -1;
	}
}
