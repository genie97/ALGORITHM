import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 상어는 1번부터 M이하의 자연수 번호가 붙어있음
// 1의 번호를 가진 어른상어가 가장 강력해서 나머지 모두를 쫓아냄
// N*N 격자 중 M개에 상어가 한마리씩 들어있다.

//시뮬레이션
// 1. 자신의 위치에 자신의 냄새를 뿌린다
// 2. 1초마다 모든 상어가 상하좌우로 인접한 칸 중 하나로 이동하고 자신의 냄새를 그 칸에 뿌린다
// 3. 냄새는 상어가 k번 이동하고 나면 사라진다
// 4. 각 상어가 이동방향을 결정할 때는 인접한 칸 중 아무 냄새가 없는 칸으로 간다
// 5. 그런 칸이 없으면 자신의 냄새가 있는 칸으로 방향을 잡는다.
// 6. 이때 가능한 칸이 여러개인 경우, 특정한 우선순위를 따른다
// 7. 우선순위는 상어마다 다를 수 있고 현재 보고 있는 방향에 따라 또 다를 수 있다.
// 8. 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.
// 9. 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.

// 입력
// N개의 줄에 걸쳐 격자의 모습이 주어짐 
// 0은 빈칸 x는 x번 상어
// N,M,k
// 각 상어의 방향이 차례대로 주어짐 (1~4 상하좌우)
// 4줄씩 순서대로 상 하 좌 우에 대한 우선순위 (4 * M 줄)

// 출력
// 1000초 초과하면 -1 리턴
// 1번 상어만 남게되는 시간 리턴

// 상어 이동
// smoke의 크기가 k보다 크면 제일 먼저 넣은 거는 지우기
// 한 칸에 여러마리인지 확인해서 지워버리기
// 칸마다 여러마리 인곳은 가장 번호가 작은 상어 빼고 지워버리기 (소팅)
public class BOJ19237_어른상어 {
	// 상어 저장용
	static class Shark implements Comparable<Shark> {
		int num; // 고유번호
		int x;
		int y;
		int dir;
		boolean live; // 쫓겨났는지?
		ArrayList<int[]> smoke;

		public Shark(int num, int x, int y, int dir, boolean live, ArrayList<int[]> smoke) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.live = live;
			this.smoke = smoke; // 냄새를 뿌린 곳의 정보 넣기 k번 이동후에는 제거
		}

