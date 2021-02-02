import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2143_두배열의합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 목표 합

		int N = Integer.parseInt(br.readLine()); // A
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine()); // B
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		// 1. A의 구간합 리스트를 구한다
		ArrayList<Integer> a_group = new ArrayList<>();
		int len = 1;
		while (len <= A.length) {
			for (int i = 0; i <= A.length - len; i++) {
				int sum = 0;
				for (int j = i; j < i + len; j++) {
					sum += A[j];
				}
				a_group.add(sum);
			}
			len++;
		}

		// 2. B의 구간합 리스트를 구한다
		ArrayList<Integer> b_group = new ArrayList<>();
		len = 1;
		while (len <= B.length) {
			for (int i = 0; i <= B.length - len; i++) {
				int sum = 0;
				for (int j = i; j < i + len; j++) {
					sum += B[j];
				}
				b_group.add(sum);
			}
			len++;
		}

		// 3. A,B를 소팅한다
		Collections.sort(a_group);
		Collections.sort(b_group);

		// 4. 투포인터로 확인한다 (a는 작은수, b는 큰수부터 확인하자) 
		int a_size = a_group.size();
		int b_size = b_group.size();

		int ap = 0; // a포인터
		int bp = b_size - 1; // b포인터
		long ans = 0;

		while (ap < a_size && bp >= 0) {
			int a_num = a_group.get(ap);
			int b_num = b_group.get(bp);
			long a_cnt = 0;
			long b_cnt = 0;
			if (a_num + b_num == T) {
				// => c++에서 upper_bound와 lower_bound를 사용하는 것과 같음
				// T인경우, 같은 숫자가 있는지 확인하기 위해 a_num값이 더 있는지 확인
				while (ap < a_size && a_group.get(ap) == a_num) {
					a_cnt++;
					ap++;
				}

				// T인경우, 같은 숫자가 있는지 확인하기 위해 b_num값이 더 있는지 확인
				while (bp >= 0 && b_group.get(bp) == b_num) {
					b_cnt++;
					bp--;
				}

				// 다 찾았으면 곱으로 모든 경우의 수 구하기
				ans += (a_cnt * b_cnt);
			} else if (a_num + b_num > T) { // T보다 큰 경우 (b의 포인터를 낮춘다)
				bp--;
			} else { // T보다 작은 경우 (a의 포인터를 높힌다)
				ap++;
			}
		}
		System.out.println(ans);
	}
}
