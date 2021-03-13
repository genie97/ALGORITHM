import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8658_D3_Summation {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int maxv = Integer.MIN_VALUE;
			int minv = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 10; i++) {
				String num = st.nextToken();
				int sum = 0;
				for (int j = 0; j < num.length(); j++) {
					sum += (num.charAt(j) - '0');
				}
				maxv = Math.max(maxv, sum);
				minv = Math.min(minv, sum);
			}
			sb.append(maxv).append(' ').append(minv).append('\n');
		}
		System.out.println(sb);
	}
}
