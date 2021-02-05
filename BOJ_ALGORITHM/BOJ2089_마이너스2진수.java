import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2089_마이너스2진수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 10진법을 -> -2진수로
		// -2진법은 부호 없는 2진수로 표현됨
		// 홀수승 -부호 짝수승은 +부호
		StringBuilder sb = new StringBuilder();
		if (N == 0) {
			sb.append(0);
		} else {
			while (N != 0) {
				// 이진수의 비트가 되는 나머지 값은 절댓값으로 한다 (0 또는 1)
				int bit = Math.abs(N % -2);
				sb.append(bit);
				// 수 - 나머지 / 나누는 수 = 몫
				N = (N - bit) / -2;
			}
		}
		System.out.println(sb.reverse());
	}
}
