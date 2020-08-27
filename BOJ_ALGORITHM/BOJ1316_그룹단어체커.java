import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1316_그룹단어체커 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int group = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (groupWord(str)) {
				group++;
			}
		}
		System.out.println(group);

	}

	static boolean groupWord(String str) {
		boolean[] visit = new boolean[26];

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!visit[ch - 'a']) {
				visit[ch - 'a'] = true;
			} else {
				if (i > 0 && str.charAt(i - 1) != ch) {
					return false;
				}
			}
		}
		return true;
	}
}
