import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ13913_숨바꼭질4 {
	static int N;
	static int K;
	static int[] dx = { 2, 1, -1 };
	static int[] visit;
	static final int MAX = 100000;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visit = new int[MAX + 1];
		Arrays.fill(visit, -2);

		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visit[N] = -1;

		int time = 0;

		out: while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				int cur = q.poll();
				if (cur == K) {
					break out;
				}
				for (int d = 0; d < 3; d++) {
					int next = 0;
					if (d == 0) {
						next = cur * dx[d];
					} else {
						next = cur + dx[d];
					}

					if (next < 0 || next > MAX)
						continue;
					if (visit[next] != -2)
						continue;
					visit[next] = cur;
					q.add(next);
				}
			}
			time++;
		}
		sb.append(time).append('\n');

		Stack<Integer> s = new Stack<>();

		for (int i = 0; i <= time; i++) {
			s.add(K);
			K = visit[K];
		}

		while (!s.isEmpty()) {
			sb.append(s.pop()).append(' ');
		}
		
		System.out.println(sb);
	}
}
