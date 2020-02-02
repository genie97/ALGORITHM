import java.util.Scanner;

public class SWEA1970_D2_쉬운거스름돈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] m = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
		for (int tc = 1; tc <= T; tc++) {
			int[] ans = new int[8];
			int N = sc.nextInt();
			int idx = 0;
			while (true) {
				if (N == 0 || idx >= 8)
					break;		
				ans[idx] = N / m[idx];
				N = N % m[idx];
				idx++;
			}
			System.out.println("#" + tc);
			for (int i = 0; i < ans.length; i++) {
				System.out.print(ans[i] + " ");
			}
			System.out.println();
		}
	}

}
