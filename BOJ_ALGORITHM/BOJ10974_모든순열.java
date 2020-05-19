import java.util.Scanner;

public class BOJ10974_모든순열 {
	static int[] list;
	static boolean[] used;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		list = new int[N];
		used = new boolean[N + 1];
		sb = new StringBuilder();
		makePem(N, 0);
		System.out.println(sb);
	}

	static void makePem(int n, int cnt) {
		if (cnt == n) {
			for (int i = 0; i < list.length; i++) {
				sb.append(list[i]).append(' ');
			}
			sb.append('\n');
		}
		for (int i = 1; i <= n; i++) {
			if (used[i])
				continue;
			used[i] = true;
			list[cnt] = i;
			makePem(n, cnt + 1);
			used[i] = false;
		}
	}
}
