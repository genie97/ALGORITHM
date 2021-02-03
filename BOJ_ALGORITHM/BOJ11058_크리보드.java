import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// A를 출력
// 전체선택
// 복사
// 붙여넣기
// N번 눌러서 출력된 A개수를 최대로 하는 프로그램

public class BOJ11058_크리보드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		// 1~6까지는 N번만큼 누르는게 최대
		for (int i = 1; i <= 6; i++) {
			dp[i] = i;
		}

		// 7번부터는
		// 3단계 전 * 2, 4단계전 * 3, 5단계전 *4 ...
		for (int i = 7; i <= N; i++) {
			for (int j = 3; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
			}
		}
		System.out.println(dp[N]);
	}
}
