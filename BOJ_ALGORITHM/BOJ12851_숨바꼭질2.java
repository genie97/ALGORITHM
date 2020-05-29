import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851_숨바꼭질2 {
	static int N;
	static int K;
	static int[] dx = { 2, 1, -1 };
	static boolean[] visit;
	static final int MAX = 100000;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visit = new boolean[MAX + 1];

		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visit[N] = true;

		int time = 0;
		int caseN = 0;
		boolean find = false;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				int cur = q.poll();
				if (cur == K) {
					caseN++;
					find = true;
				}
				visit[cur] = true;

				for (int d = 0; d < 3; d++) {
					int next = 0;
					if (d == 0) {
						next = cur * dx[d];
					} else {
						next = cur + dx[d];
					}

					if (next < 0 || next > MAX)
						continue;
					if (visit[next])
						continue;
					q.add(next);
				}
			}
			if (find)
				break;
			time++;
		}

		sb.append(time).append('\n').append(caseN).append('\n');

		System.out.println(sb);
	}

}
