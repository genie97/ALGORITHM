import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 크기가 n인 삼각형
// 첫번째 줄 숫자는 항상 1
// 두번째 줄부터는 자신의 왼쪽 + 오른쪽 위 숫자의 합
// nCr = n-1Cr-1 + n-1Cr

public class SWEA2005_D2_파스칼의삼각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N + 1][N + 1];

			// 처음은 무조건 1
			arr[1][1] = 1;

			for (int i = 2; i < arr.length; i++) {
				for (int j = 1; j <= i; j++) {
					arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
				}
			}
			sb.append('#').append(tc+1).append('\n');
			for (int i = 1; i < arr.length; i++) {
				for (int j = 1; j <= i; j++) {
					sb.append(arr[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
