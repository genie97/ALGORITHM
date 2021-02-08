import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11653_소인수분해 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] prime = new boolean[N + 1];
		prime[1] = true;
		for (int i = 2; i < prime.length; i++) {
			if (prime[i])
				continue;
			for (int j = i + i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		int q = 2;
		while (N != 1 && q <= N) {
			if (prime[q]) {
				q++;
				continue;
			}
			if (N % q == 0) {
				sb.append(q).append('\n');
				N /= q;
			} else {
				q++;
			}
		}
		System.out.println(sb);

	}
}
