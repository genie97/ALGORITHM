import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*visit 체크 없으면 메모리 초과가 발생 (조심할 것!)*/
public class BOJ1697_숨바꼭질 {
	public static int N;
	public static int K;
	public static int[] d = { -1, 1, 2 };
	public static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[100001];
		int ans = bfs(N);
		System.out.println(ans);
	}

	public static int bfs(int N) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cp = q.poll();
				visit[cp] = true;
				if (cp == K) {
					return cnt;
				}
				for (int j = 0; j < 3; j++) {
					int np = 0;
					if (j == 2) {
						np = cp * d[j];
					} else {
						np = cp + d[j];
					}
					if (np < 0 || np > 100000)
						continue;
					if (visit[np])
						continue;
					visit[np] = true;
					q.add(np);
				}
			}
			cnt++;
		}
		return cnt;
	}
}
