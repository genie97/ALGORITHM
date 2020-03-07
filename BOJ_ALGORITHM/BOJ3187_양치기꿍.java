import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3187_양치기꿍 {
	// 같은 울타리 영역안에 양 > 늑대 => 양이 늑대를 다 먹는다
	// . 빈공간 # 울타리 v늑대 k 양
	// 몇마리의 늑대와 양이 살아남는지 확인

	public static char[][] map;
	public static boolean[][] visit;

	public static int C;
	public static int R;
	public static int wolf, sheep;
	public static ArrayList<int[]> wPos;
	public static Queue<int[]> q;

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		wPos = new ArrayList<int[]>();
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'v') {
					wPos.add(new int[] { i, j });
					wolf++;
				}
				if (map[i][j] == 'k')
					sheep++;
			}
		}
		q = new LinkedList<int[]>();
		for (int i = 0; i < wPos.size(); i++) {
			q.clear();
			int x = wPos.get(i)[0];
			int y = wPos.get(i)[1];
			q.add(new int[] { x, y });
			if (visit[x][y])
				continue;
			visit[x][y] = true;
			bfs();
		}
		System.out.println(sheep + " " + wolf);
	}

	public static void bfs() {
		int wCnt = 1, sCnt = 0;

		while (!q.isEmpty()) {
			int[] data = q.poll();
			int x = data[0];
			int y = data[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if (visit[nx][ny])
					continue;
				if (map[nx][ny] == '#')
					continue;
				if (map[nx][ny] == 'k')
					sCnt++;
				if (map[nx][ny] == 'v')
					wCnt++;
				visit[nx][ny] = true;
				q.add(new int[] { nx, ny });
			}
		}

		// 어떤 공간에 양이 늑대보다 많은 경우
		if (wCnt < sCnt) {
			wolf -= wCnt;
		} else {
			sheep -= sCnt;
		}
	}
}
