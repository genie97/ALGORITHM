import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파스칼의 삼각형을 이용하자
// nCr = n-1Cr-1 + n-1Cr
public class BOJ11051_이항계수2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int MOD = 10007;
		long[][] p_triangle = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j || j == 0) {
					p_triangle[i][j] = 1;
				} else {
					p_triangle[i][j] = (p_triangle[i - 1][j - 1] + p_triangle[i - 1][j]) % MOD;
				}
			}
		}
		System.out.println(p_triangle[N][K]);
	}

}
