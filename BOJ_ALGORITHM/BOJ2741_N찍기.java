import java.util.Scanner;

public class BOJ2741_N찍기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(i).append("\n");
		}
		System.out.println(sb);
	}
}
