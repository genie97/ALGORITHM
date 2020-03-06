import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// n*n의 모양에 디저트 번호가 적혀있음, 4이상 20이하
// 디저트 번호는 1~100 
// 대각선 이동만 가능, 대각선으로 이동하면서 겹치지 않게 디저트를 먹어야함 
//  시작했던 정점으로 돌아올 수 있어야함 이동이 끝났을 때, 하나의 마름모 모양

public class SWEA2105_AD_디저트카페 {

	public static int[][] map;
	public static boolean[][] visit;
	public static boolean[] desert; // 디저트 먹은 건지 확인
	public static int N;
	public static int turn;
	public static int[] dx = { 1, 1, -1, -1 };
	public static int[] dy = { 1, -1, -1, 1 };
	public static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			desert = new boolean[101];
			visit = new boolean[N][N];
			ans = 0; // 먹을 수 없는 경우
			// 가장 자리는 무조건 꺽어서 돌아올 수 있는 경로가 없으니 제외하고 시작
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visit[i][j] = true;
					desert[map[i][j]] = true;
					dfs(i, j, 0, 1, i, j);
					visit[i][j] = false;
					desert[map[i][j]] = false;
				}
			}
			if (ans < 4)
				ans = -1;
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 방향이 3번 바뀌어야함
	public static void dfs(int cx, int cy, int dir, int cnt, int sx, int sy) {
		if (dir == 4)
			return;
		int nx = cx + dx[dir];
		int ny = cy + dy[dir];
		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			return;
		if (visit[nx][ny] || desert[map[nx][ny]]) { // 이미 방문했던 곳이 시작점인지?
			if (nx == sx && ny == sy) {
				if (ans < cnt) {
					ans = cnt;
				}
			}
			return;
		}
		desert[map[nx][ny]] = true;
		visit[nx][ny] = true;
		dfs(nx, ny, dir, cnt + 1, sx, sy); // 동일 방향 이동
		dfs(nx, ny, dir + 1, cnt + 1, sx, sy); // 동일방향 이동이 불가하다면 꺾기
		desert[map[nx][ny]] = false;
		visit[nx][ny] = false;

	}

}
