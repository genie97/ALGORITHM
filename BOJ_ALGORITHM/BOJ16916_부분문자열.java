import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ16916_부분문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		System.out.println(KMP(origin, pattern) ? 1 : 0);
	}

	static boolean KMP(String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0;
		for (int i = 0; i < origin.length(); i++) {
			while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (origin.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					return true;
				} else {
					j++;
				}
			}
		}
		return false;
	}

	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}
