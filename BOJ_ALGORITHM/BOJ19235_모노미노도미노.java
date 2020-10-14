import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 빨간, 파란, 초록 보드가 붙어있는 형태
// 블록은 1x1, 1x2, 2x1이다
// 블록을 놓을 위치를 빨간색 보드에서 선택하면 
// 그 위치부터 초록색 보드로 블록이동, 파란색 보드로 블록이동
// 블록은 다른 블록을 만나거나 경계를 만나기 전까지 계속 이동한다
// 어떤 행이 타일로 가득 차 있다면 그 행의 타일은 모두 사라진다.
// 초록색 보드에서 각 블록이 다른 블록을 만나거나 경계를 만나기전까지 아래로 이동한다.
// 파란색의 경우 열이 타일로 가득 차 있으면, 그 열의 타일이 모두 사라진다.
// 파란색 보드에서 각 블록이 다른 블록을 만나거나 그 경계를 만나기전까지 아래로 이동한다.
// 한 행이나 한 열이 가드가서 사라지면 1점을 획득한다

// 연한 부분은 특별한 칸
// 초록색보드의 0 1번 행에 블록이 있으면, 블록이 있는 행의 수 만큼 아래 행에 있는 타일이 사라지고
// 초록색 보드의 모든 블록이 아래로 경계를 만나기 전까지 이동한다
// 파란색보드의 0 1번 열에 블록이 있으면, 블록이 있는 열의 수 만큼 오른쪽 열에 있는 타일이 사라지고
// 파란색보드의 모든 블록이 오른쪽 경계를 만나기 전까지 이동한다

// 행이나 열이 타일로 가득찬 경우와 연한 칸에 블록이 있는 경우가 동시에 발생할 수 있다
// 행이나 열이 타일로 가득찬 경우가 없을 때까지 점수를 획득하는 과정이 모두 진행된 후,
// 연한 칸에 블록이 있는 경우를 처리한다

// 블록을 놓는 위치가 순서대로 주어졌을 때, 얻은 점수와 초록색 보드와 파란색 보드에 타일이 있는 칸의 개수를 모두 구한다

// 입력
// 블록을 놓는 횟수
// 둘째 줄부터 N개의 줄에 블록을 놓는 정보가 한 줄에 하나씩 t x y 형태
// t=1 1x1블록을 (x,y)에 놓는다
// t=2 1x2블록을 (x, y), (x, y+1)에 놓는다
// t=3 2×1인 블록을 (x, y), (x+1, y)에 놓는다

// 출력
// 첫째줄은 점수
// 둘째줄은 보드에서 타일이 들어있는 칸의 개수 출력

public class BOJ19235_모노미노도미노 {
	static int[][] blueMap; // 세로로 4개 채워졌을 때 지운다 (6열~9열) / 4,5열은 특별 공간
	static int[][] greenMap; // 가로로 4개 채워졌을 때 지운다 (6행~9행) / 4,5행은 특별 공간
	static int N; // 블록 놓는 횟수
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		blueMap = new int[4][10];
		greenMap = new int[10][4];
		score = 0;
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken()); // 1은 1x1 2는 1x2 3은 2x1
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			switch (t) {
			case 1:
				blueMap[x][y] = 1;
				greenMap[x][y] = 1;
				break;
			case 2:
				blueMap[x][y] = 2;
				blueMap[x][y + 1] = 4;

				greenMap[x][y] = 2;
				greenMap[x][y + 1] = 4;
				break;
			case 3:
				blueMap[x][y] = 3;
				blueMap[x + 1][y] = 5;

				greenMap[x][y] = 3;
				greenMap[x + 1][y] = 5;
				break;
			}
			// 파랑 끝까지 이동
			stackBlock(true, x, y, t);
			// 초록 끝까지 이동
			stackBlock(false, x, y, t);

