import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// pow(a,n)
// n이 1일 때, a
// n이 짝수일 때, pow(a, n/2) 
// 이론 a^4 = (a^2)^2
// n이 홀수일 때, pow(a, n/2) * a
public class BOJ1629_곱셈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		long ans = 1;
		long mul = A % C;
		while (B > 0) {
			if (B % 2 == 1) {
				ans *= mul;
				ans %= C;
			}
			mul = (mul % C) * (mul % C) % C;
			B /= 2;
		}
		System.out.println(ans);
	}
}
