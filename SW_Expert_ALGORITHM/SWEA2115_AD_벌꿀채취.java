import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2115_AD_벌꿀채취 {
	public static int[][] honeyMap;
	public static ArrayList<int[]> list;
	public static int N;
	public static int M;
	public static int C;
	public static int remain;
	public static int powS;
	public static int benefit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honeyMap = new int[N][N];
			list = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					honeyMap[i][j] = Integer.parseInt(st.nextToken());
					list.add(new int[] { i, j });
				}
			}

			benefit = Integer.MIN_VALUE;

			// 일꾼 두명이 공간 2개 선택
			for (int i = 0; i < list.size() - 2; i++) {
				int x1 = list.get(i)[0];
				int y1 = list.get(i)[1];
				if (y1 + (M - 1) >= N)
					continue;
				for (int j = i + 1; j < list.size() - 1; j++) {
					int x2 = list.get(j)[0];
					int y2 = list.get(j)[1];
					if (x1 == x2) {
						if (y1 + (M - 1) >= y2)
							continue;
					}
					if (y2 + (M - 1) >= N)
						continue;
//					System.out.println("[" + x1 + ", " + y1 + "]" + "[" + x2 + ", " + y2 + "]");
					powS = 0;
					maxBenefit(x1, y1, x2, y2);
//					System.out.println(value);
					if (powS > benefit) {
						benefit = powS;
					}
				}
			}
			System.out.println("#" + tc + " " + benefit);
		}
	}

	public static void maxBenefit(int x1, int y1, int x2, int y2) {
		getHoney(x1, y1, 0, 0, powS); // 위치 / cnt / sum /powSum
		getHoney(x2, y2, 0, 0, powS);
	}

	public static void getHoney(int x, int y, int cnt, int sum, int powSum) {
		if (sum > C)
			return;
		if (cnt == M) {
			if (powS < powSum) {
				powS = powSum; // 이득 저장
			}
			return;
		}
		getHoney(x, y + 1, cnt + 1, sum + honeyMap[x][y], powSum + (honeyMap[x][y] * honeyMap[x][y]));
		getHoney(x, y + 1, cnt + 1, sum, powSum);

	}
}
