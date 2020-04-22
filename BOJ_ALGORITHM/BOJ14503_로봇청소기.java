import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_로봇청소기 {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽 3인 경우 서쪽
		// 맵에서 0은 빈칸 1은 벽
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");

		// 첫 시작 좌표, 바라보는 방향
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		boolean[][] visit = new boolean[N][M];
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < M; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}

		int cleanPos = 0;

		while (true) {
			// 현 위치가 아직 청소가 안되었다면?
			if (!visit[r][c] && map[r][c] == 0) {
				visit[r][c] = true;
				cleanPos++;
			}
			int left = 0; // 현재 내가 바라보는 방향 기준 왼쪽
			int back = 0; // 내가 바라보고 있는 방향 기준 뒤쪽
			switch (d) {
			case 0: // 북
				left = 3;
				back = 2;
				break;
			case 1: // 동 
				left = 0;
				back = 3;
				break;
			case 2: // 남
				left = 1;
				back = 0;
				break;
			case 3: // 서
				left = 2;
				back = 1;
				break;
			}

			// 새 이동 방향
			int nr = r + dx[left];
			int nc = c + dy[left];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M)
				continue;
			if (!visit[nr][nc] && map[nr][nc] == 0) { // 왼쪽이 방문 안하고 비어 있는 곳이라면?
				r = nr;
				c = nc;
				d = left; // 방향을 왼쪽으로 전환한다
			} else { // 왼쪽 방향에 청소할 공간이 없다면? 바라보는 방향을 나의 왼쪽방향으로 전환해준다.
				int allClean = 0;
				for (int i = 0; i < 4; i++) { // 4방향의 청소 여부를 확인
					// 내가 서있는 방향 기준으로 4방향
					int ar = r + dx[i];
					int ac = c + dy[i];
					if (ar < 0 || ac < 0 || ar >= N || ac >= M) {
						allClean++;
						continue;
					}
					if (visit[ar][ac] || map[ar][ac] == 1)
						allClean++;
				}
				if (allClean == 4) { // 네 방향이 모두 청소가 되어있는 경우라면?
					// 후진 위치
					int rr = r + dx[back];
					int rc = c + dy[back];
					if (rr < 0 || rc < 0 || rr >= N || rc >= M)
						continue;
					if (map[rr][rc] == 0) { // 후진이 가능하다면
						// 위치 갱신
						r = rr;
						c = rc;
					} else { // 후진이 불가능하다면?
						break;
					}
				} else { // 4방향이 모두 청소가 된 게 아니라면 우선 왼쪽이 우선
					// 바라보는 방향만 갱신
					d = left;
				}
			}
		}
		System.out.println(cleanPos);
	}
}
