import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10820_문자열분석 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = "";
		while ((input = br.readLine()) != null) {
			int[] num = new int[4];
			for (int i = 0; i < input.length(); i++) {
				char ch = input.charAt(i);
				if (Character.isDigit(ch)) {
					num[2]++;
				} else if (ch == ' ') {
					num[3]++;
				} else {
					if ('a' <= ch && ch <= 'z') {
						num[0]++;
					} else {
						num[1]++;
					}
				}
			}

			for (int i = 0; i < num.length; i++) {
				sb.append(num[i]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}


