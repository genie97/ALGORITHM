import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1373_8진수2진수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			int bit = str.charAt(i) - '0';
			String b = Integer.toBinaryString(Integer.parseInt(bit + "", 8));
			while (i > 0 && b.length() < 3) {
				b = '0' + b;
			}
			sb.append(b);
		}

		System.out.println(sb);

	}
}
