import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SWEA4796_D4_의석이의우뚝선산 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] m = new int[N];
			for (int i = 0; i < N; i++) {
				m[i] = sc.nextInt();
			}
			// 최소 구간 3을 보면서 꼭대기를 찾는다
			ArrayList<Integer> peak = new ArrayList<Integer>();
			for (int i = 1; i < N - 1; i++) {
				if (m[i - 1] < m[i] && m[i] > m[i + 1])
					peak.add(i);
			}
			int ans = 0;
			for (int i = 0; i < peak.size(); i++) {
				int left = 0;
				int idx = peak.get(i);
				// 왼쪽 확인
				while (true) {
					if (idx - 1 >= 0 && m[idx - 1] < m[idx]) {
						left++;
					} else { // 감소하지 않으면 종료
						break;
					}
					idx = idx - 1;
				}
				int right = 0;
				idx = peak.get(i);
				// 오른쪽 확인
				while (true) {
					if (idx + 1 < N && m[idx + 1] < m[idx]) {
						right++;
					} else { // 감소하지 않으면 종료
						break;
					}
					idx = idx + 1;
				}
				ans += (left * right);
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
