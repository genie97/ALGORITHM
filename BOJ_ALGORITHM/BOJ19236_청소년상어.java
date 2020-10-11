import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 4*4 인 공간
// x는 행 y는 열
// 한칸에는 물고기가 한마리
// 물고기는 번호와 방향을 가짐 / 번호는 1~16, 방향은 0~7
// 청소년 상어는 0,0 물고기를 먹고 0,0 물고기의 방향을 갖는다
// 번호가 작은 물고기부터 움직인다
// 물고기는 한칸을 이동하고, 이동할 수 있는 칸은 빈칸과 다른 물고기가 있는 칸이다
// 상어가 있거나 공간의 경계를 넘으면 움직이지 못한다
// 이동할 수 있을 때까지 45도 반시계 회전을 한다
// 만약 칸을 이동할 수 없으면 이동하지 않는다
// 그외의 경우는 그칸으로 이동하고, 그 칸에 물고기가 있다면 서로의 위치를 바꾸는 방식으로 이동한다
// 물고기의 이동이 다 끝나면 상어가 이동한다
// 상어는 방향에 있는 칸으로 이동을 할 수 있는데 한 번에 여러 칸을 이동할 수있다.
// 먹은 물고기의 방향을 갖는다 (지나가는 칸에 있는 물고기는 먹지않는다)
// 물고기가 없는 칸으로는 이동할 수 없다
// 상어가 이동할 수 있는 칸이 더 이상 없으면 집을 간다
// 상어가 이동한 후에는 물고기가 이동
// 상어가 먹을 수 있는 번호의 합의 최댓값
public class BOJ19236_청소년상어 {
	static class fishInfo {
		int x;
		int y;
		int dir;
		boolean isAlive;

		public fishInfo(int x, int y, int dir, boolean isAlive) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}

	static Map<Integer, fishInfo> fish;

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[][] map = new int[4][4];
		fish = new TreeMap<>();
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = 0;
			while (st.hasMoreTokens()) {
				int ai = Integer.parseInt(st.nextToken());
				int bi = Integer.parseInt(st.nextToken()) - 1;
				map[i][idx] = ai;
				fish.put(ai, new fishInfo(i, idx, bi, true));
				idx++;
			}
		}
		ans = map[0][0];
		fish.put(map[0][0], new fishInfo(0, 0, fish.get(map[0][0]).dir, false));
		map[0][0] = -1; // 상어가 있다.
		dfs(0, 0, map, ans, fish.get(ans).dir);
		System.out.println(ans);
	}

	static void dfs(int x, int y, int[][] map, int count, int sDir) { // 상어 위치, 먹은 물고기번호 카운트, 방향
		ans = Math.max(ans, count);

		Map<Integer, fishInfo> copyfish = copyFishInfo(); // 이동전 물고기 정보 저장

		int[][] newMap = new int[4][4];
		/***** 물고기가 이동 *****/
		map = moveFish(map); // 물고기가 이동한다
		copy(newMap, map); // 움직인 후 맵 정보 저장

		for (int st = 1; st < 4; st++) { // 상어가 한번에 갈 수 있는 step이라고 본다

			/***** 상어가 먹음 *****/
			int nx = x + (dx[sDir] * st);
			int ny = y + (dy[sDir] * st);
			if (!isIn(nx, ny)) // 경계를 넘어가면 못간다
				continue;
			if (map[nx][ny] == -2) // 물고기가 없어도 못간다
				continue;
			int fishNum = map[nx][ny];
			int nDir = fish.get(fishNum).dir; // 잡아먹는 물고기가 가지는 방향

			// 물고기를 먹는다
			fish.put(fishNum, new fishInfo(nx, ny, nDir, false));
			map[nx][ny] = -1; // 새로운 자리에 상어를 둔디
			map[x][y] = -2; // 상어가 지나간 자리는 물고기가 사라졌을테니 -2로 표시

			dfs(nx, ny, map, count + fishNum, nDir);

			/***** 상어가 먹은거에 대한 원복 *****/
			map[x][y] = -1; // 다시 원복하기 위해 원래 자리에 상어 두기
			map[nx][ny] = fishNum; // 물고기를 다시 살렸다
			fish.put(fishNum, new fishInfo(nx, ny, nDir, true));

			/***** 물고기가 이동했던 것의 원복 *****/
			copy(map, newMap); // 맵을 원상태로 바꾼다
		}
		
		/***** (물고기 이동전으로 돌리기)물고기가  정보에 대한 원복 *****/
		fish = copyfish;

	}

	static Map<Integer, fishInfo> copyFishInfo() {
		Map<Integer, fishInfo> newFish = new TreeMap<>();

		for (int i = 1; i <= 16; i++) {
			newFish.put(i, fish.get(i));
		}
		return newFish;
	}

	static void copy(int[][] copy, int[][] origin) {
		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < origin.length; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}

	static int[][] moveFish(int[][] map) {
		for (int i = 1; i <= 16; i++) {
			int x = fish.get(i).x;
			int y = fish.get(i).y;
			boolean isAlive = fish.get(i).isAlive;
			if (isAlive) { // 살아있는 물고기만 움직인다
				int moveDir = checkDir(map, i);
				if (moveDir == -1)
					continue;
				int nx = x + dx[moveDir];
				int ny = y + dy[moveDir];

				if (map[nx][ny] == -2) { // 물고기가 없다면?
					fish.put(i, new fishInfo(nx, ny, moveDir, isAlive));
					map[nx][ny] = i;
					map[x][y] = -2;
				} else { // 물고기가 있다면
					int tradeFish = map[nx][ny];
					fish.put(tradeFish, new fishInfo(x, y, fish.get(tradeFish).dir, fish.get(tradeFish).isAlive));
					map[x][y] = tradeFish;
					fish.put(i, new fishInfo(nx, ny, moveDir, isAlive));
					map[nx][ny] = i;
				}
			}
		}
		return map;
	}

	static int checkDir(int[][] map, int fishNum) {
		int x = fish.get(fishNum).x;
		int y = fish.get(fishNum).y;
		int curDir = fish.get(fishNum).dir;
		boolean isMove = false;
		for (int i = 0; i < 8; i++) {
			int newDir = (curDir + i) % 8;
			int nx = x + dx[newDir];
			int ny = y + dy[newDir];
			if (!isIn(nx, ny)) // 위치가 경계를 넘거나
				continue;
			if (map[nx][ny] == -1) // 상어가 있거나
				continue;
			// 그게 아니면 바로 그 방향으로 이동하면 된다
			curDir = newDir;
			isMove = true;
			break;
		}
		if (isMove)
			return curDir;
		else
			return -1;
	}

	static boolean isIn(int x, int y) {
		return !(x < 0 || y < 0 || x >= 4 || y >= 4);
	}

}
