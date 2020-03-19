import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7194_D4_화섭이의미생물배양 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb.append('#').append(tc).append(' ');
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (b == 1) {
				int tmp = t - s;
				if (tmp % a == 0) {
					sb.append(tmp / a).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
				continue;
			}
			int ans = 0;
			while (s != t) {
				if (t % b == 0) {
					if (t / b < s) {
						ans++;
						t -= a;
					} else {
						ans++;
						t /= b;
					}
				} else {
					ans++;
					t -= a;
				}
				if (s > t) {
					ans = -1;
					break;
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
