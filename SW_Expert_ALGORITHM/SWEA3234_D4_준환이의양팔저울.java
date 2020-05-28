import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3234_D4_준환이의양팔저울 {
	static int N;
	static int[] arr;
	static int sum;
	static long ans;
	static boolean[] used;
	
	static int exponential[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };
	static int factorial[] = { 0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sum = 0;
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}
			used = new boolean[N];
			ans = 0;
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void dfs(int l, int r, int cnt) {
		if (cnt == arr.length) {
			ans++;
			return;
		}
		if (l >= sum - l) {
			ans += exponential[N - cnt] * factorial[N - cnt];
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (used[i])
				continue;
			used[i] = true;
			dfs(l + arr[i], r, cnt + 1);
			if (l - (r + arr[i]) >= 0)
				dfs(l, r + arr[i], cnt + 1);
			used[i] = false;
		}
	}
}
