import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {
	static int N;
	static int[] student;
	static boolean[] start;
	static boolean[] finish;
	static int cnt;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			student = new int[N + 1];
			start = new boolean[N + 1];
			finish = new boolean[N + 1];
			cnt = 0;

			for (int i = 1; i <= N; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= N; i++) {
				if (!start[i])
					dfs(i);
			}
			System.out.println(N-cnt);
		}
	}

	static void dfs(int v) {
		start[v] = true;
		int nv = student[v];
		if (!start[nv])
			dfs(nv);
		else {
			if (!finish[nv]) {
				for (int vt = nv; v != vt; vt = student[vt])
					cnt++;
				cnt++;
			}
		}
		finish[v] = true;
	}
}
