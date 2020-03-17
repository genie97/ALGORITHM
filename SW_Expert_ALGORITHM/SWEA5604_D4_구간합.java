import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5604_D4_구간합 {

	public static long A, B, ans;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0L;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			long n = 1; // 자릿수
			while (true) {
				bound(n);
				if (A == B) {
					break;
				}
				A /= 10;
				B /= 10;
				ans += 45 * n * (B - A + 1);
				n *= 10;
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	public static void bound(long n) {
		while (A % 10 != 0) {
			ans+=cal(A, n);
			if (A == B)
				return;
			A++;
		}
		while (B % 10 != 9) {
			ans+=cal(B, n);
			if (A == B)
				return;
			B--;
		}

	}

	public static long cal(long num, long n) {
		long digitSum = 0;
		while (num > 0) {
			digitSum+= (num % 10 * n);
			num /= 10;
		}
		return digitSum;
	}
}
