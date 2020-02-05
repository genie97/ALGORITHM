import java.util.Arrays;
import java.util.Scanner;

public class SWEA6808_D3_규영이와인영이의카드게임 {
	static int[] DecA, DecB, arr;
	static boolean[] checkPerm;
	static int winCount, looseCount, arrIdx;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			DecA = new int[9]; // 규영이 카드패
			DecB = new int[9]; // 인영이 카드패
			boolean[] DecCheck = new boolean[19];
			for (int i = 0; i < 9; i++) {
				DecA[i] = sc.nextInt();
				DecCheck[DecA[i]] = true;
			}
			int idx = 0;
			// 인영이 인덱스 만들기
			for (int i = 1; i < DecCheck.length; i++) {
				if (!DecCheck[i])
					DecB[idx++] = i;
			}
			checkPerm = new boolean[19];
			arr = new int[9];
			arrIdx = 0;
			winCount = 0;
			looseCount = 0;
			cardPerm(0);
			System.out.println("#" + tc + " " + winCount + " " + looseCount);
		}
	}

	public static void cardPerm(int cnt) {
		if (cnt == 9) {
			int flag = GameResult();
			if (flag == 1)
				winCount += 1;
			else if (flag == -1)
				looseCount += 1;
		} else {
			for (int i = 0; i < 9; i++) {
				if (checkPerm[DecB[i]])
					continue;
				arr[arrIdx++] = DecB[i];
				checkPerm[DecB[i]] = true;
				cardPerm(cnt + 1);
				checkPerm[DecB[i]] = false;
				arrIdx--;
			}
		}
	}

	// -1을 리턴하면 규영이가 짐, 0을 리턴하면 규영이가 무승부, 1을 리턴하면 규영이가 이김
	public static int GameResult() {
		int Ascore = 0; // 규영이 총합
		int Bscore = 0; // 인영이 총합

		for (int i = 0; i < 9; i++) {
			if (arr[i] < DecA[i]) {
				Ascore += (arr[i] + DecA[i]);
			} else {
				Bscore += (arr[i] + DecA[i]);
			}
		}
		if (Ascore > Bscore)
			return 1;
		else if (Ascore < Bscore)
			return -1;
		else
			return 0;
	}
}
