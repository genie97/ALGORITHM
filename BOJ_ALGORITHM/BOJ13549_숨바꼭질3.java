import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구현시 주의사항
 * 이 문제에서는 순간이동(*2) 작업시, 시간이 0초가 걸린다
 * 앞으로 이동/뒤로 이동 작업은 시간이 1초가 걸린다
 * 가중치가 있다고 생각하므로, 순간이동 작업을 먼저 하는 방식으로 해결해야함
 */
public class BOJ13549_숨바꼭질3 {
	static int N, K;
	static int[] d = { 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int res = bfs();
		System.out.println(res);
	}

	static int bfs() {
		boolean[] visit = new boolean[100001];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { N, 0 });
		visit[N] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int[] cur = q.poll();
				if (cur[0] == K) {
					return cur[1];
				}
				for (int i = 0; i < 3; i++) {
					int x = cur[0];
					int t = cur[1];
					if (i == 0) {
						x = cur[0] * d[i];
					} else {
						x = cur[0] + d[i];
						t = cur[1] + 1;
					}
					if (x > 100000 || x < 0)
						continue;
					if (visit[x])
						continue;
					q.add(new int[] { x, t });
					visit[x] = true;
				}
			}
		}
		return -1;
	}

}
