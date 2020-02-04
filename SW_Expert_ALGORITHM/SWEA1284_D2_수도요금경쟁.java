import java.util.Scanner;

public class SWEA1284_D2_수도요금경쟁 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// A회사 1리터당 P원의 돈
		// B회사 기본 요금 Q원 월간 사용량이 R리터보다 많으면 R * S요금 더 냄
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int P = sc.nextInt();
			int Q = sc.nextInt();
			int R = sc.nextInt();
			int S = sc.nextInt();
			int W = sc.nextInt();
			int Amoney = P * W;
			int Bmoney = 0;
			if (W > R) {
				Bmoney = Q + ((W - R) * S);
			}else {
				Bmoney = Q;
			}

			System.out.println("#" + tc + " " + Integer.min(Amoney, Bmoney));
		}
	}

}
