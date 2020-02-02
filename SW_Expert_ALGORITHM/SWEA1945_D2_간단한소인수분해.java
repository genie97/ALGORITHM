import java.util.Scanner;

public class SWEA1945_D2_간단한소인수분해 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] m = { 2, 3, 5, 7, 11 };
		for (int tc = 1; tc <= T; tc++) {
			int[] ans = new int[5];
			int N = sc.nextInt();
			int idx = 0;
			while (true) {
				if (N == 1 && idx >= 5)
					break;
				// 2 3 5 7 = 210
				while (N % m[idx] == 0) {
					ans[idx]++;
					N = N / m[idx];
					// System.out.println(N);
				}
				idx++;
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < ans.length; i++) {
				System.out.print(ans[i] + " ");
			}
			System.out.println();
		}
	}

}
