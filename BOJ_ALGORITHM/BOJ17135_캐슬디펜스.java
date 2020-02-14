import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17135_캐슬디펜스 {
	public static int N;
	public static int M;
	public static int D;
	public static int[][] map;
	public static int[][] origin;
	public static boolean[] align;
	public static ArrayList<int[]> archerList;
	public static int enemy;
	public static int remain;
	public static int originRemain;
	public static int maxValue;
	public static int round = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 공격 거리
		map = new int[N + 1][M];
		origin = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				map[i][j] = line.charAt(index) - '0';
				origin[i][j] = line.charAt(index) - '0';
				if (map[i][j] == 1) // 적이 있는 곳
					remain++;
			}
		}
		originRemain = remain;
		maxValue = Integer.MIN_VALUE;
		align = new boolean[M];
		archerList = new ArrayList<int[]>();
		alignArcher(0, 0);
		System.out.println(maxValue);
	}

	public static void alignArcher(int cnt, int idx) {
		if (cnt == 3) { // 궁수 배치 끝 => 공격 턴 => 적이동
			enemy = 0; // 죽인 적은 조합 마다 리셋
			init(); // 적 위치도 리셋
			while (true) {
				attack(); // 궁수들이 공격
				move(); // 적들이 이동 (한칸 아래로 이동)
				if (remain == 0)
					break;
			}
			// 최대로 죽인 적 계산하기
			maxValue = Integer.max(maxValue, enemy);
			round++;
		}
		for (int i = idx; i < M; i++) {
			if (align[i])
				continue;
			align[i] = true;
			archerList.add(new int[] { N, i });
			alignArcher(cnt + 1, i);
			align[i] = false;
			archerList.remove(cnt);
		}
	}

	/** 궁수는 자신에게 가장 가까운 거리에 있는 적을 공격한다 (단, 거리가 같을 경우에는 왼쪽 => 인덱스가 작은애) */
	public static void attack() {
		ArrayList<int[]> eList = new ArrayList<>();
		for (int idx = 0; idx < archerList.size(); idx++) {
			int x = archerList.get(idx)[0]; // 궁수의 x 좌표
			int y = archerList.get(idx)[1]; // 궁수의 y 좌표
			int minDis = Integer.MAX_VALUE;
			int minX = -1; // 가장 가까운 적의 x좌표
			int minY = -1; // 가장 가까운 적의 y좌표
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0)
						continue;
					int dis = Math.abs(x - i) + Math.abs(y - j);
					if (dis <= minDis && dis <= D) {
						if (dis == minDis) { // 같은 거리면 가장 왼쪽의 적 죽이기!
							if (minY > j) {
								minX = i;
								minY = j;
							}
						} else {
							minX = i;
							minY = j;
						}
						minDis = dis; // 최소 갱신
					}
				}
			} // 적을 찾았다!
			if (minX == -1 && minY == -1) { // 공격거리가 안되거나 무튼 여러가지 이유로,,,적을 못죽이면
				continue; // 공격기회 끄읏!
			}
			eList.add(new int[] {minX, minY});
		}
		for (int i = 0; i < eList.size(); i++) {
			int x = eList.get(i)[0];
			int y = eList.get(i)[1];
			if(map[x][y] == 0) continue; // 이미 공격한 곳이면
			map[x][y] = 0;
			enemy++;
			remain--;
		}
	}

	public static void move() {
		int kill = 0;
		boolean stop = false;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				if (map[i][j] == 0)
					continue;
				map[i][j] = 0; // 원래 있던 곳은 움직이니까 0으로 해주기
				if (i + 1 == N) { // 만약에 성 위치로 가게 되면 적이 죽음;;
					remain--;
					continue;
				}
				map[i + 1][j] = 1; // 제대로 이동한 경우!
				kill++;
				if (kill == remain) { // 남아있는 적이 이동이 끝났다면
					stop = true;
					break;
				}
			}
			if (stop)
				break;
		}
	}

	public static void init() { // 원래의 맵으로 변경해주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = origin[i][j];
			}
		}
		remain = originRemain; // 다시 적의 수 리셋
	}
}
