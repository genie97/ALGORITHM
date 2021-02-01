import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//10000이하의 자연수로 이루어진 N짜리 수열
//그 합이 S이상 되는 것 중 가장 짧은 것의 길이
//N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)
//전형적인 슬라이딩 윈도우 (투포인터 알고리즘)

public class BOJ1806_부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int ans = Integer.MAX_VALUE;

		while (true) {
			if (start == end && end == N) {
				break;
			}
			if (sum < S && end + 1 <= N) {
				sum += arr[end];
				end++;
			} else if (sum >= S || end == N) {
				// 앞쪽 포인터를 옮기기 전에 합이 S이상이면 길이 확인하기
				if (sum >= S) {
					ans = Math.min(ans, end - start);
				}
				// 밖의 조건문에서 확인할 경우, 제일 마지막을 확인 못할 수 있으므로 
				// 안쪽에서 다시 확인하기
				if (start + 1 <= N) {
					sum -= arr[start];
					start++;
				}
			}
		}

		// 그러한 합을 만드는 것이 불가능하다면? 0을 출력하라
		if (ans == Integer.MAX_VALUE)
			ans = 0;

		System.out.println(ans);
	}
}
