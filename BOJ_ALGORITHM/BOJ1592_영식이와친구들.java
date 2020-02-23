import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1592_영식이와친구들 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] ball = new int[N + 1];
		ball[1] = 1;
		int cur = 1;
		int cnt = 1;
		while (true) {
			if (ball[cur] == M)
				break;
			if (ball[cur] % 2 == 1) {
				cur += L;
				if (cur > N)
					cur %= N;

			} else {
				if (cur - L <= 0)
					cur += N;
				cur -= L;
			}
			ball[cur]++;
			cnt++;
		}
		System.out.println(cnt-1);
	}
}