//			System.out.println("==========파란색쌓기==========");
//			for (int j = 0; j < blueMap.length; j++) {
//				System.out.println(Arrays.toString(blueMap[j]));
//			}
//			System.out.println("=========초록색쌓기==========");
//			for (int j = 0; j < greenMap.length; j++) {
//				System.out.println(Arrays.toString(greenMap[j]));
//			}

			// 파랑 풀인지 찾기
			while (findFull(true)) {
				for (int j = blueMap[0].length - 1; j >= 4; j--) {
					for (int k = 0; k < 4; k++) {
						if (blueMap[k][j] > 0 && blueMap[k][j] < 4) {
							stackBlock(true, k, j, blueMap[k][j]);
						}
					}
				}
			}
			// 초록 풀인지
			while (findFull(false)) {
				for (int j = greenMap.length - 1; j >= 4; j--) {
					for (int k = 0; k < 4; k++) {
						if (greenMap[j][k] > 0 && greenMap[j][k] < 4) {
							stackBlock(false, j, k, greenMap[j][k]);
						}
					}
				}
			}

//			System.out.println("==========파랑 터뜨리기==========");
//			for (int j = 0; j < blueMap.length; j++) {
//				System.out.println(Arrays.toString(blueMap[j]));
//			}
//			System.out.println("=========초록 터뜨리기==========");
//			for (int j = 0; j < greenMap.length; j++) {
//				System.out.println(Arrays.toString(greenMap[j]));
//			}

			// 특별한 칸 확인
			int cnt = checkSpecial(true);
			for (int j = 0; j < cnt; j++) {
				removeLast(true);
				// 이동
				for (int idx = blueMap[0].length - 2; idx >= 4; idx--) {
					for (int k = 0; k < 4; k++) {
						if (blueMap[k][idx] > 0 && blueMap[k][idx] < 4) {
							stackBlock(true, k, idx, blueMap[k][idx]);
						}
					}
				}
			}

			// 특별한 칸 확인
			cnt = checkSpecial(false);
			for (int j = 0; j < cnt; j++) {
				removeLast(false);
				// 이동
				for (int idx = greenMap.length - 2; idx >= 4; idx--) {
					for (int k = 0; k < 4; k++) {
						if (greenMap[idx][k] > 0 && greenMap[idx][k] < 4) {
							stackBlock(false, idx, k, greenMap[idx][k]);
						}
					}
				}
			}

