import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 지도는 2개의 줄로 나누어져있다
// 각 줄은 N개의 칸으로 나누어져있다
// 위험한 칸과 안전한 칸으로 구분, 안전한칸만 이동가능
// 유저는 왼쪽 1번칸 위에 서있음
// 매초마다 세개 중 하나의 동작을 한다
// 1. 한칸 앞으로 이동
// 2. 한칸 뒤로 이동
// 3. 반대편 줄로 점프 => 원래 있던 칸보다 k칸 앞의 칸으로 이동 현재가 i면 i+k칸
// N보다 더 큰 칸으로 이동하는 경우 게임 클리어
// 1초에 한칸씩 각 첫줄의 첫칸이 사라짐
// 상근이가 3번칸에 있다면 이동하고 없어지는것임
// 게임을 클리어할 수 있는지 없는지 구하라
// i번째 문자가 0이면 위험, 1이면 안전한칸
// N과 K가 주어짐
// 두줄이 주어짐

public class BOJ15558_점프게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] line = new int[2][N];
		// 방문체크는 꼭 할 것!! => 메모리 초과의 범인!!
		boolean[][] visit = new boolean[2][N];

		for (int i = 0; i < line.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				line[i][j] = str.charAt(j) - '0';
			}
		}

		// 왼쪽 줄의 0번칸은 항상 안전 (여기서부터 시작)
		// 3가지 동작을 이동할 수 있는지
		// 1. 앞으로 이동 -> 위험한칸만 아니면 이동가능
		// 2. 뒤로 이동 -> 지워질 칸이면 이동 불가
		// 3. 위험한칸만 아니면 이동가능 (몇번째 줄인지 변경필요)
		Queue<int[]> q = new LinkedList<>(); // <라인, 위치> 표기 필요
		q.add(new int[] { 0, 0 });
		visit[0][0] = true;
		int remove_cnt = 0;

		int ans = 0;
		outer: while (!q.isEmpty()) {
			int size = q.size();
			// 1. 매초마다 칸이 지워진다
			line[0][remove_cnt] = 0;
			line[1][remove_cnt] = 0;
			remove_cnt++;

			for (int sz = 0; sz < size; sz++) {
				int[] cur = q.poll(); // 현재 위치
				// 현재 위치가 N칸보다 크면 게임 클리어 가능!
				if (cur[1] >= N) {
					ans = 1;
					break outer;
				}

				// 1. 앞으로 이동
				int np = cur[1] + 1;
				if (np >= N) {
					ans = 1;
					break outer;
				}
				if (!visit[cur[0]][np] && line[cur[0]][np] == 1) {
					visit[cur[0]][np] = true;
					q.add(new int[] { cur[0], np });
				}

				// 2. 뒤로 이동
				np = cur[1] - 1;
				if (np >= 0 && !visit[cur[0]][np] && line[cur[0]][np] == 1) {
					visit[cur[0]][np] = true;
					q.add(new int[] { cur[0], np });
				}

				// 3. 라인 바꿔서 이동
				int nl = (cur[0] == 0) ? 1 : 0;
				np = cur[1] + k;
				if (np >= N) {
					ans = 1;
					break outer;
				}
				if (!visit[nl][np] && line[nl][np] == 1) {
					visit[nl][np] = true;
					q.add(new int[] { nl, np });
				}
			}

		}
		System.out.println(ans);
	}
}
