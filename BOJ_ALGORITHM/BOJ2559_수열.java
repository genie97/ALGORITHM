import java.util.Scanner;

public class BOJ2559_수열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] temp = new int[N];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = sc.nextInt();
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= temp.length - K; i++) {
			int sum = 0;
			for (int j = i; j < i + K; j++) {
				sum += temp[j];
			}
			max = Integer.max(sum, max);
		}
		System.out.println(max);
	}
}
