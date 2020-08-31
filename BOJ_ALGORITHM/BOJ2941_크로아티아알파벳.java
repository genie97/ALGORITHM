import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2941_크로아티아알파벳 {
	static String[] new_alpha = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };
	static String str;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		int old_len = str.length();
		int cnt = 0;
		int new_len = 0;
		check = new boolean[old_len];

		for (int i = 0; i < new_alpha.length; i++) {
			int res = find(new_alpha[i]);
			if (res > 0) {
				new_len += (new_alpha[i].length() * res);
				cnt += res;
			}
		}
		cnt += (old_len - new_len);
		System.out.println(cnt);
	}

	static int find(String findStr) {
		int res = 0;
		int len = findStr.length();
		for (int i = 0; i < str.length();) {
			String compareStr = "";
			for (int j = i; i + len <= str.length() && j < i + len; j++) {
				if (check[j])
					continue;
				compareStr += str.charAt(j);
			}
			if (compareStr.equals(findStr)) {
				res++;
				for (int j = i; j < i + len; j++) {
					check[j] = true;
				}
				i += len;
			} else {
				i++;
			}
		}
		return res;
	}
}
