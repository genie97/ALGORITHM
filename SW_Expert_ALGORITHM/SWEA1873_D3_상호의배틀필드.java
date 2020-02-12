import java.util.Scanner;

public class SWEA1873_D3_상호의배틀필드 {
	public static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static char[][] map;
	public static int N;
	public static int H;
	public static int W;
	public static int sx, sy;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String line = sc.next();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						sx = i;
						sy = j;
					}
				}
			}
			N = sc.nextInt();
			String command = sc.next();
			for (int i = 0; i < N; i++) {
				char c = command.charAt(i);
				switch (c) {
				case 'U':
					up(sx, sy);
					break;
				case 'D':
					down(sx, sy);
					break;
				case 'L':
					left(sx, sy);
					break;
				case 'R':
					right(sx, sy);
					break;
				case 'S':
					shoot(sx, sy, map[sx][sy]);
					break;
				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void shoot(int x, int y, char c) {
		int dir = -1;
		switch (c) {
		case '^':
			dir = 0;
			break;
		case 'v':
			dir = 1;
			break;
		case '<':
			dir = 2;
			break;
		case '>':
			dir = 3;
			break;
		}
		int nx = x;
		int ny = y;
		while (true) {
			nx = nx + dx[dir];
			ny = ny + dy[dir];
			if (nx < 0 || ny < 0 || nx >= H || ny >= W) { // 맵을 나가도 끝
				break;
			}
			if (map[nx][ny] == '*') { // 벽돌벽이면 깨지고 끝
				map[nx][ny] = '.';
				break;
			}
			if (map[nx][ny] == '#') { // 강철벽이면 걍 끝
				break;
			}
		}
	}

	private static void right(int x, int y) {
		if (y + 1 >= W) { // 맵을 나가면 방향만 바뀜
			map[x][y] = '>';
			return;
		}
		int cx = x;
		int cy = y + 1;
		if (map[cx][cy] == '-' || map[cx][cy] == '*' || map[cx][cy] == '#') { // 물이면 방향만 바뀜
			map[x][y] = '>';
			return;
		}
		map[x][y] = '.'; // 원래 있던 자리는 평지로 바꿔주기
		map[cx][cy] = '>'; // 내가 보던 방향을 바꿔주기
		// 방향 업데이트
		sx = cx;
		sy = cy;
	}

	private static void left(int x, int y) {
		if (y - 1 < 0) {
			map[x][y] = '<';
			return;
		}
		int cx = x;
		int cy = y - 1;
		if (map[cx][cy] == '-' || map[cx][cy] == '*' || map[cx][cy] == '#') {
			map[x][y] = '<';
			return;
		}
		map[x][y] = '.'; // 원래 있던 자리는 평지로 바꿔주기
		map[cx][cy] = '<'; // 내가 보던 방향을 바꿔주기
		// 방향 업데이트
		sx = cx;
		sy = cy;
	}

	private static void down(int x, int y) {
		if (x + 1 >= H) {
			map[x][y] = 'v';
			return;
		}
		int cx = x + 1;
		int cy = y;
		if (map[cx][cy] == '-' || map[cx][cy] == '*' || map[cx][cy] == '#') {
			map[x][y] = 'v';
			return;
		}
		map[x][y] = '.'; // 원래 있던 자리는 평지로 바꿔주기
		map[cx][cy] = 'v'; // 내가 보던 방향을 바꿔주기
		// 방향 업데이트
		sx = cx;
		sy = cy;
	}

	private static void up(int x, int y) {
		if (x - 1 < 0) {
			map[x][y] = '^';
			return;
		}
		int cx = x - 1;
		int cy = y;
		if (map[cx][cy] == '-' || map[cx][cy] == '*' || map[cx][cy] == '#') {
			map[x][y] = '^';
			return;
		}
		map[x][y] = '.'; // 원래 있던 자리는 평지로 바꿔주기
		map[cx][cy] = '^'; // 내가 보던 방향을 바꿔주기
		// 방향 업데이트
		sx = cx;
		sy = cy;

	}
}
