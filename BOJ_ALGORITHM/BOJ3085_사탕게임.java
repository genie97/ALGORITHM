import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사탕의 색이 다른 인접한 두칸을 고름
// 그 다음 고른칸에 들어있는 사탕을 서로 교환
// 같은 색으로 이루어져 있는 가장 긴 연속 부분을 고른다음에 사탕을 모두 먹음
// 최대 사탕 개수

public class BOJ3085_사탕게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		char[][] map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int ans = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				// 세로로 바꾸기 => 인접한 색이 다른 두 칸을 선택
				if (i + 1 < map.length && map[i][j] != map[i + 1][j]) {
					char tmp = map[i][j];
					map[i][j] = map[i + 1][j];
					map[i + 1][j] = tmp;
					int maxv = find(map);
					ans = Math.max(maxv, ans);
					// 원복
					tmp = map[i][j];
					map[i][j] = map[i + 1][j];
					map[i + 1][j] = tmp;
				}
				if (j + 1 < map[i].length && map[i][j] != map[i][j + 1]) {
					// 가로로 바꾸기 => 인접한 색이 다른 두 칸을 선택
					char tmp = map[i][j];
					map[i][j] = map[i][j + 1];
					map[i][j + 1] = tmp;
					int maxv = find(map);
					ans = Math.max(maxv, ans);
					// 원복
					tmp = map[i][j];
					map[i][j] = map[i][j + 1];
					map[i][j + 1] = tmp;
				}
			}
		}

		System.out.println(ans);
	}

	static int find(char[][] map) {
		int maxv = 0;
		// 가로 확인
		for (int i = 0; i < map.length; i++) {
			char start = map[i][0];
			int cnt = 0;
			for (int j = 0; j < map[i].length;) {
				if (map[i][j] == start) {
					cnt++;
					maxv = Math.max(maxv, cnt);
					j++;
				} else {
					start = map[i][j];
					cnt = 0;
				}
			}
		}

		// 세로 확인
		for (int j = 0; j < map[0].length; j++) {
			char start = map[0][j];
			int cnt = 0;
			for (int i = 0; i < map.length;) {
				if (map[i][j] == start) {
					cnt++;
					maxv = Math.max(maxv, cnt);
					i++;
				} else {
					start = map[i][j];
					cnt = 0;
				}
			}
		}
		return maxv;
	}
}
