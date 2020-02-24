import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BOJ2443_별찍기_6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
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
