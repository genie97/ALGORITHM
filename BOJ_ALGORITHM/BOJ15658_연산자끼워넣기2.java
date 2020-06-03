import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15658_연산자끼워넣기2 {
	static int[] opr, opt;
	static int N;
	static int min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		opr = new int[N];
		opt = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			opr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			opt[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		cal(0, opr[0]);
		System.out.println(max);
		System.out.println(min);
	}

	static void cal(int idx, int res) {
		if (idx == N - 1) {
			min = Math.min(min, res);
			max = Math.max(max, res);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (opt[i] > 0) {
				opt[i]--;
				cal(idx + 1, cal_res(res, i, opr[idx + 1]));
				opt[i]++;
			}
		}
	}

	static int cal_res(int a, int i, int b) {
		switch (i) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			return a / b;
		}
		return 0;
	}

}
