import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5373_큐빙 {
	static char[][] cube;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			init();

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				String command = st.nextToken();
				char look = command.charAt(0); // U D F B R L
				char dir = command.charAt(1); // + -
				move(look, dir);
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 3; j < 6; j++) {
					sb.append(cube[i][j]);
				}
				sb.append('\n');
			}
		}

		System.out.println(sb);
	}

	static void init() {
		// 큐브 기본 세팅
		cube = new char[12][9];

		// 맨위 흰색
		for (int i = 0; i < 3; i++) {
			for (int j = 3; j < 6; j++) {
				cube[i][j] = 'w';
			}
		}
		// 왼쪽 초록 앞쪽 빨강 오른쪽 파랑
		for (int i = 3; i < 6; i++) {
			String str = "gggrrrbbb";
			for (int j = 0; j < cube[i].length; j++) {
				cube[i][j] = str.charAt(j);
			}
		}

		// 아래 노랑
		for (int i = 6; i < 9; i++) {
			for (int j = 3; j < 6; j++) {
				cube[i][j] = 'y';
			}
		}

		// 뒤 오렌지색
		for (int i = 9; i < 12; i++) {
			for (int j = 3; j < 6; j++) {
				cube[i][j] = 'o';
			}
		}
	}

	static void move(char look, char dir) {
		String l = "";
		String u = "";
		String r = "";
		String d = "";
		round(look, dir);
		switch (look) {
		case 'U':
			for (int i = 0; i < 3; i++) {
				l = l + cube[3][i];
			}
			for (int i = 8; i >= 6; i--) {
				r = r + cube[3][i];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[11][i];
			}
			for (int i = 3; i < 6; i++) {
				d = d + cube[3][i];
			}

			if (dir == '+') {
				// l자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[3][i] = d.charAt(idx);
				}
				// r자리
				for (int i = 8, idx = 0; i >= 6; i--, idx++) {
					cube[3][i] = u.charAt(idx);
				}
				// u자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[11][i] = l.charAt(idx);
				}
				// d자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[3][i] = r.charAt(idx);
				}
			} else {
				// l자리
				for (int i = 2, idx = 0; i >= 0; i--, idx++) {
					cube[3][i] = u.charAt(idx);
				}
				// r자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[3][i] = d.charAt(idx);
				}
				// u자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[11][i] = r.charAt(idx);
				}
				// d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[3][i] = l.charAt(idx);
				}
			}
			break;

		case 'D':
			for (int i = 2; i >= 0; i--) {
				l = l + cube[5][i];
			}
			for (int i = 6; i < 9; i++) {
				r = r + cube[5][i];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[5][i];
			}
			for (int i = 3; i < 6; i++) {
				d = d + cube[9][i];
			}

			if (dir == '+') {
				// l자리
				for (int i = 2, idx = 0; i >= 0; i--, idx++) {
					cube[5][i] = d.charAt(idx);
				}
				// r자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[5][i] = u.charAt(idx);
				}
				// u자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[5][i] = l.charAt(idx);
				}
				// d자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[9][i] = r.charAt(idx);
				}
			} else {
				// l자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[5][i] = u.charAt(idx);
				}
				// r자리
				for (int i = 8, idx = 0; i >= 6; i--, idx++) {
					cube[5][i] = d.charAt(idx);
				}
				// u자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[5][i] = r.charAt(idx);
				}
				// d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[9][i] = l.charAt(idx);
				}
			}
			break;

		case 'F':
			for (int i = 3; i < 6; i++) {
				l = l + cube[i][2];
			}
			for (int i = 3; i < 6; i++) {
				r = r + cube[i][6];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[2][i];
			}
			for (int i = 3; i < 6; i++) {
				d = d + cube[6][i];
			}

			if (dir == '+') {
				// l자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][2] = d.charAt(idx);
				}
				// r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][6] = u.charAt(idx);
				}
				// u자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[2][i] = l.charAt(idx);
				}
				// d자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[6][i] = r.charAt(idx);
				}
			} else {
				// l자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][2] = u.charAt(idx);
				}
				// r자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][6] = d.charAt(idx);
				}
				// u자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[2][i] = r.charAt(idx);
				}
				// d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[6][i] = l.charAt(idx);
				}
			}
			break;

		case 'B':
			for (int i = 5; i >= 3; i--) {
				l = l + cube[i][0];
			}
			for (int i = 5; i >= 3; i--) {
				r = r + cube[i][8];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[8][i];
			}
			for (int i = 3; i < 6; i++) {
				d = d + cube[0][i];
			}

			if (dir == '+') {
				// l자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][0] = d.charAt(idx);
				}
				// r자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][8] = u.charAt(idx);
				}
				// u자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[8][i] = l.charAt(idx);
				}
				// d자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[0][i] = r.charAt(idx);
				}
			} else {
				// l자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][0] = u.charAt(idx);
				}
				// r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][8] = d.charAt(idx);
				}
				// u자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[8][i] = r.charAt(idx);
				}
				// d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[0][i] = l.charAt(idx);
				}
			}
			break;

		case 'R':
			for (int i = 3; i < 6; i++) {
				l = l + cube[i][5];
			}
			for (int i = 11; i >= 9; i--) {
				r = r + cube[i][5];
			}
			for (int i = 2; i >= 0; i--) {
				u = u + cube[i][5];
			}
			for (int i = 6; i < 9; i++) {
				d = d + cube[i][5];
			}

			if (dir == '+') {
				// l자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][5] = d.charAt(idx);
				}
				// r자리
				for (int i = 11, idx = 0; i >= 9; i--, idx++) {
					cube[i][5] = u.charAt(idx);
				}
				// u자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[i][5] = l.charAt(idx);
				}
				// d자리
				for (int i = 8, idx = 0; i >= 6; i--, idx++) {
					cube[i][5] = r.charAt(idx);
				}

			} else {
				// l자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][5] = u.charAt(idx);
				}
				// r자리
				for (int i = 9, idx = 0; i < 12; i++, idx++) {
					cube[i][5] = d.charAt(idx);
				}
				// u자리
				for (int i = 2, idx = 0; i >= 0; i--, idx++) {
					cube[i][5] = r.charAt(idx);
				}
				// d자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[i][5] = l.charAt(idx);
				}
			}
			break;

		case 'L':
			for (int i = 11; i >= 9; i--) {
				l = l + cube[i][3];
			}
			for (int i = 3; i < 6; i++) {
				r = r + cube[i][3];
			}
			for (int i = 0; i < 3; i++) {
				u = u + cube[i][3];
			}
			for (int i = 8; i >= 6; i--) {
				d = d + cube[i][3];
			}

			if (dir == '+') {
				// l자리
				for (int i = 11, idx = 0; i >= 9; i--, idx++) {
					cube[i][3] = d.charAt(idx);
				}
				// r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][3] = u.charAt(idx);
				}
				// u자리
				for (int i = 2, idx = 0; i >= 0; i--, idx++) {
					cube[i][3] = l.charAt(idx);
				}
				// d자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[i][3] = r.charAt(idx);
				}

			} else {
				// l자리
				for (int i = 9, idx = 0; i < 12; i++, idx++) {
					cube[i][3] = u.charAt(idx);
				}
				// r자리
				for (int i = 5, idx = 0; i >= 3; i--, idx++) {
					cube[i][3] = d.charAt(idx);
				}
				// u자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[i][3] = r.charAt(idx);
				}
				// d자리
				for (int i = 8, idx = 0; i >= 6; i--, idx++) {
					cube[i][3] = l.charAt(idx);
				}

			}
			break;
		}
	}

	static void round(char look, char dir) {
		String l = "";
		String u = "";
		String r = "";
		String d = "";
		switch (look) {
		case 'U':
			for (int i = 0; i < 3; i++) {
				l = l + cube[i][3];
				r = r + cube[i][5];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[0][i];
				d = d + cube[2][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[i][3] = d.charAt(idx);
					cube[i][5] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[0][i] = l.charAt(2 - idx);
					cube[2][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[i][3] = u.charAt(2 - idx);
					cube[i][5] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[0][i] = r.charAt(idx);
					cube[2][i] = l.charAt(idx);
				}
			}
			break;

		case 'D':
			for (int i = 6; i < 9; i++) {
				l = l + cube[i][3];
				r = r + cube[i][5];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[6][i];
				d = d + cube[8][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[i][3] = d.charAt(idx);
					cube[i][5] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[6][i] = l.charAt(2 - idx);
					cube[8][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[i][3] = u.charAt(2 - idx);
					cube[i][5] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[6][i] = r.charAt(idx);
					cube[8][i] = l.charAt(idx);
				}
			}
			break;

		case 'F':
			for (int i = 3; i < 6; i++) {
				l = l + cube[i][3];
				r = r + cube[i][5];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[3][i];
				d = d + cube[5][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][3] = d.charAt(idx);
					cube[i][5] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[3][i] = l.charAt(2 - idx);
					cube[5][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][3] = u.charAt(2 - idx);
					cube[i][5] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[3][i] = r.charAt(idx);
					cube[5][i] = l.charAt(idx);
				}
			}
			break;

		case 'B':
			for (int i = 9; i < 12; i++) {
				l = l + cube[i][3];
				r = r + cube[i][5];
			}
			for (int i = 3; i < 6; i++) {
				u = u + cube[9][i];
				d = d + cube[11][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 9, idx = 0; i < 12; i++, idx++) {
					cube[i][3] = d.charAt(idx);
					cube[i][5] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[9][i] = l.charAt(2 - idx);
					cube[11][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 9, idx = 0; i < 12; i++, idx++) {
					cube[i][3] = u.charAt(2 - idx);
					cube[i][5] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[9][i] = r.charAt(idx);
					cube[11][i] = l.charAt(idx);
				}
			}
			break;

		case 'R':
			for (int i = 3; i < 6; i++) {
				l = l + cube[i][6];
				r = r + cube[i][8];
			}
			for (int i = 6; i < 9; i++) {
				u = u + cube[3][i];
				d = d + cube[5][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][6] = d.charAt(idx);
					cube[i][8] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[3][i] = l.charAt(2 - idx);
					cube[5][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][6] = u.charAt(2 - idx);
					cube[i][8] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 6, idx = 0; i < 9; i++, idx++) {
					cube[3][i] = r.charAt(idx);
					cube[5][i] = l.charAt(idx);
				}
			}
			break;

		case 'L':
			for (int i = 3; i < 6; i++) {
				l = l + cube[i][0];
				r = r + cube[i][2];
			}
			for (int i = 0; i < 3; i++) {
				u = u + cube[3][i];
				d = d + cube[5][i];
			}
			if (dir == '+') {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][0] = d.charAt(idx);
					cube[i][2] = u.charAt(idx);
				}
				// u자리 d자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[3][i] = l.charAt(2 - idx);
					cube[5][i] = r.charAt(2 - idx);
				}
			} else {
				// l자리, r자리
				for (int i = 3, idx = 0; i < 6; i++, idx++) {
					cube[i][0] = u.charAt(2 - idx);
					cube[i][2] = d.charAt(2 - idx);
				}
				// u자리 d자리
				for (int i = 0, idx = 0; i < 3; i++, idx++) {
					cube[3][i] = r.charAt(idx);
					cube[5][i] = l.charAt(idx);
				}
			}
			break;
		}
	}
}
