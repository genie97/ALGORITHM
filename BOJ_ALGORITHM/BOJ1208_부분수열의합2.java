import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하라
// N과 S가 주어진다
public class BOJ1208_부분수열의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		// N이 최대 40이기 때문에 메모리 초과가 발생할 수 있음!
		// 두개의 배열로 나눠서 보자!
		int mid = N / 2;
		int[] left_arr = new int[mid];
		int[] right_arr = new int[N - mid];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			if (i < mid) {
				left_arr[i] = Integer.parseInt(st.nextToken());
			} else {
				right_arr[i - mid] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 모든 부분 수열의 합을 구한다 (1 ≤ N ≤ 40) (연속될 필요가 없으므로 부분집합의 갯수)
		long[] left_sub = new long[(int) (Math.pow(2, mid)) - 1];
		long[] right_sub = new long[(int) (Math.pow(2, N - mid)) - 1];
		//long 으로 선언 => (부분수열의 합 개수가 각 배열당 최대 (2^20)-1 개, (2^20)-1 개이므로 곱해서 INTEGER 범위 넘을 수 있음)
		long ans = 0; 

		int idx = 0;
		for (int i = 1; i < (1 << mid); i++) {
			int sum = 0; 
			for (int j = 0; j < left_arr.length; j++) {
				if ((1 & (i >> j)) == 0)
					continue;
				sum += left_arr[left_arr.length - 1 - j];
			}
			// 한쪽에서만 선택하는 경우
			if (sum == S) {
				ans++;
			}
			left_sub[idx++] = sum;
		}

		idx = 0;
		for (int i = 1; i < (1 << (N - mid)); i++) {
			int sum = 0;
			for (int j = 0; j < right_arr.length; j++) {
				if ((1 & (i >> j)) == 0)
					continue;
				sum += right_arr[right_arr.length - 1 - j];
			}
			// 한쪽에서만 선택하는 경우
			if (sum == S) {
				ans++;
			}
			right_sub[idx++] = sum;
		}

		// 2. 부분수열을 소팅한다.
		Arrays.sort(left_sub);
		Arrays.sort(right_sub);

		// 3. 양쪽에서 포인터를 이동하면서 S인 지점을 찾는다
		int left_max = left_sub.length;
		int right_max = right_sub.length;

		int s = 0;
		int e = right_max - 1;

		while (s < left_max && e >= 0) {
			long l_cnt = 0;
			long r_cnt = 0;
			long a_num = left_sub[s];
			long b_num = right_sub[e];

			if (a_num + b_num == S) {
				while (s < left_max && left_sub[s] == a_num) {
					s++;
					l_cnt++;
				}
				while (e >= 0 && right_sub[e] == b_num) {
					e--;
					r_cnt++;
				}
				ans += (l_cnt * r_cnt);
			} else if (a_num + b_num > S) {
				e--;
			} else {
				s++;
			}
		}
		System.out.println(ans);
	}
}
