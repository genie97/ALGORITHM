import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4366_D4_정식이의은행업무 {
	static char[] binary;
	static char[] ternary;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			binary = br.readLine().toCharArray();
			ternary = br.readLine().toCharArray();

			for (int i = 0; i < binary.length; i++) {
				binary[i] ^= 1; // 한자리를 한 번 바꿔본다
				String ternaryStr = convertTo3();
				if (isPossible(ternaryStr)) {
					sb.append(Long.parseLong(ternaryStr, 3)).append('\n');
					break;
				}
				binary[i] ^= 1;
			}
		}
		System.out.println(sb);
	}

	static boolean isPossible(String str) {
		if (str.length() != ternary.length) { // 글자수가 다르다면
			int len = Math.abs(ternary.length - str.length());
			for (int i = 0; i < len; i++) {
				str = '0' + str;
			}
		}

		int cnt = 0; // 다른 숫자 개수
		for (int i = 0; i < ternary.length; i++) {
			if (ternary[i] != str.charAt(i))
				cnt++;
			if (cnt >= 2)
				return false;
		}
		return true;
	}

	static String convertTo3() {
		String binaryStr = "";
		for (int i = 0; i < binary.length; i++) {
			binaryStr += binary[i];
		}
		long decimal = Long.parseLong(binaryStr, 2);

		String ternaryStr = "";
		for (long i = decimal; i > 0; i /= 3) {
			ternaryStr = (i % 3) + ternaryStr;
		}
		return ternaryStr;
	}

}
