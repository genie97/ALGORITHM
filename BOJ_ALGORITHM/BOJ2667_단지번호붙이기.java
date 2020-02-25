import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667_단지번호붙이기 {
	public static int N;
	public static int[][] map;
	public static ArrayList<Integer> ans;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ArrayList<int[]> homeList = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] == 1) {
					homeList.add(new int[] { i, j });
				}
			}
		}
		ans = new ArrayList<Integer>();
		for (int i = 0; i < homeList.size(); i++) {
			int x = homeList.get(i)[0];
			int y = homeList.get(i)[1];
			if (map[x][y] == 0)
				continue;
			cnt = 0;
			int area = dfs(x, y);
			if (area == 0) // 자기 영역 체크 못하는 경우
				area = 1;
			ans.add(area);
		}
		Collections.sort(ans);
		System.out.println(ans.size());
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}

	private static int dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (map[nx][ny] == 0)
				continue;
			map[nx][ny] = 0;
			cnt++;
			dfs(nx, ny);
		}
		return cnt;
	}
}
