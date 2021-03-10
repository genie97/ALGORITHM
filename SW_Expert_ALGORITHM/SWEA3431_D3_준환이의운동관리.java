import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1주일에 L분 이상 U이하의 운동
// X만큼 운동을 했다면 몇분더 운동을 해야 제한을 맞출지?

public class SWEA3431_D3_준환이의운동관리 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(st.nextToken());
			int U = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			sb.append('#').append(tc).append(' ');
			int ans = 0;
			if (X > U) {
				ans = -1;
			} else if (X < L) {
				ans = L - X;
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
