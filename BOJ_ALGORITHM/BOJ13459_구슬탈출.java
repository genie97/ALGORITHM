import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 큐에 빨간 구슬의 좌표와 파란 구슬의 좌표를 넣는다
// 방문체크는  visit[rx][ry][bx][by]
// 구슬을 굴릴 때, 다음 위치가 벽(#)인지 구멍(O)인지 확인한다
// 구슬의 위치가 벽이라면 앞으로 가지 못한다
// 구슬의 위치가 구멍이라면 현재 구슬의 색을 판별한다
// 만약 파란 구슬이 구멍에 위치한다면 무시하고, 빨간 구슬이 구멍에 위치한다면 1을 출력한다
// 구슬을 굴리면서, 빨간 구슬의 이동거리와 파란 구슬의 이동거리를 카운트 한다
// 구슬을 굴린 후, 두 구슬의 위치가 같다면 이동거리 비교를 통해 겹치지 않게 처리한다
// 만약 구슬이 겹쳤다면, 굴릴 때 카운트했던 이동거리가 더 긴 구슬의 위치를 한칸 이전으로 되돌린다

public class BOJ13459_구슬탈출 {

	public static int N;
	public static int M;
	public static char[][] map;
	public static boolean[][][][] visit;
	public static Marble mb;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static boolean ans;

	public static class Marble {
		int rx, ry, bx, by;

		Marble(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		mb = new Marble(0, 0, 0, 0);

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					mb.rx = i;
					mb.ry = j;
				} else if (map[i][j] == 'B') {
					mb.bx = i;
					mb.by = j;
				}
			}
		}
		ans = false;
		visit = new boolean[N][M][N][M]; // 빨간구슬 x,y 파란 구슬 x,y
		bfs();
		System.out.println(ans ? 1 : 0);
	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>(); // 빨간 구슬 좌표, 파란 구슬 좌표, 이동거리
		// 처음 위치 큐에 삽입
		q.add(new int[] { mb.rx, mb.ry, mb.bx, mb.by, 0 });
		visit[mb.rx][mb.ry][mb.bx][mb.by] = true;

		while (!q.isEmpty()) {
			int[] data = q.poll();
			int rx = data[0];
			int ry = data[1];
			int bx = data[2];
			int by = data[3];
			int dis = data[4];
			if (dis >= 10)
				break;
			for (int i = 0; i < 4; i++) {
				int nrx = rx;
				int nry = ry;
				int nbx = bx;
				int nby = by;
				int rdis = 0, bdis = 0; // 각 구슬 이동 횟수
				rdis = move(nrx, nry, rdis, i);
				nrx = rx + (dx[i] * rdis);
				nry = ry + (dy[i] * rdis);

				bdis = move(nbx, nby, bdis, i);
				nbx = bx + (dx[i] * bdis);
				nby = by + (dy[i] * bdis);

				if (map[nbx][nby] == 'O') // 파란 구슬이 구멍에 갔을 때는 무시
					continue;
				if (map[nrx][nry] == 'O') { // 삘간 구슬이 구멍에 갔을 때는 종료
					ans = true;
					return;
				}
				
				if (nbx == nrx && nby == nry) { // 두 구슬의 좌표가 동일하다면
					if (rdis > bdis) { // 적게 움직인 구슬이 앞에 있었다
						nrx -= dx[i];
						nry -= dy[i];
					} else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}

				if (visit[nrx][nry][nbx][nby]) // 이미 방문한 곳
					continue;
				visit[nrx][nry][nbx][nby] = true;
				q.add(new int[] { nrx, nry, nbx, nby, dis + 1 });
			}
		}
		ans = false;
	}

	public static int move(int x, int y, int dis, int dir) {
		while (true) {
			if (map[x + dx[dir]][y + dy[dir]] == '#' || map[x][y] == 'O')
				break;
			x += dx[dir];
			y += dy[dir];
			dis++; // 거리 증가
		}
		return dis;
	}
}
