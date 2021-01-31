import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// 총 81칸
// 각각의 세로줄 가로줄에는 1~9가 한번씩
// 3*3에는 1~9가 한번씩
// 최종으로 채워진 모습 출력
// 빈칸은 0으로 채워짐

// 풀이방법
// 1. 빈칸의 위치를 확인
// 2. 해당 위치에서 가로, 세로, 3x3칸을 확인해서 넣을 수 있는 숫자 후보군 판별
// 3. 해당 후보군을 채운 뒤 다음 빈칸을 확인
// 3-1. 3에서 후보군이 존재하지 않는다는 것은 불가한 조합이라는 것!
// 2-3단계를 반복하면서 채움


public class BOJ2580_스도쿠 {
	static int[][] sdoku;
	static int[][] ans;
	static ArrayList<int[]> blank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdoku = new int[9][9];
		blank = new ArrayList<>();

		for (int i = 0; i < sdoku.length; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < sdoku[i].length; j++, idx += 2) {
				sdoku[i][j] = str.charAt(idx) - '0';
				if (sdoku[i][j] == 0) {
					blank.add(new int[] { i, j });
				}
			}
		}

		ans = new int[9][9];
		fillSdoku(0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].length; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	static void fillSdoku(int cnt) {
		if (cnt == blank.size()) { // 모든 블랭크를 다채웠다
			copy();
			return;
		}
		int x = blank.get(cnt)[0];
		int y = blank.get(cnt)[1];
		ArrayList<Integer> candidate = new ArrayList<>();
		candidate = check(x, y); // 후보군 찾기 (static변수를 사용하면 새로 갱신되므로 다른 객체로 반환해서 사용할 것)
		if (candidate.size() == 0) // 후보군이 없다는 것은 불가능한 조합이라는 것!
			return;
		for (int j = 0; j < candidate.size(); j++) {
			sdoku[x][y] = candidate.get(j);
			fillSdoku(cnt + 1);
			sdoku[x][y] = 0; // 다시 되돌리기
		}
	}

	static void copy() {
		for (int i = 0; i < sdoku.length; i++) {
			for (int j = 0; j < sdoku[i].length; j++) {
				ans[i][j] = sdoku[i][j];
			}
		}
	}

	static ArrayList<Integer> check(int x, int y) {
		ArrayList<Integer> res = new ArrayList<>();
		boolean[] check = new boolean[10];

		// 가로
		for (int i = 0; i < 9; i++) {
			check[sdoku[x][i]] = true;
		}

		// 세로
		for (int i = 0; i < 9; i++) {
			check[sdoku[i][y]] = true;
		}

		// 9 by 9 확인하기
		if (x < 3 && y < 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 6 && y < 3) {
			for (int i = 3; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 9 && y < 3) {
			for (int i = 6; i < 9; i++) {
				for (int j = 0; j < 3; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 3 && y < 6) {
			for (int i = 0; i < 3; i++) {
				for (int j = 3; j < 6; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 6 && y < 6) {
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 9 && y < 6) {
			for (int i = 6; i < 9; i++) {
				for (int j = 3; j < 6; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 3 && y < 9) {
			for (int i = 0; i < 3; i++) {
				for (int j = 6; j < 9; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 6 && y < 9) {
			for (int i = 3; i < 6; i++) {
				for (int j = 6; j < 9; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		} else if (x < 9 && y < 9) {
			for (int i = 6; i < 9; i++) {
				for (int j = 6; j < 9; j++) {
					check[sdoku[i][j]] = true;
				}
			}
		}

		for (int i = 1; i < check.length; i++) {
			if (!check[i]) {
				res.add(i);
			}
		}
		return res;
	}
}
