import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4530_D4_극한의청소작업 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			A = convert(A);
			B = convert(B);
			long ans = (A > B) ? A - B : B - A;
			sb.append(((A < 0 && B < 0) || (A > 0 && B > 0) ? ans : ans - 1)).append('\n'); // 부호 다르면 0 제외
		}
		System.out.println(sb);
	}

	public static long convert(long f) {
		// 지하인지 아닌지 확인
		boolean under = false;
		if (f < 0) {
			under = true;
		}
		String s = Math.abs(f) + "";
		String res = (under ? "-" : ""); // 리턴해줄 값, 부호는 미리 저장

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) - '0' > 4) {
				res += (s.charAt(i) - '1'); // 4이상의 수는 -1씩
			} else {
				res += (s.charAt(i) - '0'); // 4이하의 수는 그대로
			}
		}

		// 다시 10진수 변환
		int exp = 0;
		long ret = 0L;
		for (int i = res.length() - 1; i >= 0; i--) {
			if (res.charAt(i) == '-')
				continue;
			ret += ((res.charAt(i) - '0') * (long) Math.pow(9, exp++));
		}
		if (under)
			ret *= -1;
		return ret;
	}
}
