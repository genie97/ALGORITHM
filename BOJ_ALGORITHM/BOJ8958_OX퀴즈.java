import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ8958_OX퀴즈 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int sum = 0;
			int score = 0;
			
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == 'O') {
					score++;
					sum += score;
				} else {
					score = 0;
				}
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
