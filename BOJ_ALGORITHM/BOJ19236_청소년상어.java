import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1. 상어는 (0,0)에서 시작한다 -> (0,0)자리에 있는 물고기를 먹고 그 방향을 갖는다
// 2-1. 물고기는 번호가 작은순으로 가진 방향으로 한칸씩 움직인다 (1번부터 16번까지다)
// 2-2. 빈칸은 그냥 가고, 물고기가 있는 칸은 자리를 교환한다.
// 2-3. 상어가 있거나 공간을 넘어가는 칸이 있다면 반시계방향으로 45도씩 회전해본다
// 2-4. 그래도 이동을 못하면 가만히 있는다
// 3-1. 상어가 움직인다.
// 3-2. 상어는 그 칸에 물고기가 없으면 안간다
// 3-3. 상어는 한번에 여러칸 갈 수 있지만 지나가면서는 물고기를 못먹는다
// 3-4. 물고기를 먹으면 그 물고기의 방향을 갖게 된다.
// 4. 상어가 더 이상 움직이지 못한다면 퇴근한다!

public class BOJ19236_청소년상어 {
	static class fishInfo {
		int dir; // 방향
		int x, y; // 위치
		boolean alive; // 먹혔는지 (안먹혔을때 true)
		// 처음에는 0이다.
		// 만약 8이 되면 8방향 봐도 못가는 얘니까 그냥 가만히 있게 하자 (갈 수 있다면 0으로 리셋한다)
		int roate;

		public fishInfo(int dir, int x, int y, boolean alive, int roate) {
			this.dir = dir;
			this.x = x;
			this.y = y;
			this.alive = alive;
			this.roate = roate;
		}
	}

	static class sharkInfo {
		int dir; // 방향
		int x, y; // 위치
		int sum; // 누적 번호 합

		public sharkInfo(int dir, int x, int y, int sum) {
			super();
			this.dir = dir;
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}

	static int max; // 최종 답

	// 8방향 0~8이라고 하자
	// 상, 상좌, 좌, 하좌, 하, 하우, 우, 상우
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	// 청소년 상어
	static sharkInfo shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int[][] map = new int[4][4]; // 맵에 물고기 번호 쓰기 (0이면 없는 것!) -1은 상어
		// 16마리 물고기 넣을 배열 (17까지 인덱스하기)
		fishInfo[] fish = new fishInfo[17];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 물고기 방향
				map[i][j] = a;
				fish[a] = new fishInfo(b, i, j, true, 0);
			}
		}

//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

//		int num = map[0][0]; // 0,0에 있는 물고기 번호
//		max = num;

		// 상어가 맵에 딱 들어왔다!
//		shark = new sharkInfo(fish[num].dir, 0, 0, num);
//		map[0][0] = -1; // 상어가 있었던 곳은 이제 상어가 사라지면 0이 되는 곳이다.

		// 이제 상어가 이동할 수 없을 때까지 움직인다!
		//while (true) {
//			map = moveFish(map, fish);
		//}

	}

	static int[][] moveFish(int[][] map, fishInfo[] fish) {
		// 1번부터 16번까지 움직이자
		for (int i = 1; i <= 2; i++) {
			boolean doMove = true; // 지금 물고기가 움직일 수 있는지?

			// 우선 물고기가 먹힌 애면 제외
			if (!fish[i].alive)
				continue;
			int nx = fish[i].x + dx[fish[i].dir];
			int ny = fish[i].y + dy[fish[i].dir];

			// 상어가 있거나 공간을 넘으면 45도씩 회전해본다
			if (map[nx][ny] == -1 || !isIn(nx, ny)) {
				int nDir = 0;
				while (true) {
					if (fish[i].roate == 8) {
						doMove = false;
						break;
					}
					
					nDir = (fish[i].dir + 1) % 8;
					int fx = fish[i].x + dx[nDir];
					int fy = fish[i].y + dy[nDir];
					// 방향을 바꿔서 문제 없이 이동이 된다면? while문을 나온다!
					if (map[fx][fy] != -1 && isIn(fx, fy)) {
						nx = fish[i].x = fx;
						ny = fish[i].y = fy;
						fish[i].dir = nDir;
						fish[i].roate = 0;
						break;
					} else { // 이동이 불가능하면 방향을 증가시켜줌
						fish[i].roate++;
					}
				}
				
				for (int s = 0; s < 4; s++) {
					System.out.println(Arrays.toString(map[s]));
				}
			}

			if (doMove) { // 이동가능하면 한다!
				if (map[nx][ny] == 0) { // 물고기가 없다
					map[nx][ny] = i;
					fish[i].x = nx;
					fish[i].y = ny;
				} else {
					// 교환할 자리
					int tx = fish[i].x;
					int ty = fish[i].y;
					int ti = map[nx][ny];

					map[nx][ny] = i;
					map[tx][ty] = ti;

					fish[i].x = nx;
					fish[i].y = ny;
					fish[ti].x = tx;
					fish[ti].y = ty;
				}
			}
		}
		return map;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}

}
