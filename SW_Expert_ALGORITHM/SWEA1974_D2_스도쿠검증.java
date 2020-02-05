import java.util.Scanner;

public class SWEA1974_D2_스도쿠검증 {
	public static int[][] sudoku;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			sudoku = new int[9][9];
			for (int i = 0; i < sudoku.length; i++) {
				for (int j = 0; j < sudoku.length; j++) {
					sudoku[i][j] = sc.nextInt();
				}
			}

			boolean flag = false;
			for (int i = 0; i < sudoku.length; i++) {
				if (!checkSudoku(i, 0)) {
					flag = false;
					break;
				}
				flag = true;
			}
			boolean stop = false;
			if (flag) {
				for (int i = 0; i < sudoku.length; i += 3) {
					for (int j = 0; j < sudoku.length; j += 3) {
						if (!checkThree(i, j)) {
							break;
						}
						stop = true;
					}
					if (!stop) {
						System.out.println("#" + tc + " " + 0);
						break;
					}
				}
				if (stop)
					System.out.println("#" + tc + " " + 1);
			} else {
				System.out.println("#" + tc + " " + 0);
			}
		}
	}

	public static boolean checkSudoku(int x, int y) {
		boolean[] num = new boolean[10];
		// 가로
		for (int i = y; i < sudoku.length; i++) {
			if (num[sudoku[x][i]]) {
				return false;
			}
			num[sudoku[x][i]] = true;
		}
		// 세로
		num = new boolean[10];
		for (int j = 0; j < sudoku.length; j++) {
			if (num[sudoku[j][x]]) {
				return false;
			}
			num[sudoku[j][x]] = true;

		}
		return true;
	}

	public static boolean checkThree(int x, int y) {
		boolean[] num = new boolean[10];
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (num[sudoku[i][j]]) {
					return false;
				}
				num[sudoku[i][j]] = true;
			}
		}
		return true;
	}
}
