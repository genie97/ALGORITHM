import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11048_이동하기 {
	static int[] dx = { 1, 0, 1 };
	static int[] dy = { 0, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		int[][] memo = new int[N][M];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		memo[0][0] = arr[0][0];
		for (int i = 1; i < memo[0].length; i++) {
			memo[0][i] = memo[0][i - 1] + arr[0][i];
		}

		for (int i = 1; i < memo.length; i++) {
			memo[i][0] = memo[i - 1][0] + arr[i][0];
		}

		for (int i = 1; i < memo.length; i++) {
			for (int j = 1; j < memo[i].length; j++) {
				// 세 방향을 확인안해도 되는 이유는 오른쪽 -> 아래 이동이 대각선 이동과 동일한 효과이기 때문이다!
				// memo[i][j] = arr[i][j] + Math.max(memo[i - 1][j], Math.max(memo[i][j - 1], memo[i - 1][j - 1]));
				memo[i][j] = arr[i][j] + Math.max(memo[i - 1][j], memo[i][j - 1]);

			}
		}
		System.out.println(memo[N - 1][M - 1]);

	}

}
