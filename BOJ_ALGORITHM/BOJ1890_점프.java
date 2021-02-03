import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1890_점프 {
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}

		// 최단거리 = 맨하튼 거리와 동일 -> 트리의 레벨은 2N만큼 나올 수 있음
		long[][][] memo = new long[(N + N)][N][N];
		memo[0][0][0] = 1; // 트리의 0번째, (0,0) 이동 가능
		for (int i = 0; i < memo.length - 1; i++) {
			for (int j = 0; j < memo[i].length; j++) {
				for (int k = 0; k < memo[i][j].length; k++) {
					if (map[j][k] == 0) {
						continue;
					}

					if (memo[i][j][k] == 0) // 해당 루트는 가는 길이 없다
						continue;
					// 가는 길이 있다면
					for (int dir = 0; dir < 2; dir++) {
						int nx = j + (map[j][k] * dx[dir]);
						int ny = k + (map[j][k] * dy[dir]);
						if (nx >= N || ny >= N)
							continue;
						memo[i + 1][nx][ny] += memo[i][j][k];
					}

				}
			}
		}
		long ans = 0;
		for (int i = 0; i < memo.length; i++) {
			ans += memo[i][N - 1][N - 1];
		}
		System.out.println(ans);
	}
}
