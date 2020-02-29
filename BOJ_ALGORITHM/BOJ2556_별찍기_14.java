import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2556_별찍기_14 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
