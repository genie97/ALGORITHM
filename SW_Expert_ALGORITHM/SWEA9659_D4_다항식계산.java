import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9659_D4_다항식계산 {
	static final int MOD = 998244353;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			int[] t = new int[51];
			int[] a = new int[51];
			int[] b = new int[51];
			long[] x = new long[51];
			long[] ans = new long[51];

			StringTokenizer st = null;
			for (int i = 2; i <= N; i++) { // n은 2~~50
				st = new StringTokenizer(br.readLine(), " ");
				t[i] = Integer.parseInt(st.nextToken());    //ti
				a[i] = Integer.parseInt(st.nextToken());   //ai
				b[i] = Integer.parseInt(st.nextToken());   //bi
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				long xi = Long.parseLong(st.nextToken());
				ans[0] = 1;   //f0(x) = 1 
				ans[1] = xi;  //f1(x) = x;
				for (int j = 2; j <= N; j++) {
					switch (t[j]) {
					case 1:
						ans[j] = (ans[a[j]] + ans[b[j]]) % MOD;
						break;
					case 2:
						ans[j] = (a[j] * ans[b[j]]) % MOD;
						break;
					case 3:
						ans[j] = (ans[a[j]] * ans[b[j]]) % MOD;
						break;
					}
				}
				sb.append(ans[N]).append(' ');
			}
            sb.append('\n');
		}
		System.out.println(sb);
	}
}
