import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932_정수삼각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 현재 층에서 선택한 부분의 오른쪽 혹은 왼쪽만 선택 가능
		int[][] map = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] memo = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			memo[N-1][i] = map[N-1][i];
		}
	
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				memo[i - 1][j] = Math.max(map[i - 1][j] + memo[i][j], map[i - 1][j] + memo[i][j + 1]);
			}
		}

		System.out.println(memo[0][0]);
	}
}
