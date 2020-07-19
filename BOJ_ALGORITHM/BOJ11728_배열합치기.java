/*
시간초과에 대한 해답
1. A,B 입력자체는 sort가 되어있으므로 merge만 하면 된다.
2. 입력은 Scanner보다는 BufferedReader를 사용한다. (인풋양이 많다)
3. 출력은 System.out.println 보다는 Stringbuilder를 사용한다. (출력양 역시 많다!)
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class BOJ11728_배열합치기 {
	static int[] AB;
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] A = new int[N];
		int[] B = new int[M];

		AB = new int[N + M];
		Random r = new Random();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);
		Arrays.sort(B);

		merge(A, B, 0, 0, 0);

		for (int i = 0; i < AB.length; i++) {
			sb.append(AB[i]).append(' ');
		}
		System.out.println(sb);
	}

	static void merge(int[] a, int[] b, int aIdx, int bIdx, int idx) {
		if (idx == N + M)
			return;

		if (bIdx == b.length || (aIdx != a.length && a[aIdx] <= b[bIdx])) {
			AB[idx] = a[aIdx];
			merge(a, b, aIdx + 1, bIdx, idx + 1);
		} else if (aIdx == a.length || (bIdx != b.length && a[aIdx] > b[bIdx])) {
			AB[idx] = b[bIdx];
			merge(a, b, aIdx, bIdx + 1, idx + 1);
		}
	}
}
