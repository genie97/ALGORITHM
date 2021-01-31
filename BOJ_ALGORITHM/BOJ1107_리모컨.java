import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// + +1
// - -1
// 0에서 -면 변하지 않음
// 기본이 100
// 한번에 만들 수 있는 수까지 + 혹은 -를 한다
// 최종 길이는 (+횟수 / -횟수) + (N의 자릿수)

public class BOJ1107_리모컨 {
	static boolean[] num;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = new boolean[10];

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		if (M > 0)
			st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			num[n] = true;
		}

		ans = Math.abs(N - 100);
		if (N != 100 && M != 10) { // 이미 100번 채널일때, 모든 키보드가 고장났을때
			// 무한대값을 1000000으로 확인하자
			// 500000이 고장났을 때 눌러볼 수 있는 최대가 7자리
			for (int i = 0; i < 1000000; i++) {
				int len = 0;
				if (i != 100) {
					len = check(i);
				}
				ans = (len == 0) ? ans : Math.min(ans, len + Math.abs(N - i));
			}
		}
		System.out.println(ans);

	}

	static int check(int n) {
		if (n == 0 && !num[n]) // 0을 누를 수 있을 때는 len이 1이다! (반례 포인트)
			return 1;
		int len = 0;
		while (n > 0) {
			int digit = n % 10;
			if (num[digit])
				return 0;
			n /= 10;
			len++;
		}
		return len;
	}
}
