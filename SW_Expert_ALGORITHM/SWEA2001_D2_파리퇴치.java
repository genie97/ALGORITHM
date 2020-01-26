import java.util.Scanner;

public class SWEA2001_D2_파리퇴치 {
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int max = 0;
			for (int i = 0; i <= map.length-M; i++) {
				for (int j = 0; j <= map[i].length-M; j++) {
					if(max < find_area(i,j, map))
						max = find_area(i,j,map);
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
	private static int find_area(int x, int y, int map[][]) {
		int sum = 0;
		for (int i = x; i < x + M; i++) {
			for(int j = y; j < y + M; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
}
