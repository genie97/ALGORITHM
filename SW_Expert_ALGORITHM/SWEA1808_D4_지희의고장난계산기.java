import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1808_D4_지희의고장난계산기 {

	public static boolean[] num;
	public static int X; // 어떤 수의 곱이므로 a * b ... 의 인수분해 형태 가능
	public static int[] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			num = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 10; i++) {
				num[i] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			}
			X = Integer.parseInt(br.readLine());
			memo = new int[X + 10];
			for (int i = 0; i < 10; i++) {
				if (num[i])
					memo[i] = 1;
			}
			int ans = divide(X);
			ans = ans == Integer.MAX_VALUE ? -1 : ans + 1;
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static int divide(int xNum) {

		if (memo[xNum] > 0)
			return memo[xNum];

		memo[xNum] = countPush(xNum);

		// xNum 인수 분해
		for (int i = 1; i <= (int) Math.sqrt(xNum); i++) {
			if (xNum % i == 0) {
				int n1 = divide(i);
				int n2 = divide(xNum / i);
				memo[xNum] = Math.min(memo[xNum],
						n1 == Integer.MAX_VALUE || n2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : n1 + n2 + 1);
			}
		}
		return memo[xNum];
	}

	public static int countPush(int xNum) {
		int cnt = 0;
		for (int i = xNum; i > 0; i /= 10) {
			int n = i % 10;
			if (!num[n])
				return Integer.MAX_VALUE;
			cnt++;
		}
		return cnt;
	}

}
