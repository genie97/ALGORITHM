import java.util.Scanner;

public class Main_146 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char c = 'A';
		int num = 0;
		for (int i = n; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print(c++ + " ");
			}
			for (int j = i; j < n; j++) {
				System.out.print(num++ + " ");
			}
			System.out.println();
		}
	}
}
