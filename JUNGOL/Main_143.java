import java.util.Scanner;

public class Main_143 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = i; j < 2 * n -1 - i; j++) {
				System.out.print("*");
			}
			for (int j = 2 * n - 1 - i; j < 2 * n; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}
		for (int i = n-2; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = i; j < 2 * n -1 - i; j++) {
				System.out.print("*");
			}
			for (int j = 2 * n - 1 - i; j < 2 * n; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
