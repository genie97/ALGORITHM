import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054_가장긴바이토닉부분수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		int[] dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}

		int LIS = 0;
		ArrayList<Integer> maxIdx = new ArrayList<>();
		for (int i = 0; i < dp.length; i++) {
			LIS = Math.max(LIS, dp[i]);
		}

		for (int i = 0; i < dp.length; i++) {
			if (LIS == dp[i])
				maxIdx.add(i);
		}

		Arrays.fill(dp, 0);

		int LDS = 0;
		for (int i = 0; i < maxIdx.size(); i++) {
			int start = maxIdx.get(i);
			for (int j = start; j < arr.length; j++) {
				for (int k = start; k < j; k++) {
					if (arr[j] < arr[k] && dp[j] < dp[k] + 1) {
						dp[j] = dp[k] + 1;
					}
				}
			}

			for (int j = start; j < dp.length; j++) {
				LDS = Math.max(LDS, dp[j]);
			}
		}

		System.out.println(LDS + LIS);
	}
}
