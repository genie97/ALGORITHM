import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2615_오목 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[19][19];

		int ax = 0;
		int ay = 0;
		int a = 0;

		for (int i = 0; i < board.length; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < board[i].length; j++, idx += 2) {
				board[i][j] = str.charAt(idx) - '0';
			}
		}

		outer: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0)
					continue;
				int start = board[i][j];
				int ni = i;
				int nj = j;
				// 가로 확인
				int cnt = 0;
				while (nj < board[0].length && board[i][nj] == start) {
					cnt++;
					nj++;
				}
				nj = j - 1;
				while (nj >= 0 && board[i][nj] == start) {
					cnt++;
					nj--;
				}
				if (cnt == 5) {
					a = start;
					ax = i;
					ay = j;
					break outer;
				}

				// 세로확인
				cnt = 0;
				while (ni < board.length && board[ni][j] == start) {
					cnt++;
					ni++;
				}
				ni = i - 1;
				while (ni >= 0 && board[ni][j] == start) {
					cnt++;
					ni--;
				}
				if (cnt == 5) {
					a = start;
					ax = i;
					ay = j;
					break outer;
				}

				// 우측으로 뻗는 대각선
				ni = i;
				nj = j;
				cnt = 0;
				while (ni < board.length && nj < board[0].length && board[ni][nj] == start) {
					cnt++;
					ni++;
					nj++;
				}
				ni = i - 1;
				nj = j - 1;
				while (ni >= 0 && nj >= 0 && board[ni][nj] == start) {
					cnt++;
					ni--;
					nj--;
				}
				if (cnt == 5) {
					a = start;
					ax = i;
					ay = j;
					break outer;
				}

				// 왼쪽으로 뻗는 대각선
				ni = i;
				nj = j;
				cnt = 0;
				while (ni < board.length && nj >= 0 && board[ni][nj] == start) {
					cnt++;
					ni++;
					nj--;
				}
				ni = i - 1;
				nj = j + 1;
				while (ni >= 0 && nj < board[0].length && board[ni][nj] == start) {
					cnt++;
					ni--;
					nj++;
				}
				// 가장 왼쪽에 있는 좌표를 뽑아야함
				if (cnt == 5) {
					a = start;
					ax = i + 4;
					ay = j - 4;
					break outer;
				}

			}
		}
		if (a == 0)
			System.out.println(0 + "\n");
		else
			System.out.println(a + "\n" + (ax + 1) + " " + (ay + 1));
	}

}
