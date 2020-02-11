import java.util.Scanner;

public class BOJ2559_수열{

	public static int K;
	public static int N;
	public static int max;
	public static int[] temp;
	public static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		temp = new int[N];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = sc.nextInt();
		}
		max = Integer.MIN_VALUE;
		visit = new boolean[N];
		DFS(0, 0);
		System.out.println(max);
	}

	public static void DFS(int cnt, int sum) {
		if (cnt == K) {
			if (sum > max) {
				max = sum;
			}
		}
		for (int i = 0; i < temp.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			sum += temp[i];
			DFS(cnt + 1, sum);
			visit[i] = false;
			sum -= temp[i];
		}
	}
}
