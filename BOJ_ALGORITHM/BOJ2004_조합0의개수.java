import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2004_조합0의개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());

		// nCr = n! / (r! * (n-r)!)
		long two = get(N, 2) - get(M, 2) - get(N - M, 2);
		long five = get(N, 5) - get(M, 5) - get(N - M, 5);

		System.out.println(Math.min(two, five));
	}

	static long get(long num, int target) {
		int cnt = 0;

		// 소인수 분해 확인하는 것이 시간초과를 해결할 수 있는 방법
		// EX) 20!에서 5를 소인수로 갖는 숫자의 개수
		// 1. 5 10 15 20
		// 2. 위에 수를 다시 5로 나누어 확인하면
		// 3. 1 2 3 4
		// 4. 위에 수에서는 5를 소인수로 가지는 숫자가 없으므로 
		// 총 개수는 4
		
		while (num >= target) {
			cnt += (num / target);
			num /= target;
		}
		return cnt;
	}
}
