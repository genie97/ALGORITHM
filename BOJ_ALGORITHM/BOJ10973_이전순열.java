import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10973_이전순열 {
	static int[] list;
	static int N;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		if (prevPermutation()) {
			for (int i = 0; i < N; i++) {
				System.out.print(list[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}

	static boolean prevPermutation() {
		// 1 2 3 4
		// 5 4 3 2 1
		int i = N - 1;
		while (i > 0 && list[i-1] <= list[i])
			--i;
		if (i == 0)
			return false;
		int j = N - 1;
		while (list[i - 1] <= list[j])
			--j;

		int tmp = list[i - 1];
		list[i - 1] = list[j];
		list[j] = tmp;

		int k = N - 1;
		while (i < k) {
			tmp = list[i];
			list[i] = list[k];
			list[k] = tmp;
			++i;
			--k;
		}
		return true;
	}
}
