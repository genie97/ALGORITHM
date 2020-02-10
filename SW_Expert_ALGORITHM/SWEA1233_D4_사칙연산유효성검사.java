import java.util.Scanner;

public class SWEA1233_D4_사칙연산유효성검사 {
	public static String[] tree;
	public static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			result = 0;
			int N = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < N; i++) {
				String[] line = sc.nextLine().split(" ");
				char node = line[1].charAt(0);
				if (N / 2 > i) {
					if (node == '+' || node == '-' || node == '*' || node == '/') {
						result = 1;
					} else {
						result = 0;
					}
				} else {
					if (node == '+' || node == '-' || node == '*' || node == '/') {
						result = 0;
					}
				}
			}
			System.out.println("#" + tc + " " + result);
		}

	}
}
