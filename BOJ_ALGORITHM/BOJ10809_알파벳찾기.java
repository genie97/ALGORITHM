import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10809_알파벳찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();

		int a = 'a';

		for (int i = 0; i < 26; i++) {
			char ch = (char) (a + i);
			sb.append(str.indexOf(ch)).append(' ');
		}

		System.out.println(sb);
	}
}
