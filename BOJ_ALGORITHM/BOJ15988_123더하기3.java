import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 정해진 n의 크기가 크기때문에, 중간에 dp하면서 오버플로우 발생할 수 있음
// 이런 경우에는, 그냥 맘 편히 long타입으로 선언해서 dp하자!

public class BOJ15988_123더하기3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int q = 1000000009;
		long[] dp = new long[1000001];

		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			dp[i] %= q;
		}
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
