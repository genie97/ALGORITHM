import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17143_낚시왕 {
	static class Shark {
		int r; // 행위치
		int c; // 열위치
		int s; // 속력 (한 번에 몇 칸 이동하는지 여부)
		int d; // 이동방향 (0: 상 1: 하 2: 우 3: 좌)
		int z; // 크기

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static int R, C, M;
	static int[][] map;
	static Shark[] shark;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 상어 마리수
		map = new int[R + 1][C + 1];
		shark = new Shark[10001];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			shark[z] = new Shark(r, c, s, d, z); // 크기별로 넣기
			map[r][c] = z;
		}
		res = 0;
		for (int i = 1; i <= C; i++) {
			f_moving(i);
//			System.out.println("잡음");
//			printMap();
//			System.out.println();
			s_moving();
//			System.out.println("이동");
//			printMap();
//			System.out.println();
		}
		System.out.println(res);
	}

	// 상어가 자기 속력대로 이동하기
	// 대신 같은 공간에 있는 상어라면 몸집 큰 애가 잡아먹음
	static void s_moving() {
		for (int j = 1; j <= 10000; j++) {
			if (shark[j] == null)
				continue;
			Shark curS = shark[j];
			int nr = curS.r;
			int nc = curS.c;
			int move = curS.s;
			int nd = curS.d;
			map[nr][nc] = 0; // 이동했으니 맵에서 제거

			switch (nd) {
			case 0:
			case 1:
				move %= (R * 2 - 2);
				while (move > 0) {
					if (nr == 1) {
						nd = 1;
					}
					if (nr == R) {
						nd = 0;
					}
					nr += dx[nd];
					move--;
				}
				shark[curS.z] = new Shark(nr, nc, curS.s, nd, curS.z);
				break;
			case 2:
			case 3:
				move %= (C * 2 - 2);
				while (move > 0) {
					if (nc == 1)
						nd = 2;
					if (nc == C)
						nd = 3;
					nc += dy[nd];
					move--;
				}
				shark[curS.z] = new Shark(nr, nc, curS.s, nd, curS.z);
				break;
			}
		}
		// 제일 크기가 큰 상어만 살아남음
		for (int j = 1; j <= 10000; j++) {
			if (shark[j] == null)
				continue;
			Shark s1 = shark[j];
			if (map[s1.r][s1.c] < s1.z) {
				shark[map[s1.r][s1.c]] = null;
				map[s1.r][s1.c] = s1.z;
			}
		}
	}

	// 낚시왕이 c행으로 이동해서 땅과 가까운 상어를 잡는다
	static void f_moving(int c) {
		for (int i = 1; i <= R; i++) {
			if (map[i][c] == 0)
				continue;
			shark[map[i][c]] = null;
			res += map[i][c];
			map[i][c] = 0;
			break;
		}
	}

//	static void printMap() {
//		for (int i = 1; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//	}
}