//			System.out.println("==========파랑 specail==========");
//			for (int j = 0; j < blueMap.length; j++) {
//				System.out.println(Arrays.toString(blueMap[j]));
//			}
//			System.out.println("=========초록 specail==========");
//			for (int j = 0; j < greenMap.length; j++) {
//				System.out.println(Arrays.toString(greenMap[j]));
//			}
		}
		// 남은 블록 개수 세기
		// 파란 공간
		int blue = countBlock(true);
		// 초록 공간
		int green = countBlock(false);
		System.out.println(score + "\n" + (blue + green));
	}

	static void stackBlock(boolean isBlue, int x, int y, int type) {
		if (isBlue) {
			int idx = y + 1;
			switch (type) {
			case 1:
				while (true) {
					if (idx >= blueMap[0].length)
						break;
					if (blueMap[x][idx] > 0)
						break;

					int tmp = blueMap[x][idx];
					blueMap[x][idx] = blueMap[x][idx - 1];
					blueMap[x][idx - 1] = tmp;

					idx++;
				}
				break;
			case 2:
				idx = y + 2;
				while (true) {
					if (idx >= blueMap[0].length)
						break;
					if (blueMap[x][idx] > 0)
						break;

					int tmp = blueMap[x][idx];
					blueMap[x][idx] = blueMap[x][idx - 1];
					blueMap[x][idx - 1] = tmp;

					tmp = blueMap[x][idx - 1];
					blueMap[x][idx - 1] = blueMap[x][idx - 2];
					blueMap[x][idx - 2] = tmp;
					idx++;
				}
				break;
			case 3:
				while (true) {
					if (idx >= blueMap[0].length)
						break;
					if (blueMap[x][idx] > 0 || blueMap[x + 1][idx] > 0)
						break;

					int tmp = blueMap[x][idx];
					blueMap[x][idx] = blueMap[x][idx - 1];
					blueMap[x][idx - 1] = tmp;

					tmp = blueMap[x + 1][idx];
					blueMap[x + 1][idx] = blueMap[x + 1][idx - 1];
					blueMap[x + 1][idx - 1] = tmp;
					idx++;
				}
				break;
			}

		} else {
			int idx = x + 1;
			switch (type) {
			case 1:
				while (true) {
					if (idx >= greenMap.length)
						break;
					if (greenMap[idx][y] > 0)
						break;

					int tmp = greenMap[idx][y];
					greenMap[idx][y] = greenMap[idx - 1][y];
					greenMap[idx - 1][y] = tmp;
					idx++;

				}
				break;
			case 2:
				while (true) {
					if (idx >= greenMap.length)
						break;
					if (greenMap[idx][y] > 0 || greenMap[idx][y + 1] > 0)
						break;

					int tmp = greenMap[idx][y];
					greenMap[idx][y] = greenMap[idx - 1][y];
					greenMap[idx - 1][y] = tmp;

					tmp = greenMap[idx][y + 1];
					greenMap[idx][y + 1] = greenMap[idx - 1][y + 1];
					greenMap[idx - 1][y + 1] = tmp;
					idx++;
				}
				break;
			case 3:
				idx = x + 2;
				while (true) {
					if (idx >= greenMap.length)
						break;
					if (greenMap[idx][y] > 0)
						break;

					int tmp = greenMap[idx][y];
					greenMap[idx][y] = greenMap[idx - 1][y];
					greenMap[idx - 1][y] = tmp;

					tmp = greenMap[idx - 1][y];
					greenMap[idx - 1][y] = greenMap[idx - 2][y];
					greenMap[idx - 2][y] = tmp;
					idx++;

				}
				break;
			}
		}
	}

	static void removeLast(boolean isBlue) {
		if (isBlue) {
			for (int i = 0; i < 4; i++) {
				if (blueMap[i][9] == 4) {
					blueMap[i][8] = 1;
				}
				blueMap[i][9] = 0;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (greenMap[9][i] == 5) {
					greenMap[8][i] = 1;
				}
				greenMap[9][i] = 0;
			}
		}
	}

	static int checkSpecial(boolean isBlue) {
		int cnt = 0;
		if (isBlue) {
			for (int j = 4; j < 6; j++) {
				for (int i = 0; i < 4; i++) {
					if (blueMap[i][j] > 0) {
						cnt++;
						break;
					}
				}
			}
		} else {
			for (int i = 4; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (greenMap[i][j] > 0) {
						cnt++;
						break;
					}
				}
			}
		}
		return cnt;
	}

	static boolean findFull(boolean isBlue) {
		boolean checkFull = false;

		if (isBlue) {
			for (int i = blueMap[0].length - 1; i >= 6; i--) {
				boolean isFull = true;
				for (int j = 0; j < 4; j++) {
					if (blueMap[j][i] == 0) {
						isFull = false;
						break;
					}
				}
				if (isFull) {
					checkFull = true;
					score++;
					for (int j = 0; j < 4; j++) {
						if (blueMap[j][i] == 2) {
							blueMap[j][i + 1] = 1;
						} else if (blueMap[j][i] == 4) {
							blueMap[j][i - 1] = 1;
						}
						blueMap[j][i] = 0;
					}
				}
			}
		} else {
			for (int i = greenMap.length - 1; i >= 6; i--) {
				boolean isFull = true;
				for (int j = 0; j < 4; j++) {
					if (greenMap[i][j] == 0) {
						isFull = false;
						break;
					}
				}
				if (isFull) {
					checkFull = true;
					score++;
					for (int j = 0; j < 4; j++) {
						if (greenMap[i][j] == 3) {
							greenMap[i + 1][j] = 1;
						} else if (greenMap[i][j] == 5) {
							greenMap[i - 1][j] = 1;
						}
						greenMap[i][j] = 0;
					}
				}
			}
		}
		return checkFull;
	}

	static int countBlock(boolean isBlue) {
		int cnt = 0;
		if (isBlue) {
			for (int i = 0; i < blueMap.length; i++) {
				for (int j = 6; j < blueMap[i].length; j++) {
					if (blueMap[i][j] > 0)
						cnt++;
				}
			}
		} else {
			for (int i = 6; i < greenMap.length; i++) {
				for (int j = 0; j < greenMap[i].length; j++) {
					if (greenMap[i][j] > 0)
						cnt++;
				}
			}
		}
		return cnt;
	}

}
