import java.util.Scanner;

public class BOJ2669_직사각형네개의합집합의면적구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] map = new boolean[101][101];
		for (int cnt = 0; cnt < 4; cnt++) {
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			for (int i = sx; i < ex; i++) {
				for (int j = sy; j < ey; j++) {
					if (!map[i][j]) map[i][j] = true;
				}
			}
		}
		int area = 0;

		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				if (map[i][j]) area++;
			}
		}
		System.out.println(area);
	}
}
