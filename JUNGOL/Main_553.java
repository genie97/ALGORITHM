import java.util.Scanner;

public class Main_553 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		char ch = 'A';
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-i; j++) {
				System.out.print(ch++);
			}
			System.out.println();
		}

	}

}
