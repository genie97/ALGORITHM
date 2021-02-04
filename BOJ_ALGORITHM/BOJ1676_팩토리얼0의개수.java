import java.util.Scanner;

public class BOJ1676_팩토리얼0의개수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// 2*5의 개수를 구하라!
		int two = 0;
		int five = 0;
		for (int i = N; i >= 2; i--) {
			two += check(i, 2);
			five += check(i, 5);
		}
		System.out.println(Math.min(two, five));
	}

	static int check(int num, int target) {
		int cnt = 0;
		while (num % target == 0) {
			cnt++;
			num /= target;
		}
		return cnt;
	}
}
