import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2675_문자열반복 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			String S = st.nextToken();
			for (int i = 0; i < S.length(); i++) {
				for (int j = 0; j < R; j++) {
					sb.append(S.charAt(i));
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
