import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080_행렬 {
	static boolean[][] check;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] A = new int[N][M];
		int[][] B = new int[N][M];
		
		for (int i = 0; i < A.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] = str.charAt(j);
			}
		}

		int diff = 0;
		check = new boolean[N][M];
		
		for (int i = 0; i < B.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < B[i].length; j++) {
				B[i][j] = str.charAt(j);
				if (A[i][j] != B[i][j]) {
					check[i][j] = true;
					diff++;
				}
			}
		}

		if (diff == 0) {
			System.out.println(0);
		} else {
			System.out.println(greedy());
		}
	}

	static int greedy() {
		if (N < 3 || M < 3)
			return -1;

		int min = 0;

		for (int i = 0; i <= N - 3; i++) {
			for (int j = 0; j <= M - 3; j++) {
				if (i == N - 3 && !(check[i][j] == check[i + 1][j] && check[i + 1][j] == check[i + 2][j]))
					return -1;
				if (j == M - 3 && !(check[i][j] == check[i][j + 1] && check[i][j + 1] == check[i][j + 2]))
					return -1;

				if (check[i][j]) { // true면 flip을 한다
					flip(i, j);
					min++;
				}
			}
		}

		// 마지막 네모
		boolean flag = check[N - 3][M - 3];
		for (int i = N - 1; i > N - 3; i--) {
			for (int j = M - 1; j > M - 3; j--) {
				if (flag != check[i][j])
					return -1;
			}
		}
		
		return min;
	}

	static void flip(int i, int j) {
		for (int x = i; x < i + 3; x++) {
			for (int y = j; y < j + 3; y++) {
				check[x][y] = !check[x][y];
			}
		}
	}
}
