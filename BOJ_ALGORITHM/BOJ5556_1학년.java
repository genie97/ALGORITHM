import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5556_1학년 {
	// memo[1]번 {8}
	// memo[2]번 {memo[1][1] + 3, memo[1][1] - 3}
	// memo[3]번 {memo[2][1] + 2, memo[2][1] - 2, memo[2][2] + 2, memo[2][2] - 2}
	// 특정 자료 구조를 쓰면 최대 (2^63)-1이므로 메모리 초과가 발생할 수 있다
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 등식으로 만들어야하는 합
		int T = Integer.parseInt(st.nextToken());
		// dp[i][j] => i번째까지 연산했을 때, j라는 값이 나온 횟수라고 생각하자 (합은 0~20까지만 유효하다)
		long[][] dp = new long[N - 1][21];
		dp[0][arr[0]] = 1; // 0번째까지 연산했을 때 8이라는 값이 나온 횟수 1

		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < 21; j++) {
				if (dp[i - 1][j] == 0) // 해당 연산 값은 없는 경우
					continue;
				int p_num = j + arr[i];
				int m_num = j - arr[i];
				if (p_num <= 20) {
					dp[i][p_num] += dp[i - 1][j]; // 이전 j값이 나온 횟수를 더해준다!
				}
				if (m_num >= 0) {
					dp[i][m_num] += dp[i - 1][j];
				}

			}
		}
		System.out.println(dp[N - 2][T]);
	}
}
