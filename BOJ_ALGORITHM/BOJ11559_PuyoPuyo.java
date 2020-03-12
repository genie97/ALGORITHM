import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {

	public static char[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static boolean[][] visit;
	public static ArrayList<int[]> removeList;
	public static int ans;
	public static int cnt;
	public static Queue<int[]> q;
	public static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		removeList = new ArrayList<>();
		q = new LinkedList<int[]>();
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] != '.') {
					q.add(new int[] { i, j });
				}
			}
		}
		ans = 0;
		flag = false;
		bfs();
		System.out.println(ans);
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int x = q.peek()[0];
				int y = q.peek()[1];
				q.poll();
				cnt = 0;
				removeList.clear();
				if (map[x][y] == '.')
					continue;
				visit = new boolean[12][6];
				dfs(x, y, map[x][y]);
				if (cnt >= 4) {
					pop();
					flag = true;
				}
			}
			if (!flag)
				break;
			ans++;
			down();
			in();
			flag = false;
		}
	}

	public static void dfs(int x, int y, char color) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6)
				continue;
			if (visit[nx][ny])
				continue;
			if (map[nx][ny] != color)
				continue;
			visit[nx][ny] = true;
			removeList.add(new int[] { nx, ny });
			cnt++;
			dfs(nx, ny, color);
		}
	}

	public static void pop() {
		for (int i = 0; i < removeList.size(); i++) {
			int x = removeList.get(i)[0];
			int y = removeList.get(i)[1];
			map[x][y] = '.';
		}
	}

	private static void down() {
		for (int i = map.length - 2; i >= 0; i--) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '.')
					continue;
				int x = i;
				while (x + 1 < 12 && map[x + 1][j] == '.') {
					x++;
				}
				if (x != i) {
					map[x][j] = map[i][j];
					map[i][j] = '.';
				}
			}
		}
	}

	private static void in() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != '.') {
					q.add(new int[] { i, j });
				}
			}
		}
	}

}
