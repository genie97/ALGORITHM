import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1자리 1 dp[1] = 1
// 2자리 10 dp[2] = 1
// 3자리 101 100 dp[3] = 2
// dp[3] = 2
// 4자리 1000 1001 1010 3 (1의자리, 2의자리로 만듬) + 1
// 5자리 100000 10101 10100 10010 10001 (3의자리, 2의자리, 1의자리) + 1
   
public class BOJ2193_이친수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N + 2];
		
		dp[1] = 1;
		dp[2] = 1;

		/*for (int i = 3; i <= N; i++) {
			for (int j = i - 2; j > 0; j--) {
				dp[i] += dp[j];
			}
			dp[i] += 1;
		}*/
		
		// 피보나치 성립 가능
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		System.out.println(dp[N]);

	}
}
