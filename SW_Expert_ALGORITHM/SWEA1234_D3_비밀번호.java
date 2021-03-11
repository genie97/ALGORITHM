import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 붙어있는 쌍들은 소거
// 남은 번호를 비밀번호로 만드는것

public class SWEA1234_D3_비밀번호 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			while (true) {
				int idx = isDelete(str);
				if (idx == -1)
					break;
				int s = idx;
				int e = idx + 1;
				while (s >= 0 && e < str.length() && str.charAt(s) == str.charAt(e)) {
					s--;
					e++;
				}
				str = str.substring(0, s + 1) + str.substring(e);
			}
			sb.append(str).append('\n');
		}
		System.out.println(sb);
	}

	static int isDelete(String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) == str.charAt(i + 1))
				return i;
		}
		return -1;
	}
}
