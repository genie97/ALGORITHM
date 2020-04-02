import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA6719_D4_성수의프로그래밍강좌시청 {
	// S(K) = num(K) / 2^K의 합....
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			num = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(num); // 큰 순으로 k개 선택
			float sum = 0f;
			for (int i = N - 1, j = 1; i >= (N - K); i--, j++) {
				sum += ((num[i]) / Math.pow(2, j));
			}
			System.out.printf("#%d %.6f\n", tc, sum);
		}
	}

}
