import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1644_소수의연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] prime = new boolean[N + 1]; // 소수 확인용

		prime[1] = true;

		// 1. 소수와 합성수를 구분한다
		for (int i = 2; i <= (int) (Math.sqrt(N)); i++) {
			if (prime[i])
				continue;
			for (int j = i + i; j <= N; j += i) {
				prime[j] = true;
			}
		}

		// 2. 소수 리스트를 확인한다
		ArrayList<Integer> prime_list = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (prime[i])
				continue;
			prime_list.add(i);
		}

		// 3. 슬라이딩 윈도우로 합이 N이 되는 경우를 찾는다
		int start = 0;
		int end = 0;
		int sum = 0;
		int ans = 0;
		while (start < prime_list.size()) {
			if (sum >= N || end == prime_list.size()) {
				if (sum == N) {
					ans++;
				}
				sum -= prime_list.get(start);
				start++;
			} else {
				sum += prime_list.get(end);
				end++;
			}
		}
		System.out.println(ans);
	}
}
