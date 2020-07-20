import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12886_돌그룹 {
	static int A, B, C, D;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		D = A + B + C;

		if (D % 3 != 0) {
			System.out.println(0);
		} else {
			bfs();
		}
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visit = new boolean[1000][1000];
		q.add(new int[] { A, B });

		visit[A][B] = true;

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();

			int z = D - x - y;

			if (x == y && y == z) {
				System.out.println(1);
				return;
			}

			// 2개씩 선택하는 방식
			int[] nx = new int[] { x, x, y };
			int[] ny = new int[] { y, z, z };

			for (int i = 0; i < 3; i++) {
				int a = nx[i];
				int b = ny[i];

				if (a < b) {
					b -= a;
					a += a;
				} else if (a > b) {
					a -= b;
					b += b;
				} else {
					continue;
				}

				int c = D - a - b;
				int X = Math.min(Math.min(a, b), c);
				int Y = Math.max(Math.min(a, b), c);

				if (isIn(X, Y) && !visit[X][Y]) {
					q.add(new int[] { X, Y });
					visit[X][Y] = true;
				}
			}

		}
		System.out.println(0);
	}

	private static boolean isIn(int x, int y) {
		return 1 <= x && x <= 500 && 1 <= y && y <= 500;
	}
}
