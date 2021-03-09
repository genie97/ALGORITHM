import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1954_D2_달팽이숫자 {
	static int[][] arr;
	static int N;
	static int fx, fy;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append('#').append(tc).append('\n');
			arr = new int[N][N];
			fx = N / 2;
			fy = N % 2 == 0 ? N / 2 - 1 : N / 2;
			snail_moving(0, 0, 1, 1);

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					sb.append(arr[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	static void snail_moving(int x, int y, int n, int dir) {
		arr[x][y] = n;

		if (x == fx && y == fy)
			return;

		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		// 꺾이는 지점의 특징: 주어진 보드판을 넘어간 곳 / 이미 채워둔 칸인 경우
		if (!isIn(nx, ny) || arr[nx][ny] > 0) 
			dir = (dir + 1) % 4;

		snail_moving(x + dx[dir], y + dy[dir], n + 1, dir);
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
