import java.util.Scanner;

public class SWEA1288_D2_새로운불면증치료법 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			boolean[] check = new boolean[10];
			int checkNum = 0;
			int time = 1;
			while (true) {
				if(checkNum == 10) {
					time = N * (time-1);
					break;
				}
				String checkStr = (N * time) + "";
				for (int i = 0; i < checkStr.length(); i++) {
					if (!check[checkStr.charAt(i) - '0']) {
						check[checkStr.charAt(i) - '0'] = true;
						checkNum++;
					}
				}
				time++;
			}
			System.out.println("#" + tc + " " + time);
		}
	}
}
