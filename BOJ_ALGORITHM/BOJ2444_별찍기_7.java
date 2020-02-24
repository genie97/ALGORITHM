import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2444_별찍기_7 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = N-1; i >=0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = i; j < 2 * N - 1 - i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = i; j < 2 * N - 1 - i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
