import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638_치즈 {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<int[]> q;
	static boolean inner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < map[i].length; j++, index += 2) {
				map[i][j] = str.charAt(index) - '0';
			}
		}
//		printMap();
		q = new LinkedList<int[]>();
		init();
		int res = melting();
		System.out.println(res);
	}

	static int melting() {
		int time = 0; // 처음에 녹을 애들을 넣고 시작하니까 time은 1부터 시작
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				map[cur[0]][cur[1]] = 0;
			}
			init();
			time++;
		}
		return time;
	}

	static void init() {
		boolean[][] visit = new boolean[N][M];
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[i].length - 1; j++) {
				if (map[i][j] == 0)
					continue;
				if (MeetAir(i, j)) {
					q.add(new int[] { i, j });
					visit[i][j] = true;
				}
			}
		}
	}

	static boolean MeetAir(int x, int y) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (map[nx][ny] == 1)
				continue;
			inner = true;
			boolean[][] check = new boolean[N][M];
			outRange(nx, ny, check);
			if (!inner) {
				cnt++;
			}
		}
		if (cnt >= 2)
			return true;
		else
			return false;
	}
//	static void printMap() {
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//	}

	static void outRange(int x, int y, boolean[][] check) {
		check[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				inner = false;
				return;
			}
			if (map[nx][ny] == 1 || check[nx][ny])
				continue;
			outRange(nx, ny, check);
		}
	}
}
