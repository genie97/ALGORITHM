import java.util.*;
import java.io.*;

public class BOJ10434_행복한소수 {
	static boolean[] prime;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		prime = new boolean[10001];
		prime[1] = true; // 합성수이다
		for (int i = 4; i < prime.length; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					prime[i] = true;
				}
			}
		}
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			boolean isPrime = true;
			if (isHappyNumber(n)) {// 행복한 수 일 때, 소수인지 확인
				if (!prime[n])
					isPrime = false;
			}
			sb.append(t).append(' ').append(n).append(' ').append(!isPrime ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}

	static boolean isHappyNumber(int n) {
		int sum = 0;
		Set<Integer> s = new HashSet<>();
		while (sum != 1) {
			// 제곱수를 만든다
			sum = 0;
			String powNum = n + "";
			// 합을 구한다
			for (int i = 0; i < powNum.length(); i++) {
				int num = (powNum.charAt(i) - '0');
				sum += num * num;
			}
			if (s.contains(sum))
				return false;
			s.add(sum);
			n = sum;
		}
		return true;
	}
}
