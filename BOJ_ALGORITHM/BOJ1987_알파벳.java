import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987_알파벳 {

	public static char[][] map;
	public static boolean[] alpha;
	public static int R;
	public static int C;
	public static int maxCnt;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		alpha = new boolean[26];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		alpha[map[0][0] - 'A'] = true;
		maxCnt = Integer.MIN_VALUE;
		backTracking(0, 0, 1);
		System.out.println(maxCnt);
	}

	public static void backTracking(int x, int y, int cnt) {
		if (cnt > maxCnt)
			maxCnt = cnt;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= R || ny >= C)
				continue;
			char c = map[nx][ny];
			if (alpha[c - 'A'])
				continue;
			alpha[c - 'A'] = true;
			backTracking(nx, ny, cnt + 1);
			alpha[c - 'A'] = false;
		}
	}

}
