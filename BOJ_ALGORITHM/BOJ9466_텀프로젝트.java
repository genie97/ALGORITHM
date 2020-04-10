import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트_Main_임효진 {
	static int N;
	static int[] student;
	static boolean[] start;
	static boolean[] finish;
	static int cnt;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			student = new int[N + 1];
			start = new boolean[N + 1]; // 처음 시작할 때 선택
			finish = new boolean[N + 1]; // 선택하고 다시 돌아온 경우 끝났다로 표시
			cnt = 0;

			for (int i = 1; i <= N; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= N; i++) {
				if (!start[i] && !finish[i]) // 시작도 안하고, 끝도 안난 정점으로만 dfs
					dfs(i);
			}
			sb.append(N - cnt).append('\n');
		}
		System.out.println(sb);
	}

	static void dfs(int v) {
		start[v] = true;
		int nv = student[v]; // 다음 정점
		if (!start[nv]) // 첫 시작 방문을 안했다면 dfs
			dfs(nv);
		else { // 시작 방문을 했을 때,
			if (!finish[nv]) { // 끝이 나지 않았다면 사이클이니 확인해주기
				for (int vt = nv; v != vt; vt = student[vt])
					cnt++; 
				cnt++;
			}
		}
		finish[v] = true; // 최종적으로 v는 사이클확인까지 끝났다라고 표시
	}
}
