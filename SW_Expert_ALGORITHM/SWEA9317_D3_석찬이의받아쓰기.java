import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA9317_D3_석찬이의받아쓰기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			String origin = br.readLine();
			String copy = br.readLine();
			for (int i = 0; i < origin.length(); i++) {
				if (origin.charAt(i) != copy.charAt(i))
					N--;
			}
			sb.append(N).append('\n');
		}
		System.out.println(sb);
	}
}
