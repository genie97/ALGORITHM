import java.util.Scanner;

public class SWEA1285_D2_아름이의돌던지기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] count = new int[100001];
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int a = sc.nextInt();
				count[Math.abs(a)]++;
				if (min > Math.abs(a)) {
					min = Math.abs(a);
				}
			}
			System.out.println("#" + tc + " " + min + " " + count[min]);
		}
	}
}
