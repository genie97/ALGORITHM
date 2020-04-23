import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_인구이동 {
	static int people = 0; // 연합국에 있는 사람 수
	static int n = 0; // 연합국 개수
	static int[][] A;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, L, R;
	static boolean[][] visit;
	static ArrayList<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < A.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < A.length; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean move = true; // 이동 가능 여부
		int cnt = 0; // 인구 이동 횟수
		while (move) {
			move = false;
			visit = new boolean[N][N];
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A.length; j++) {
					if (!visit[i][j]) {
						// bfs할 때마다 연합국을 찾기 때문에 초기화 필요
						list = new ArrayList<>(); // 연합국 리스트
						people = 0;
						n = 0;
						bfs(i, j);
					}
					if (n >= 2) { // 연합국이 2개 이상이면 이동 가능
						move = true;
						for (int k = 0; k < list.size(); k++) {
							A[list.get(k)[0]][list.get(k)[1]] = people / n;
						}
					}
				}
			}
			if (!move)
				break;
			cnt++;
		}
		System.out.println(cnt);
	}

	static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { i, j });
		list.add(new int[] { i, j });
		visit[i][j] = true;
		n = 1;
		people = A[i][j];

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (Math.abs(A[nx][ny] - A[x][y]) >= L && Math.abs(A[nx][ny] - A[x][y]) <= R) { // 차이가 L,R사이인 경우
					if (visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					q.add(new int[] { nx, ny });
					list.add(new int[] { nx, ny });
					n++;
					people += A[nx][ny];
				}
			}
		}
	}
}
