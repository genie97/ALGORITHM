import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 수빈이는 현재 S에 있다
// 동생은 A1,A2,...,AN에 있다
// 모든 동생을 찾기 위해 D의 값을 정할때, 최대값은?
// 1초 후에 X+D 혹은 X-D로 이동
public class BOJ17087_숨바꼭질6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ArrayList<Integer> pos = new ArrayList<>();

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			pos.add(Math.abs(S - Integer.parseInt(st.nextToken())));
		}

		int a = pos.get(0);
		for (int i = 1; i < pos.size(); i++) {
			a = gcd(a, pos.get(i));
		}
		System.out.println(a);

	}

	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
