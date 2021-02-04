import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929_소수구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// M이상 N이하의 소수
		boolean[] prime = new boolean[N + 1];
		prime[1] = true;

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			if (prime[i])
				continue;
			for (int j = i + i; j <= N; j += i) {
				prime[j] = true;
			}
		}

		for (int i = M; i <= N; i++) {
			if (prime[i])
				continue;
			sb.append(i).append('\n');
		}
		System.out.println(sb);
	}
}
