import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1907_D5_모래성쌓기 {

	public static int[][] map;
	public static int[][] damage;
	public static int N, M;
	public static int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0 };
	public static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			damage = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				String str = br.readLine();
				for (int j = 0; j < map[i].length; j++) {
					if (str.charAt(j) == '.') {
						map[i][j] = -1;
					} else {
						map[i][j] = str.charAt(j) - '0';
					}
				}
			}

			int ans = pado();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static int pado() {
		Queue<int[]> q = new LinkedList<int[]>();
		// 주변에 모래가 있지 않는 칸 세기 (외곽은 제외하기)
		for (int i = 1; i < damage.length - 1; i++) {
			for (int j = 1; j < damage[i].length - 1; j++) {
				damage[i][j] = checkSand(i, j); // 각 칸에 주위 모래 개수 넣기
				if (map[i][j] != -1 && map[i][j] <= damage[i][j]) {
					q.add(new int[] { i, j });
				}
			}
		}

		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int[] data = q.poll();
				for (int i = 0; i < 8; i++) {
					int nx = data[0] + dx[i];
					int ny = data[1] + dy[i];
					// 현재 사라질 모래성 기준으로 없어지지 않는 모래성의 주변 모래 개수 증가시키기기
					if (map[nx][ny] > damage[nx][ny]) {
						damage[nx][ny]++; // 사라지는 모래성 -> 빈공간이 되므로 하나씩 증가
					} else {
						continue;
					}
					if (map[nx][ny] != -1 && map[nx][ny] <= damage[nx][ny]) { // 8방향 모래가 map값보다 작거나 같으면 큐 추가
						q.add(new int[] { nx, ny });
					}
				}
			}
			cnt++;
		}
		return cnt;
	}

	public static int checkSand(int x, int y) {
		int empty = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (map[nx][ny] == -1) {
				empty++;
			}
		}
		return empty;
	}
}
