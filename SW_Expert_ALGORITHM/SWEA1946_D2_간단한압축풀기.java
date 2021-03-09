import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1946_D2_간단한압축풀기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder str = new StringBuilder();

			sb.append('#').append(tc).append('\n');
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char ch = st.nextToken().charAt(0);
				int cnt = Integer.parseInt(st.nextToken());

				while (cnt > 0) {
					str.append(ch);
					if (str.toString().length() == 10) {
						str.append('\n');
						sb.append(str.toString());
						str = new StringBuilder();
					}
					cnt--;
				}
			}
			if (!str.toString().equals("")) {
				sb.append(str.toString());
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
