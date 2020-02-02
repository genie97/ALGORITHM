import java.util.Scanner;

public class SWEA1976_D2_시각덧셈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] h = new int[2];
		int[] m = new int[2];
		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < 2; i++) {
				h[i] = sc.nextInt();
				m[i] = sc.nextInt();
			}
			int ans_h = 0, ans_m = 0;
			ans_h = h[0] + h[1];
			ans_m = m[0] + m[1];
			if (ans_m >= 60) {
				ans_h += 1;
				ans_m -= 60;
			}
			if(ans_h > 12) {
				ans_h-=12;
			}
			System.out.println("#" + tc + " " + ans_h + " " + ans_m);
		}
	}

}
