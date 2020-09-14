import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11655_ROT13 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isAlphabetic(ch)) {
				if (ch >= 'A' && ch <= 'Z') {
					int alpha = (int) (ch - 'A');
					alpha += 13;
					alpha %= 26;
					ch = (char) (alpha + 'A');
					res += ch;
				} else {
					int alpha = (int) (ch - 'a');
					alpha += 13;
					alpha %= 26;
					ch = (char) (alpha + 'a');
					res += ch;
				}
			} else {
				res += ch;
			}
		}
		System.out.println(res);
	}
}
