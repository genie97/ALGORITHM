import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8673_D3_코딩토너먼트1 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int K = Integer.parseInt(br.readLine());
			int cnt = K;
			K = (int) Math.pow(2, K);

			int[] arr = new int[K];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int sum = 0;

			while (cnt-- > 0) {
				for (int i = 0; i < K; i += 2) {
					sum += Math.abs(arr[i] - arr[i + 1]);
					arr[i / 2] = Math.max(arr[i], arr[i + 1]);
				}
				K /= 2;
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
