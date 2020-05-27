import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1373_2진수8진수 {
	static StringBuilder sb = new StringBuilder();
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		splitDigit(str.length() - 1);
		System.out.println(sb.reverse());
	}

	static void splitDigit(int idx) {
		if (idx < 0)
			return;
		String tmp = "";
		for (int i = 2; i >= 0; i--) {
			if (idx - i < 0)
				continue;
			tmp += str.charAt(idx - i);
		}
//		System.out.println(tmp);
		sb.append(Integer.toOctalString(Integer.parseInt(tmp, 2)));
		splitDigit(idx - 3);
	}
}
