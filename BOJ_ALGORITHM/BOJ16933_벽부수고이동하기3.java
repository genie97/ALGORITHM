import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ16933_벽부수고이동하기3 {

	public static int N;
	public static int M;
	public static int K;
	public static int ans;
	public static int[][] map;
	public static boolean[][][][] visit;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M][11][2]; // 현재 위치, 벽을 부신 횟수, 낮인지 밤인지
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		ans = Integer.MAX_VALUE;
		bfs(0, 0, 0, 0, 1); // 시작점 x, y, 벽을 부신횟수, cnt, 지금의 시각(낮:0/밤:1)
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	public static void bfs(int x, int y, int b, int day, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { x, y, b, day, cnt });
		visit[x][y][b][day] = true;
		while (!q.isEmpty()) {
			int cx = q.peek()[0];
			int cy = q.peek()[1];
			int cb = q.peek()[2];
			int cDay = q.peek()[3];
			int cCnt = q.peek()[4];
			q.poll();
			if (ans < cCnt)
				continue;
			if (cx == N - 1 && cy == M - 1) {
				if (ans > cCnt) {
					ans = cCnt;
				}
			}
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (map[nx][ny] == 0) { // 만약 벽이 없으면 그냥 지나가고 밤->낮 / 낮->밤
					if (visit[nx][ny][cb][(cDay ^ 1)]) // 방문했으면 넘김
						continue;
					visit[nx][ny][cb][(cDay ^ 1)] = true;
					q.add(new int[] { nx, ny, cb, (cDay ^ 1), cCnt + 1 });
				} else { // 벽을 만났을 때는? 벽을 부순 회수를 확인하기
					if (cb < K && cDay == 0) { // 벽이 k회수보다 적게 부셔졌고, 낮일때만 부수고 지나갈 수 있다.
						if (visit[nx][ny][cb + 1][(cDay ^ 1)]) // 방문했으면 넘김
							continue;
						visit[nx][ny][cb + 1][(cDay ^ 1)] = true;
						q.add(new int[] { nx, ny, cb + 1, (cDay ^ 1), cCnt + 1 });
					} else { // 이게 불가능 일때는 한번 머물러보자 대신 재방문 구역이면 못가는 곳이다
						if (visit[cx][cy][cb][(cDay ^ 1)]) // 방문했으면 넘김
							continue;
						visit[cx][cy][cb][(cDay ^ 1)] = true;
						q.add(new int[] { cx, cy, cb, (cDay ^ 1), cCnt + 1 });
					}
				}
			}
		}
	}

}
