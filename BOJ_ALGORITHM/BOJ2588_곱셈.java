import java.util.Scanner;

public class BOJ2588_곱셈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int[] res = new int[3];
		for (int i = num2, idx = 0; i > 0; i /= 10) {
			int rem = i % 10;
			System.out.println(num1 * rem);
			res[idx++] = num1 * rem;
		}
		int sum = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] *= (int) Math.pow(10, i);
			sum += res[i];
		}
		System.out.println(sum);
	}
}
