import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4012_AD_요리사 {

	public static int N;
	public static int[][] map;
	public static int[] A;
	public static int[] B;
	public static boolean[] check;
	public static int ans = 0;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			StringTokenizer st = null;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			A = new int[N / 2];
			B = new int[N / 2];
			check = new boolean[N + 1];
			ans = Integer.MAX_VALUE;
			comb(0, 1);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	public static void comb(int cnt, int idx) {
		// 156ms -> 146ms 최적화: ([1,2]-[3,4] / [3,4]-[1,2]와 같은 중복 경우 제거)
		if (idx > N) 
			return;
		
		if (cnt == N / 2) {
			int bIdx = 0;
			for (int i = 1; i < check.length; i++) {
				if (check[i])
					continue;
				B[bIdx++] = i;
			}
			int res = makeSynergy();
			if(ans > res) {
				ans = res;
			}
			return;
		}
		for (int i = idx; i <= N; i++) {
			A[cnt] = i;
			check[i] = true;
			comb(cnt + 1, i + 1);
			check[i] = false;
		}
	}

	public static int makeSynergy() {
		int aValue = 0;
		int bValue = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				aValue += (map[A[i]][A[j]] + map[A[j]][A[i]]);
			}
		}

		for (int i = 0; i < B.length; i++) {
			for (int j = i + 1; j < B.length; j++) {
				bValue += (map[B[i]][B[j]] + map[B[j]][B[i]]);
			}
		}
		return Math.abs(aValue - bValue);
	}
}
