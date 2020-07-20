import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12886_돌그룹 {
	static int A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int ans = bfs();
		System.out.println(ans);
	}

	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[501][3]; // 1~500까지 숫자, 뒤에는 A,B,C
		q.add(new int[] { A, B, C });

		visit[A][0] = true;
		visit[B][1] = true;
		visit[C][2] = true;

		while (!q.isEmpty()) {
			int[] stone = q.poll();
			if (stone[0] == stone[1] && stone[1] == stone[2]) {
				return 1;
			}

			// 2개씩 선택하는 방식
			int X = 0, Y = 0;
			int xIdx = 0, yIdx = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = i + 1; j < 3; j++) {
					if (stone[i] <= stone[j]) {
						X = stone[i];
						xIdx = i;
						Y = stone[j];
						yIdx = j;
					} else {
						X = stone[j];
						xIdx = j;
						Y = stone[i];
						yIdx = i;
					}

					if (!isIn(X, Y))
						continue;
					if (visit[X + X][xIdx] && visit[Y - X][yIdx])
						continue;

					visit[X + X][xIdx] = true;
					visit[Y - X][yIdx] = true;

					int[] new_stone = new int[] { stone[0], stone[1], stone[2] };

					new_stone[xIdx] = X + X;
					new_stone[yIdx] = Y - X;
					q.add(new_stone);
				}
			}

		}
		return 0;
	}

	private static boolean isIn(int x, int y) {
		return 1 <= (x + x) && (x + x) <= 500 && 1 <= (y - x) && (y - x) <= 500;
	}
}
