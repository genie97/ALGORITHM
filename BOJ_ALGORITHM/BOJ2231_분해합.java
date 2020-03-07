import java.util.Scanner;

public class BOJ2231_분해합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		for (int i = 1; i < N; i++) {
			int sum = digitSum(i);
			if (sum + i == N) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}

	public static int digitSum(int n) {
		int sum = 0;
		for (int i = n; i > 0; i /= 10) {
			sum += (i % 10);
		}
		return sum;
	}

}
