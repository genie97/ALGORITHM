import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA10570_D3_제곱팰린드롬수 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		boolean[] isPalindrome = new boolean[10001];

		for (int i = 1; i < 32; i++) {
			if (palindrome(i) && palindrome(i * i)) {
				isPalindrome[i * i] = true;
			}

		}
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int ans = 0;
			for (int i = A; i <= B; i++) {
				if (isPalindrome[i])
					ans++;
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);

	}

	static boolean palindrome(int n) {
		String str = n + "";
		int len = str.length();
		for (int i = 0; i < len / 2; i++) {
			if (str.charAt(i) != str.charAt(len - i - 1))
				return false;
		}
		return true;
	}
}
