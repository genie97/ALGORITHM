import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607_D3_조합 {
	static final int MOD = 1234567891;
	static long[] facto = new long[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		facto[0] = facto[1] = 1;
		for (int i = 2; i < 1000001; i++) {
			facto[i] = (i * facto[i - 1]) % MOD; // 각 팩토리얼 값을 MOD로 나눈 값을 다 저장
		}

		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			// nCr = {n! * r! * (n-r)!^(1234567891-2)}%1234567891
			long ans = (facto[n] * cal((facto[r] * facto[n-r]) % MOD, MOD - 2)) % MOD;
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	public static long cal(long A, int exp) {
		if (exp == 1) // 지수가 1이면 그대로 리턴
			return A;
		long B = cal(A, exp / 2);
		if (exp % 2 == 0) { // 짝수 지수?
			return (B * B) % MOD;
		} else { // 홀수 지수
			return (((B * B) % MOD) * A) % MOD;
		}
	}

}
