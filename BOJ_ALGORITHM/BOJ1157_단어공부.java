import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1157_단어공부 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		str = str.toUpperCase();

		int[] count = new int[26];

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			count[ch - 'A']++;
		}

		char ch = 'a';
		int max = 0;
		for (int i = 0; i < count.length; i++) {
			max = Math.max(max, count[i]);
		}

		ArrayList<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] == max) {
				indexList.add(i);
			}
			if (indexList.size() > 1) {
				ch = '?';
				break;
			}
		}

		if (indexList.size() == 1) {
			int idx = indexList.get(0);
			ch = (char) (idx + 'A');
		}

		System.out.println(ch);
	}
}
