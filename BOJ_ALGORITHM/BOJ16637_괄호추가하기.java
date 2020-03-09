import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16637_괄호추가하기 {

	public static int[] operand;
	public static char[] operator;
	public static int ans;
	public static int N;

	// 중첩 괄호는 안됨
	// 무조건 피연산자 2개 연산자 1개 구성의 괄호 묶음만 가능
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int idx1 = 0, idx2 = 0;
		operand = new int[N / 2 + 1];
		operator = new char[N / 2];
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				operand[idx1++] = str.charAt(i) - '0';
			else
				operator[idx2++] = str.charAt(i);
		}
		ans = Integer.MIN_VALUE;
		dfs(0, operand[0]);
		System.out.println(ans);
	}

	public static void dfs(int idx, int n) {
		if (idx == N / 2) { // 연산자 다 봄
//			System.out.println(n);
			if (ans < n) {
				ans = n;
			}
			return;
		}

    // 앞에서부터 순서대로 괄호를 치는 경우
		int res = cal(n, operator[idx], operand[idx + 1]);
		dfs(idx + 1, res);
    // 괄호를 2개 이상 친다고 생각하는 경우 (앞에서 계산한 인자, 연산자, 괄호로 묶어 계산한 인자)
		if (idx + 1 < N / 2) {
			res = cal(n, operator[idx], cal(operand[idx + 1], operator[idx + 1], operand[idx + 2]));
			dfs(idx + 2, res);
		}
	}

	public static int cal(int n1, char op, int n2) {
		int res = 0;
		switch (op) {
		case '+':
			res = n1 + n2;
			break;
		case '-':
			res = n1 - n2;
			break;
		case '*':
			res = n1 * n2;
			break;
		}
		return res;
	}
}
