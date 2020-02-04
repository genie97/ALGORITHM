import java.util.Scanner;

public class SWEA1948_D2_날짜계산기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] date = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int sm = sc.nextInt();
			int sd = sc.nextInt();
			int em = sc.nextInt();
			int ed = sc.nextInt();

			int sday = date[sm] - sd + 1; 
			int daySum = sday;
			for (int i = sm + 1; i < em; i++) {
				daySum += date[i]; 
			}
			if (sm != em)
				daySum += ed;
			System.out.println("#" + tc + " " + daySum);
		}
	}
}
