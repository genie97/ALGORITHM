import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17090_미로탈출하기 {
	static int N, M;
	static int[][] map;
	static int[][] memo;
	static boolean flag;
	static int[] dx = { -1, 0, 1, 0 }; // U R D L
	static int[] dy = { 0, 1, 0, - 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		memo = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				switch(str.charAt(j)) {
				case 'U':
					map[i][j] = 0;
					break;
				case 'R':
					map[i][j] = 1;
					break;
				case 'D':
					map[i][j] = 2;
					break;
				case 'L':
					map[i][j] = 3;
					break;
				}
			}
		}
		
		// 확인하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(memo[i][j] == 0) {
					flag = false;
					dfs(i, j, map[i][j]);
				}	
			}
		}
		
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(memo[i][j] == 1) cnt++;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(memo[i]));
//		}
		System.out.println(cnt);
		
	}

	static void dfs(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
			flag = true;
			memo[x][y] = 1;
			return;
		}
		
		if(memo[nx][ny] != 0) {
			if(memo[nx][ny] == 1) {
				flag = true;
				memo[x][y] = 1;
			}
			return;
		}
	
		memo[nx][ny] = -1;
		dfs(nx, ny, map[nx][ny]);
		if(flag) memo[x][y] = 1;
	}

}
