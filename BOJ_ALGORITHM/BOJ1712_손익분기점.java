import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1712_손익분기점 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long A = Integer.parseInt(st.nextToken()); // 고정비용
		long B = Integer.parseInt(st.nextToken()); // 가변 비용
		long C = Integer.parseInt(st.nextToken()); // 노트북 가격

		// A + B * X대 >= C * X
		// A >= CX - BX
		// A >= X(C-B)
		// X <= A/(C-B)
		// X >= A/(B-C)
		if (C - B == 0) {
			System.out.println(-1);
		} else {
			double res = A / (C - B);
			if (res < 0) {
				System.out.println(-1);
			} else {
				System.out.println((int) res + 1);
			}
		}
	}

}
