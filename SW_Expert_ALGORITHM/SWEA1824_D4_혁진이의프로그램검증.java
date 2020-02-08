import java.util.Arrays;
import java.util.Scanner;

public class SWEA1824_D4_혁진이의프로그램검증 {
	private static char[][] map;
	private static boolean[][][][] visit;
	private static int R;
	private static int C;
	private static int[] dx = { -1, 1, 0, 0 }; // 0. 상 1. 하 2. 좌 3. 우
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			R = sc.nextInt();
			C = sc.nextInt();
			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			visit = new boolean[R][C][16][4];
			if (isPossible(0, 0, 0, 3)) {
				System.out.println("#" + tc + " YES");
			} else {
				System.out.println("#" + tc + " NO");
			}
		}
	}

	public static boolean isPossible(int x, int y, int mem, int dir) {

		switch (map[x][y]) {
		case '@':
			return true;
		case '<':
			dir = 2;
			break;
		case '>':
			dir = 3;
			break;
		case '^':
			dir = 0;
			break;
		case 'v':
			dir = 1;
			break;
		case '_':
			if (mem == 0)
				dir = 3;
			else
				dir = 2;
			break;
		case '|':
			if (mem == 0)
				dir = 1;
			else
				dir = 0;
			break;
		case '?':
			for (int i = 0; i < 4; i++) {
				if (visit[x][y][mem][i]) {
					return false;
				} else {
					visit[x][y][mem][i] = true;
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (ny >= C) {
						ny = 0;
					} else if (ny < 0) {
						ny = C - 1;
					} else if (nx >= R) {
						nx = 0;
					} else if (nx < 0) {
						nx = R - 1;
					}
					if (isPossible(nx, ny, mem, i)) {
						return true;
					}
				}
			}
			break;
		case '.':
			break;
		case '+':
			mem += 1;
			if (mem > 15)
				mem = 0;
			break;
		case '-':
			mem -= 1;
			if (mem < 0)
				mem = 15;
			break;
		default:
			mem = map[x][y] - '0';
		}
		if (visit[x][y][mem][dir])
			return false;
		else {
			visit[x][y][mem][dir] = true;
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (ny >= C) {
				ny = 0;
			} else if (ny < 0) {
				ny = C - 1;
			} else if (nx >= R) {
				nx = 0;
			} else if (nx < 0) {
				nx = R - 1;
			}
			if (isPossible(nx, ny, mem, dir)) {
				return true;
			}
		}
		return false;
	}
}
