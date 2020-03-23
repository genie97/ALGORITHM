import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973_직사각형탈출 {
	static int N, M, W, H, Sr, Sc, Fr, Fc, map[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < M; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Sr = Integer.parseInt(st.nextToken()) - 1;
		Sc = Integer.parseInt(st.nextToken()) - 1;
		Fr = Integer.parseInt(st.nextToken()) - 1;
		Fc = Integer.parseInt(st.nextToken()) - 1;
		System.out.println(bfs());

	}

	static int bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit = new boolean[N][M];
		// 사각형의 시작점 방문
		visit[Sr][Sc] = true;
		q.add(new int[] { Sr, Sc, 0 });

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int cnt = q.peek()[2];
			q.poll();
			if (x == Fr && y == Fc) { // 도착
				return cnt;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) // 기본 범위 체크
					continue;
				if (visit[nx][ny]) // 방문 체크
					continue; 
				if (!canMove(nx, ny, i)) // 움직일 수 있는지 체크 (방문 배열을 넘기는 이유: 이중 for문으로 다 확인하면 1000 by 1000이라서 시간초과 발생
					continue;
				visit[nx][ny] = true;
				q.add(new int[] { nx, ny, cnt + 1 });
			}
		}
		return -1;
	}

	static boolean canMove(int x, int y, int dir) {
		switch (dir) { // 상 하 좌 우: 현재 기준을 안보는 이유는 사각형이 존재했던 위치이므로 무조건 0임이 보장됨
		case 0:
			for (int i = y; i < y + W; i++) { // 위쪽을 볼때는, 현재 기준으로 가로배열을 학인
				if (map[x][i] == 1)
					return false;
			}
			break;
		case 1:
			int nx = x + H - 1;  // 아래쪽을 볼때는, 현재 기준 가장 아래쪽 위치를 잡고 가로 배열 확인
			if (nx >= N)
				return false;
			for (int i = y; i < y + W; i++) { 
				if (map[nx][i] == 1)
					return false;
			}
			break;
		case 2:
			for (int i = x; i < x + H; i++) { // 왼쪽을 볼때는, 현재 기준으로 세로배열을 학인
				if (map[i][y] == 1)
					return false;
			}
			break;
		case 3:
			int ny = y + W - 1;
			if (ny >= M)
				return false;
			for (int i = x; i < x + H; i++) { // 오른쪽을 볼때는, 현재 기준으로 가장 오른쪽 위치에서 세로배열을 학인
				if (map[i][ny] == 1)
					return false;
			}
			break;
		}
		return true;
	}
}
