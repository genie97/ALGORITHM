import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 상하좌우 네 방향 중 하나로 이동시키는 것
// 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐짐
// 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.

public class BOJ12100_2048Easy {
	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		simulation(0, map);
		System.out.println(ans);
	}

	static void simulation(int cnt, int[][] map) {
		if (cnt == 5) {
			findMax(map);
			return;
		}
		// 이동을 위한 새로운 맵
		// 0: 상 1: 하 2: 좌 3: 우
		int[][] new_map = new int[N][N];
		for (int i = 0; i < 4; i++) {
			copy(new_map, map);
			new_map = move(i, new_map);
			simulation(cnt + 1, new_map);
		}

	}

	static void findMax(int[][] map) {
		int maxv = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				maxv = Integer.max(maxv, map[i][j]);
			}
		}
		ans = Math.max(ans, maxv);
	}

	static void copy(int[][] new_map, int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				new_map[i][j] = arr[i][j];
			}
		}
	}

	static int[][] move(int dir, int[][] arr) {
		int[][] res = new int[N][N];
		boolean[][] combine = new boolean[N][N];
		if (dir == 0) { // 위로!
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					int x = i;
					while (x - 1 >= 0 && res[x - 1][j] == 0) {
						x--;
					}
					// 위에 있는 블록과 값이 동일할 때, (한번도 합쳐지지 않은 곳임을 확인해야함!)
					if (x - 1 >= 0 && res[x - 1][j] == arr[i][j] && !combine[x - 1][j]) {
						res[x - 1][j] = arr[i][j] * 2;
						// 이 위치는 이미 합쳐진 곳이다
						combine[x - 1][j] = true;
					} else {
						res[x][j] = arr[i][j];
					}
				}
			}
		} else if (dir == 1) { // 아래로!
			for (int i = arr.length - 1; i >= 0; i--) {
				for (int j = 0; j < arr[i].length; j++) {
					int x = i;
					while (x + 1 < arr.length && res[x + 1][j] == 0) {
						x++;
					}
					// 아래 있는 블록과 값이 동일할 때,
					if (x + 1 < res.length && res[x + 1][j] == arr[i][j] && !combine[x + 1][j]) {
						res[x + 1][j] = arr[i][j] * 2;
						// 이 위치는 이미 합쳐진 곳이다
						combine[x + 1][j] = true;
					} else {
						res[x][j] = arr[i][j];
					}
				}
			}
		} else if (dir == 2) { // 왼쪽으로!
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					int y = j;
					while (y - 1 >= 0 && res[i][y - 1] == 0) {
						y--;
					}
					// 위에 있는 블록과 값이 동일할 때,
					if (y - 1 >= 0 && res[i][y - 1] == arr[i][j] && !combine[i][y - 1]) {
						res[i][y - 1] = arr[i][j] * 2;
						// 이 위치는 이미 합쳐진 곳이다
						combine[i][y - 1] = true;
					} else {
						res[i][y] = arr[i][j];
					}
				}
			}
		} else { // 오른쪽으로!
			for (int i = 0; i < arr.length; i++) {
				for (int j = arr[i].length - 1; j >= 0; j--) {
					int y = j;
					while (y + 1 < arr[i].length && res[i][y + 1] == 0) {
						y++;
					}
					// 위에 있는 블록과 값이 동일할 때,
					if (y + 1 < res[i].length && res[i][y + 1] == arr[i][j] && !combine[i][y + 1]) {
						res[i][y + 1] = arr[i][j] * 2;
						// 이 위치는 이미 합쳐진 곳이다
						combine[i][y + 1] = true;
					} else {
						res[i][y] = arr[i][j];
					}
				}
			}
		}
		return res;
	}
}
