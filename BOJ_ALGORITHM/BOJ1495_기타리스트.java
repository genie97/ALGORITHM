import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연주할 N개의 곡을 연주하고 있다.
// 바꿀 수 있는 볼륨의 리스트를 만들었다.
// V[i]는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨
// 항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있다.
// 현재 볼륨이 P이고 i번째 곡을 연주하기 전이라면, i번 곡은 P+V[i]나 P-V[i]로 연주해야한다
// 0보다 작은 값 볼륨이나, M보다 큰 값으로 볼륨을 바꿀 수 없다.

public class BOJ1495_기타리스트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] V = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}

		int[][] memo = new int[N + 1][M + 1]; // n번째곡, 최대 볼륨은 M까지
		memo[0][S] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < memo[i].length; j++) {
				if (memo[i - 1][j] == 1) {
					int iv = j + V[i - 1];
					int dv = j - V[i - 1];
					if (iv <= M) {
						memo[i][iv] = 1;
					}
					if (dv >= 0) {
						memo[i][dv] = 1;
					}
				}
			}
		}
		int maxv = -1;
		for (int i = 0; i < memo[N].length; i++) {
			if (memo[N][i] == 1)
				maxv = i;
		}
		System.out.println(maxv);
	}
}
