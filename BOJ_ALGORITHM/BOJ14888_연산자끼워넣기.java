import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_연산자끼워넣기 {
	public static int[] operand;
	public static int[] operator;
	public static int N;
	public static int min, max;
	public static int totalCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		operand = new int[N];
		operator = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			operand[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0, idx = 0; i < 4; i++) {
			int op = Integer.parseInt(st.nextToken());
			operator[idx++] = op;
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		dfs(0, operand[0]);
		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int cnt, int res) {
		if (cnt == N - 1) {
			totalCnt++;
			min = Math.min(res, min);
			max = Math.max(res, max);
			return;
		}
		for (int i = 0; i < 4; i++) { 
			if (operator[i] == 0)
				continue;
			int ans = cal(res, operand[cnt + 1], i);
			operator[i]--;
			dfs(cnt + 1, ans);
			operator[i]++;
		}
	}

	public static int cal(int n1, int n2, int op) {
		int res = 0;
		switch (op) {
		case 0:
			res = n1 + n2;
			break;
		case 1:
			res = n1 - n2;
			break;
		case 2:
			res = n1 * n2;
			break;
		case 3:
			res = n1 / n2;
			break;
		}
		return res;
	}
}
