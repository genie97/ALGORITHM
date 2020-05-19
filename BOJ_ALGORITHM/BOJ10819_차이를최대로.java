import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819_차이를최대로 {
	static int[] list;
	static boolean[] selected;
	static int N;
	static int[] data;
	static int maxDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		data = new int[N];
		selected = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		maxDiff = Integer.MIN_VALUE;
		findMax(0);
		System.out.println(maxDiff);
	}

	static void findMax(int idx) {
		if (idx == N) {
			int res = cal();
			maxDiff = Math.max(res, maxDiff);
			return;
		}
		for (int i = 0; i < data.length; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			list[idx] = data[i];
			findMax(idx + 1);
			selected[i] = false;
		}
	}

	static int cal() {
		int sum = 0;
		for (int i = 1; i < list.length; i++) {
			sum += Math.abs(list[i - 1] - list[i]);
		}
		return sum;
	}

}
