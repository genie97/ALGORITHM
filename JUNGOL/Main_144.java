import java.util.Scanner;

public class Main_144 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= 2 * n - 1 - (i * 2); j++) {
				System.out.print(" ");
			}
			for (int j = 2 * n - (i*2); j < 2 * n - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
