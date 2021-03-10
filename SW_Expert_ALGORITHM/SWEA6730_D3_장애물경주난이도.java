import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6730_D3_장애물경주난이도 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int up_max = 0;
			int down_max = 0;

			int cur = Integer.parseInt(st.nextToken());

			for (int i = 1; i < N; i++) {
				int next = Integer.parseInt(st.nextToken());
				int diff = cur - next;
				if (diff < 0) {
					up_max = Math.max(up_max, Math.abs(diff));
				} else {
					down_max = Math.max(down_max, diff);
				}
				cur = next;
			}
			sb.append('#').append(tc).append(' ').append(up_max).append(' ').append(down_max).append('\n');
		}
		System.out.println(sb);
	}
}
