import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N개의 자연수
// 이중 K개를 선택 (모두를 한번에 고름)
// 그 수의 점수 계산
// 자신의 수에서 왼쪽에 있는 수 중 선택된 수의 개수를 뺀값
// 전체 점수는 계산된 점수들의 합
// 전체점수가 최대가 되도록 K개의 수를 고른다.

// 제약조건
// 1 ≤ N ≤ 5 000
// 1 ≤ K ≤ N
// 주어지는 자연수는 1 이상 100 000 이하

// 풀이방법
// 1. N개 중에 K개를 선택하여 계산 (60점) => 아무래도 시간초과에 걸렸던 것 같음
// 2. 소팅 후, 가장 큰수부터 K개를 선택, 그리고 순서대로 0,1,2...K-1를 뺀 값의 합이 결국 최대가 됨
// EX) 4 1 5 2 6 3 > 1 2 3 4 5 6 > 선택  4, 5, 6 > (4 - 0) + (5 - 1) + (6 - 2) 



public class BOJ20186_수고르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int diff = K - 1;

		int ans = 0;
		for (int i = arr.length - 1; i >= arr.length - K; i--) {
			ans += (arr[i] - diff);
			diff--;
		}
		System.out.println(ans);
	}
}
