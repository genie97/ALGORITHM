import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6913_D3_동철이의프로그래밍대회 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // N명
			int M = Integer.parseInt(st.nextToken()); // M문제
			// i번사람이 j번 문제 풀었다 1 못풀었다 0
			// 가장 맞춘 사람의 문제 수, 명수
			int[] cnt = new int[N];
			int maxv = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						cnt[i]++;
					}
				}
				maxv = Math.max(maxv, cnt[i]);
			}
			int people = 0;
			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == maxv) {
					people++;
				}
			}
			sb.append(people).append(' ').append(maxv).append('\n');
		}
		System.out.println(sb);
	}
}
