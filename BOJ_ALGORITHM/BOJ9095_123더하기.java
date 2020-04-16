import java.util.Scanner;

public class BOJ9095_123더하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt();
			long[] f = new long[11];
			f[1] = 1;
			f[2] = 2;
			f[3] = 4;
			for (int i = 4; i <= 10; i++) {
				f[i] = f[i - 1] + f[i - 2] + f[i - 3];
			}
			sb.append(f[N]).append('\n');
		}
		System.out.println(sb);
	}

}
