import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		arr[0] = Integer.parseInt(st.nextToken());
		int max = arr[0];

		for (int i = 1; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = Math.max(arr[i - 1] + num, num);
			max = Math.max(arr[i], max);
		}

		System.out.println(max);

	}
}
