import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first = br.readLine();
		String second = br.readLine();

		int[][] memo = new int[second.length() + 1][first.length() + 1];

		int max = 0;
		for (int i = 0; i < memo.length; i++) {
			memo[i][0] = 0;
		}
		for (int i = 0; i < memo[0].length; i++) {
			memo[0][i] = 0;
		}

		for (int i = 0; i < second.length(); i++) {
			for (int j = 0; j < first.length(); j++) {
				if (first.charAt(j) == second.charAt(i)) {
					memo[i + 1][j + 1] = memo[i][j] + 1;
				} else {
					memo[i + 1][j + 1] = Math.max(memo[i + 1][j], memo[i][j + 1]);
				}
				max = Math.max(max, memo[i + 1][j + 1]);
			}
		}

		System.out.println(max);
	}
}
