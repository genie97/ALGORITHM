import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2382_AD_미생물격리하기 {

	public static class Cluster {
		int n, d;
		boolean m;

		Cluster(int n, int d, boolean m) {
			this.n = n;
			this.d = d;
			this.m = m;
		}
	}

	public static int N;
	public static int M;
	public static int K;
	// 상: 0 하 : 1 좌: 2 우: 3
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static ArrayList<Cluster>[][] map;
	public static int[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new ArrayList[N][N];
			visit = new int[N][N];
			// 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new ArrayList<Cluster>();
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				map[x][y].add(new Cluster(n, d, false));
				visit[x][y]++;
			}
			for (int i = 0; i < M; i++) {
				move();
				joinCluster();
			}
			int ans = count();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

//	private static void print() {
//		for (int x = 0; x < N; x++) {
//			for (int y = 0; y < N; y++) {
//				if (visit[x][y] == 0)
//					System.out.print(0 + " ");
//				else
//					System.out.print(map[x][y].get(0).n + " ");
//			}
//			System.out.println();
//		}
//
//	}

	public static int count() {
		int sum = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (visit[x][y] == 0)
					continue;
				sum += map[x][y].get(0).n;
			}
		}
		return sum;
	}

	// 같은 위치에 군집이 있는 경우 합친다
	public static void joinCluster() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (visit[x][y] == 0)
					continue;
				int nn = 0, nd = -1, maxN = 0;
				if (visit[x][y] == 1) { // 이동 가능만 변경해주고 continue
					map[x][y].get(0).m = false;
					continue;
				}
				// 2마리 이상 군집일 때는 합치는 역할 
				for (int i = 0; i < visit[x][y]; i++) {
					int n = map[x][y].get(i).n;
					int d = map[x][y].get(i).d;
					if (maxN < n) { // 가장 많은 미생물 수를 가진 군집의 방향을 따른다
						maxN = n;
						nd = d;
					}
					nn += n; // 모든 미생물 수는 합쳐진다
				}
				// 군집을 하나로 만들기
				map[x][y].clear();
				map[x][y].add(new Cluster(nn, nd, false));
				visit[x][y] = 1;
			}
		}
	}

	public static void move() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (visit[x][y] == 0)
					continue;
				// 해당 위치에서 군집 좌표 꺼내기
				int n = map[x][y].get(0).n;
				int d = map[x][y].get(0).d;
				boolean doMove = map[x][y].get(0).m;
				if (doMove) // 이번 타임에 이미 움직인 군집인 경우 제외
					continue;
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nd = d, nn = n;
				// 약품 처리 한곳=> 절반이 죽고, 방향이 전환
				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
					nn = n / 2;
					// 방향이 반대로 전환
					switch (nd) {
					case 0:
						nd = 1;
						break;
					case 1:
						nd = 0;
						break;
					case 2:
						nd = 3;
						break;
					case 3:
						nd = 2;
						break;
					}
				}
				// 기존 위치 지우기
				map[x][y].remove(0);
				visit[x][y]--;

				// 새로운 위치 업데이트
				map[nx][ny].add(new Cluster(nn, nd, true));
				visit[nx][ny]++;
			}
		}
	}

}
