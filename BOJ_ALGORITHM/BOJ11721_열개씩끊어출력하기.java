import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11721_열개씩끊어출력하기 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		for (int i = 0; i < line.length(); i++) {
			sb.append(line.charAt(i));
			if (i % 10 == 9) {
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
