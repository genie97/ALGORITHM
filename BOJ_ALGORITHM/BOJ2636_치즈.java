import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636_치즈 {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static Queue<int[]> q; // 모든 치즈
	static Queue<int[]> cq; // 현 시점에 공기중에 있는 치즈

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < M; j++, idx += 2) {
				if (str.charAt(idx) == '1')
					map[i][j] = 0;
				else
					map[i][j] = -1;
			}
		}

		q = new LinkedList<>();
		cq = new LinkedList<>();

		q.add(new int[] { 0, 0 });
		map[0][0] = -2; // 방문

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				if (isIn(nx, ny)) {
					if (map[nx][ny] == -1) {
						map[nx][ny] = -2;
						q.add(new int[] { nx, ny });
					} else if (0 <= map[nx][ny]) {
						if (++map[nx][ny] >= 1) { // 공기중에 노출된 애들
							cq.add(new int[] { nx, ny });
							map[nx][ny] = -2;
						}
					}
				}
			}
		}

		int time = 0;
		int last = 0;
		while (!cq.isEmpty()) {
			int size = cq.size();
			if (size != 0)
				last = size;
			time++;
			while (size-- > 0) {
				int[] pos = cq.poll();
				for (int d = 0; d < 4; d++) {
					int nx = pos[0] + dx[d];
					int ny = pos[1] + dy[d];
					if (isIn(nx, ny)) {
						if (map[nx][ny] == -1) {
							map[nx][ny] = -2;
							q.add(new int[] { nx, ny });
						} else if (0 <= map[nx][ny]) {
							if (++map[nx][ny] >= 1) { // 공기중에 노출된 애들
								cq.add(new int[] { nx, ny });
								map[nx][ny] = -2;
							}
						}
					}
				}
			}
			while (!q.isEmpty()) {
				int[] pos = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = pos[0] + dx[d];
					int ny = pos[1] + dy[d];
					if (isIn(nx, ny)) {
						if (map[nx][ny] == -1) {
							map[nx][ny] = -2;
							q.add(new int[] { nx, ny });
						} else if (0 <= map[nx][ny]) {
							if (++map[nx][ny] >= 1) { // 공기중에 노출된 애들
								cq.add(new int[] { nx, ny });
								map[nx][ny] = -2;
							}
						}
					}
				}
			}
		}
		sb.append(time).append('\n').append(last).append('\n');
		System.out.println(sb);
	}

	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
