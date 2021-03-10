import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4406_D3_모음이보이지않는사람 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			str = str.replaceAll("[aeiou]", "");
			sb.append('#').append(tc).append(' ').append(str).append('\n');
		}
		System.out.println(sb);
	}
}
