import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8382_D4_방향전환 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int rowD = Math.abs(x1 - x2);
			int colD = Math.abs(y1 - y2);
			int max = Math.max(rowD, colD);
			int min = Math.min(rowD, colD);

			int ans = 0;
			ans += min * 2;
			max -= min;
			ans += 2 * max - (max % 2);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}

/** BFS 방식
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA8382_D4_방향전환 {

	// 가로 이동 [0,1] 세로 이동[2,3]
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int x1, y1, x2, y2;
	public static boolean[][][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;
			visit = new boolean[201][201][2];
			int ans = bfs();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<int[]>(); // (x좌표, y좌표, 가로/세로 방향(0/1), cnt, visit)

		q.add(new int[] { x1, y1, 0, 0 });
		visit[x1][y1][0] = true;
		q.add(new int[] { x1, y1, 1, 0 });
		visit[x1][y1][1] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int[] data = q.poll();
				if (data[0] == x2 && data[1] == y2) {
					return data[3];
				}
				if (data[2] == 0) { // 가로
					for (int i = 0; i < 2; i++) {
						int nx = data[0] + dx[i];
						int ny = data[1] + dy[i];
						if (nx < 0 || ny < 0 || nx > 200 || ny > 200)
							continue;
						if (visit[nx][ny][1])
							continue;
						visit[nx][ny][1] = true;
						q.add(new int[] { nx, ny, 1, data[3] + 1 });
					}
				} else { // 세로
					for (int i = 2; i < 4; i++) {
						int nx = data[0] + dx[i];
						int ny = data[1] + dy[i];
						if (nx < 0 || ny < 0 || nx > 200 || ny > 200)
							continue;
						if (visit[nx][ny][0])
							continue;
						visit[nx][ny][0] = true;
						q.add(new int[] { nx, ny, 0, data[3] + 1 });
					}
				}
			}
		}
		return -1;
	}

}
*/
