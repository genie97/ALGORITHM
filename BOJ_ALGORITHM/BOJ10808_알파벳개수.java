import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10808_알파벳개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] count = new int[26];

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			count[(int)(ch - 'a')]++;
		}

		for (int i = 0; i < count.length; i++) {
			System.out.print(count[i] + " ");
		}
	}

}
