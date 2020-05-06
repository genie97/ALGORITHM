public class PRO_프렌즈4블록 {

	public static void main(String[] args) {
		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };
		int res = solution(m, n, board);
		System.out.println(res);
	}

	static char[][] map;
	static boolean[][] bomb;

	static int solution(int m, int n, String[] board) {
		int answer = 0;
		map = new char[board.length][board[0].length()];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}

		boolean isOver = false;

		while (!isOver) {
			isOver = true;
			bomb = new boolean[map.length][map[0].length];

			for (int i = 0; i < map.length - 1; i++) {
				for (int j = 0; j < map[i].length - 1; j++) {
					if (map[i][j] != '.' && fourBlock(i, j)) { // 블록이 있는 경우만 확인하기
						isOver = false;
						bomb[i][j] = true;
						bomb[i + 1][j] = true;
						bomb[i][j + 1] = true;
						bomb[i + 1][j + 1] = true;
					}
				}
			}
			answer += removeFourBlock();
			downBlock();
		}
		return answer;
	}

	static void downBlock() {
		for (int i = map.length - 1; i >= 0; i--) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '.') {
					int ni = i, nj = j;
					while (--ni >= 0) {
						if (map[ni][nj] != '.') {
							map[i][j] = map[ni][nj];
							map[ni][nj] = '.';
							break;
						}
					}
				}
			}
		}
	}

	static int removeFourBlock() {
		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (bomb[i][j]) {
					cnt++;
					map[i][j] = '.'; // 비었다.
				}
			}
		}
		return cnt;
	}

	static boolean fourBlock(int i, int j) {
		char ch = map[i][j];
		return map[i + 1][j] == ch && map[i][j + 1] == ch && map[i + 1][j + 1] == ch;
	}

}
