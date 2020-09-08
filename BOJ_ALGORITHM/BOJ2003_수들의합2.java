import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003_수들의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 포인터 두개
		int s = -1;
		int e = -1;
		int S = 0; // 서브셋의 합
		int res = 0;

		while (s <= e) {
			if (e == N - 1 && s == N - 1)
				break;

			if (S >= M || e == N - 1) {
				s++;
				S -= arr[s];

			} else if (S < M || s == e) {
				e++;
				S += arr[e];
			}

			if (S == M) {
				res++;
			}
		}

		System.out.println(res);
	}
}
