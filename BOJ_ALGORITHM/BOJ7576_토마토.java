import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {

	private static int M;
	private static int N;
	public static int[][] map;
	public static Queue<int[]> tomato;
	public static int tomatoCnt; // 익어야 하는 토마토 개수

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static boolean[][] visit;
	public static int minDay;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		tomato = new LinkedList<int[]>();
		tomatoCnt = 0;
		minDay = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					tomato.offer(new int[] { i, j });
					visit[i][j] = true;
				} else if (map[i][j] == 0) {
					tomatoCnt++;
				}
			}
		}
		if (tomatoCnt == 0) { // 저장할 때부터 이미 모든 토마토가 익어있다면?
			System.out.println(0);
		} else { // 토마토를 익혀보자는!
			bfs();
			System.out.println(minDay-1);
		}
	}

	public static void bfs() {
		int cnt = 0;
		while (!tomato.isEmpty()) {
			int size = tomato.size();
			for (int sz = 0; sz < size; sz++) { // 현재 같은 시간대 들어온 토마토가 같이 익어감
				int x = tomato.peek()[0];
				int y = tomato.peek()[1];
				tomato.poll();
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (map[nx][ny] == -1)
						continue;
					if (visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					tomatoCnt--;
					map[nx][ny] = 1;
					tomato.offer(new int[] { nx, ny });
				}
			}
			cnt++;
		}
		if (tomatoCnt == 0) {
			minDay = Math.min(minDay, cnt);
		} else {
			minDay = 0;
		}
	}

}
