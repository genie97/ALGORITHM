import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16194_카드구매하기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < memo.length; i++) {
			memo[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					memo[i] = Math.min(memo[i], memo[j] * (i / j));
				}
				memo[i] = Math.min(memo[j] + memo[i - j], memo[i]);
			}
		}
		System.out.println(memo[N]);
	}
}
