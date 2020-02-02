import java.util.Scanner;

public class SWEA1979_D2_어디에단어가들어갈수있을까 {
	public static int[][] map;
	public static int N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N+1][N+1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1) {
						ans += check_puzzle(i, j);
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	} // end of main

	public static int check_puzzle(int x, int y) {
		int cnt = 0;
		int blank = 1;

		for (int i = x + 1; i <= N; i++) {
			if (map[i][y] == 1) {
				blank++;
			} else
				break;
		}
		if (blank == K && map[x - 1][y] != 1)
			cnt++;
		blank = 1;
		for (int i = y + 1; i <= N ; i++) {
			if (map[x][i] == 1) {
				blank++;
			} else
				break;
		}
		if (blank == K && map[x][y - 1] != 1)
			cnt++;
		return cnt;
	}
}
