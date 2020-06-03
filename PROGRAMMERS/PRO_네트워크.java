public class PRO_네트워크 {
	static boolean[] visit;
	static int v_cnt;

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } };
		System.out.println(solution(n, computers));
	}

	static int solution(int n, int[][] computers) {
		int answer = 0;
		visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			v_cnt = 0;
			if (visit[i])
				continue;
			dfs(n, computers, i);
			if (v_cnt != 0)
				answer++;
		}
		return answer;
	}

	static void dfs(int n, int[][] computers, int v) {
		visit[v] = true;

		for (int i = 0; i < computers[v].length; i++) {
			if (computers[i][v] == 1) {
				if (visit[i] || v==i)
					continue;
				dfs(n, computers, i);
			}
		}
		v_cnt++;
	}
}
