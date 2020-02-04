import java.util.Scanner;

public class SWEA1940_D2_가랏RC카 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int dis = 0; // 초기 속도
			// 가속도 값은 1혹은 2 현재 속도보다 감속 속도가 더 큰경우는 0

			int curSpeed = 0, speed = 0;
			for (int i = 0; i < N; i++) {
				int RCtype = sc.nextInt(); // 가속 혹은 감속 혹은 현재 속도 유지
				switch (RCtype) {
				case 1: // 가속
					speed = sc.nextInt(); // 가속도 값
					curSpeed += speed;
					dis += curSpeed;
					break;
				case 2: // 감속
					speed = sc.nextInt(); // 가속도 값
					curSpeed -= speed;
					if (curSpeed <= 0)
						curSpeed = 0;
					dis += curSpeed;
					break;
				default: // 현재 속도 유지
					dis += curSpeed;
					break;
				}
			}
			System.out.println("#" + tc + " " + dis);
		}
	}
}
