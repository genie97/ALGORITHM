import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14002_가장긴증가하는부분수열4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}

		int max = 0;
		int maxIdx = 0;
		for (int i = 0; i < dp.length; i++) {
			if (max < dp[i]) {
				max = dp[i];
				maxIdx = i;
			}
		}

		ArrayList<Integer> res = new ArrayList<>();

		int cur = dp[maxIdx];
		int curIdx = maxIdx;
		res.add(arr[curIdx]);

		for (int i = maxIdx - 1; i >= 0; i--) {
			if (cur == dp[i] + 1 && arr[curIdx] > arr[i]) {
				res.add(arr[i]);
				cur = dp[i];
				curIdx = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append('\n');
		for (int i = res.size() - 1; i >= 0; i--) {
			sb.append(res.get(i)).append(' ');
		}

		System.out.println(sb);
	}
}
