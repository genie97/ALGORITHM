import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// n일 동안 최대한 많은 상담
// Ti는 걸리는 시간
// Pi는 받을 수 있는 금액
// 최대 수익을 구하는 프로그램

public class BOJ15486_퇴사2 {
	static int[] memo;
	static int[][] schedule;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		schedule = new int[N + 1][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			schedule[i][0] = t;
			schedule[i][1] = p;
		}

		int ans = 0;
		memo = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			ans = Math.max(ans, memo[i]);
			if (i + schedule[i][0] > N)
				continue;
			memo[i + schedule[i][0]] = Math.max(ans + schedule[i][1], memo[i + schedule[i][0]]);
		}

		System.out.println(ans);
	}
}
