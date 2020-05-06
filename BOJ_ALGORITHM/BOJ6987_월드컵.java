import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6987_월드컵 {
	static int[][] res;

	// 팀이 6개인 경우
	// 0: [1,2,3,4,5]
	// 1: [2,3,4,5]
	// 2: [3,4,5]
	// 3: [4,5]
	// 4: [5]
	// 대진표
	static int[] team1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] team2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
	static boolean possible;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			int w = 0, d = 0, l = 0; // 승리, 무승부, 패배
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			possible = false;
			res = new int[6][3];
			for (int j = 0; j < 6; j++) {
				w += res[j][0] = Integer.parseInt(st.nextToken());
				d += res[j][1] = Integer.parseInt(st.nextToken());
				l += res[j][2] = Integer.parseInt(st.nextToken());
			}

			if (w + d + l != 30)// 모든 경우의 총합은 항상 30이다
				possible = false;
			else
				dfs(0);
			sb.append(possible ? 1 : 0).append(' ');
		}
		System.out.println(sb);
	}

	static void dfs(int game) {
		if (possible)
			return;

		if (game == 15) { // 한 게임에 15번 경기
			possible = true;
			return;
		}
		int t1 = team1[game];
		int t2 = team2[game];

		// team1 이 승리할 수 있다면
		if (res[t1][0] > 0 && res[t2][2] > 0) {
			res[t1][0]--;
			res[t2][2]--;
			dfs(game + 1);
			res[t1][0]++;
			res[t2][2]++;
		}
		// team1 과 team2가 무승부라면
		if (res[t1][1] > 0 && res[t2][1] > 0) {
			res[t1][1]--;
			res[t2][1]--;
			dfs(game + 1);
			res[t1][1]++;
			res[t2][1]++;
		}
		// team2 가 승리할 수 있다면
		if (res[t1][2] > 0 && res[t2][0] > 0) {
			res[t1][2]--;
			res[t2][0]--;
			dfs(game + 1);
			res[t1][2]++;
			res[t2][0]++;
		}
	}

}