		@Override
		public int compareTo(Shark o) { // 번호순 정렬
			return Integer.compare(this.num, o.num);
		}

	}

	static Shark[] sharks;

	static int N, M, k;
	static int[][] map;
	static int[][] timeMap;
	static ArrayList<Integer> list[]; // 각 인덱스별 상어 넣을 곳 (상어 여러마리 들어가는 후보군저장)
	static int[][][] info; // 우선순위 저장 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		timeMap = new int[N][N];
		info = new int[M + 1][4][4]; // 상어번호 - 상하좌우(look) - 우선순위(dir)
		sharks = new Shark[M + 1]; // 상어 정보 번호대로 넣어두기

		list = new ArrayList[N * N + 1];
		// 후보군 리스트에 대한 초기화
		for (int i = 0; i <= N * N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		// 시간에 대한 정보
		for (int i = 0; i < timeMap.length; i++) {
			Arrays.fill(timeMap[i], -1);
		}
		// 맵에 대한 정보, 상어의 처음 시작 위치에 대한 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					ArrayList<int[]> smoke = new ArrayList<>();
					smoke.add(new int[] { i, j });
					sharks[map[i][j]] = new Shark(map[i][j], i, j, -1, true, smoke);
					timeMap[i][j] = k;
				}
			}
		}

		// 최초 방향 정보 입력
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}

		// 우선순위에 대한 정보
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < 4; k++) {
					info[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}

		int ans = simulation();
		System.out.println(ans);
	}

	static int simulation() {
		int time = 0;
		while (true) {
			time++; // 1. 1초가 지났다
			if (time > 1000) { // 1000초 이상이면 break
				break;
			}
			// 2. 상어가 이동하면서 냄새를 뿌린다0
			for (int i = 1; i <= M; i++) {
				if (!sharks[i].live)
					continue;
				// 우선순위가 가장 높은 방향을 찾기 (우선순위별로 갈 수 있는지 여부를 파악한다, 1부터 보는데 갈 수있으면 바로 break한다)
				ArrayList<Integer> emptySpace = new ArrayList<>();
				for (int j = 0; j < 4; j++) {
					int nx = sharks[i].x + dx[j];
					int ny = sharks[i].y + dy[j];
					if (!isIn(nx, ny))
						continue;
					if (map[nx][ny] == 0)
						emptySpace.add(j); // 움직이는 방향
				}

				if (emptySpace.size() == 0) { // 빈 곳이 아예 없다면 내 냄새가 있는 곳을 찾는다
					ArrayList<Integer> mySpace = new ArrayList<>();
					for (int j = 0; j < 4; j++) {
						int nx = sharks[i].x + dx[j];
						int ny = sharks[i].y + dy[j];
						if (!isIn(nx, ny))
							continue;
						if (map[nx][ny] == i)
							mySpace.add(j); // 움직이는 방향
					}
					// mySpace 중 우선순위가 높은 애로 처리한다
					if (mySpace.size() == 1) {
						int nDir = mySpace.get(0);
						int nx = sharks[i].x + dx[nDir];
						int ny = sharks[i].y + dy[nDir];
						sharks[i].x = nx;
						sharks[i].y = ny;
						sharks[i].dir = nDir;
						sharks[i].smoke.add(new int[] { nx, ny }); // 새로운 곳에 대한 냄새 저장
						list[nx * N + ny].add(i); // 해당 위치에 있을 수 있다고 넣기
					} else {

						outer: for (int j = 0; j < 4; j++) {
							int nDir = info[i][sharks[i].dir][j];
							for (int k = 0; k < mySpace.size(); k++) {
								if (nDir == mySpace.get(k)) {
									int nx = sharks[i].x + dx[nDir];
									int ny = sharks[i].y + dy[nDir];
									sharks[i].x = nx;
									sharks[i].y = ny;
									sharks[i].dir = nDir;
									sharks[i].smoke.add(new int[] { nx, ny }); // 새로운 곳에 대한 냄새 저장
									list[nx * N + ny].add(i); // 해당 위치에 있을 수 있다고 넣기
									break outer;
								}
							}
						}
					}
				} else {
					// emptySpace 중 우선순위가 높은 애로 처리한다
					if (emptySpace.size() == 1) {
						int nDir = emptySpace.get(0);
						int nx = sharks[i].x + dx[nDir];
						int ny = sharks[i].y + dy[nDir];
						sharks[i].x = nx;
						sharks[i].y = ny;
						sharks[i].dir = nDir;
						sharks[i].smoke.add(new int[] { nx, ny }); // 새로운 곳에 대한 냄새 저장
						list[nx * N + ny].add(i); // 해당 위치에 있을 수 있다고 넣기
					} else {
						outer: for (int j = 0; j < 4; j++) {
							int nDir = info[i][sharks[i].dir][j];
							for (int k = 0; k < emptySpace.size(); k++) {
								if (nDir == emptySpace.get(k)) {
									int nx = sharks[i].x + dx[nDir];
									int ny = sharks[i].y + dy[nDir];
									sharks[i].x = nx;
									sharks[i].y = ny;
									sharks[i].dir = nDir;
									sharks[i].smoke.add(new int[] { nx, ny }); // 새로운 곳에 대한 냄새 저장
									list[nx * N + ny].add(i); // 해당 위치에 있을 수 있다고 넣기
									break outer;
								}
							}
						}
					}
				}
			}
			// 리스트 정리하기
			for (int i = 0; i <= N * N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			for (int i = 1; i <= M; i++) {
				if (!sharks[i].live)
					continue;
				list[sharks[i].x * N + sharks[i].y].add(i);
			}

			// 한칸에 두마리 이상인 곳 정리하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int num = i * N + j;
					if (list[num].size() == 0)// 아무것도 없는 곳
						continue;
					Collections.sort(list[num]); // 번호에 대한 오름차순으로 정렬
					map[i][j] = list[num].get(0);
					timeMap[i][j] = k + 1; // 새로 들어온 위치는 새로운 시간으로 세팅
					if (list[num].size() > 1) { // 한 공간에 두마리 이상이다?
						while (list[num].size() > 1) {
							int sharkNum = list[num].get(1);
							sharks[sharkNum].live = false;
							list[num].remove(1);
						}
					}
				}
			}

			// K번 이동했다면 냄새 지우기
			for (int i = 0; i < timeMap.length; i++) {
				for (int j = 0; j < timeMap[i].length; j++) {
					if (timeMap[i][j] == -1)
						continue;
					timeMap[i][j]--;
					if (timeMap[i][j] == 0) {
						int sharkNum = map[i][j];
						sharks[sharkNum].smoke.remove(new int[] { i, j });
						map[i][j] = 0;
					}
				}
			}
			// 1번만 남았는지 확인하기
			if (onlyOne())
				return time;
		}
		return -1;

	}

	static boolean isIn(int nx, int ny) {
		return !(nx < 0 || ny < 0 || nx >= N || ny >= N);
	}

	// 2번부터 살아 있다면? 1번만 남은 경우가 아님
	static boolean onlyOne() {
		for (int i = 2; i <= M; i++) {
			if (sharks[i].live)
				return false;
		}
		return true;
	}

}
