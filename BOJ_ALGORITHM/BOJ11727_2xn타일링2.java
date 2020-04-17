import java.util.Scanner;

public class BOJ11727_2xn타일링2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] f = new int[N + 3];
		f[1] = 1;
		f[2] = 3;
		for (int i = 3; i <= N; i++) {
			f[i] = 2 * f[i - 2] + f[i - 1];
			f[i]%=10007;
		}
		System.out.println(f[N]);
	}
}
