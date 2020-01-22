import java.util.Scanner;

public class SWEA2071_D1_평균값구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				int a = sc.nextInt();
				sum+=a;
			}
			System.out.println(sum);
			System.out.println("#" + tc + " " + (int)Math.round((float)sum/10));
		}
	}
}
