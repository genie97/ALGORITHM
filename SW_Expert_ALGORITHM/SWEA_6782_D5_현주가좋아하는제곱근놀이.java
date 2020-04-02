import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_6782_D5_현주가좋아하는제곱근놀이 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			int ans = 0;
			sb.append('#').append(tc).append(' ');
			while (true) {
				if (N == 2) {
					sb.append(ans).append('\n');
					break;
				}
				double sqrtV = Math.sqrt(N);
				int sqrtZ = (int) sqrtV;
				if (sqrtV == sqrtZ) { // 제곱근이 정수이다.
					N = sqrtZ;
				} else {
					ans += (sqrtZ + 1) * (sqrtZ + 1) - N;
					N = sqrtZ + 1;
				}
				ans++;
			}
		}
		System.out.println(sb);
	}
}
