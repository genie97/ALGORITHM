import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1786_찾기 {
	static ArrayList<Integer> pos;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		pos = new ArrayList<>();
		KMP(origin, pattern);
		StringBuilder sb = new StringBuilder();
		sb.append(pos.size()).append('\n');
		for (int i = 0; i < pos.size(); i++) {
			sb.append(pos.get(i)).append(' ');
		}
		System.out.println(sb);
	}

	static void KMP(String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0;
		for (int i = 0; i < origin.length(); i++) {
			while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (origin.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) { // 패턴 길이 끝까지 다 봤다면?
					cnt++;
					pos.add(i - pattern.length() + 2);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}

	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { // 다를 때까지 i를 이동
				j = pi[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) { // 같아지면 pi배열에 저장
				pi[i] = ++j;
			}
		}
		return pi;
	}

}
