import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3314_D3_보충학습과평균 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sum = 0;
			for (int i = 0; i < 5; i++) {
				int num = Integer.parseInt(st.nextToken());
				sum += num < 40 ? 40 : num;
			}
			sb.append(sum / 5).append('\n');
		}
		System.out.println(sb);
	}
}
