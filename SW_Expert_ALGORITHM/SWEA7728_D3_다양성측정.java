import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SWEA7728_D3_다양성측정 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			String str = br.readLine();
			Set<Integer> s = new HashSet<>();
			for (int i = 0; i < str.length(); i++) {
				s.add(str.charAt(i) - '0');
			}
			sb.append(s.size()).append('\n');
		}
		System.out.println(sb);
	}
}
