import java.util.Scanner;

public class BOJ1527_금민수의개수 {
	public static int ans = 0;
	public static int A;
	public static int B;
	public static int[] arr = { 4, 7 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		DFS(4, arr[0]);
		DFS(7, arr[1]);
		System.out.println(ans);
	}

	public static void DFS(int init, long start) {
		if (B < start)
			return;
		if (A <= start) {
			System.out.println(start);
			ans++;
		}
		DFS(init, start * 10 + arr[0]);
		DFS(init, start * 10 + arr[1]);
	}
}
