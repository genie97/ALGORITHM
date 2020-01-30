import java.util.Scanner;

public class Main_552 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 0 + i; j++) {
				System.out.print(" ");
			}
			for (int j = 0 + i; j < 2 * N - 1 - i; j++) {
				System.out.print("*");
			}
			for (int j = 2 * N - 1 - i; j < 2 * N - 1; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
