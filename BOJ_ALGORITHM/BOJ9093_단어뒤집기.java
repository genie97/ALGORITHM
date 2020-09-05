import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9093_단어뒤집기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int TC = 0; TC < T; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreElements()) {
				String str = st.nextToken();
				StringBuilder rb = new StringBuilder(str);
				sb.append(rb.reverse()).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}
