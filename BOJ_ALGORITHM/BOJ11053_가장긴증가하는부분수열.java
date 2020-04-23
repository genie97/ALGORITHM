import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < LIS.length; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < LIS.length; i++) {
			max = Math.max(max, LIS[i]);
		}
		System.out.println(max);
	}

}
