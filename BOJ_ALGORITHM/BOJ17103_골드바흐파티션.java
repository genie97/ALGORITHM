import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17103_골드바흐파티션 {
	// 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다.
	// 짝수 n을 두 소수의 합으로 나타내는 표현을 골드바흐 파티션
	// 짝수 n이 주어졌을 때, 골드바흐 파티션의 개수를 구하자
	// 순서가 다른 것은 다른 파티션이다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean[] prime = new boolean[1000001];
		prime[1] = true;
		for (int i = 2; i < prime.length; i++) {
			if (prime[i])
				continue;
			for (int j = i + i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
		for (int t = 0; t < tc; t++) {
			int N = Integer.parseInt(br.readLine());
			int b = N / 2;
			int cnt = 0;
			while (b >= 2) {
				int a = N - b;
				if (!prime[a] && !prime[b])
					cnt++;
				b--;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}
