public class PRO_문자열압축 {

	public static void main(String[] args) {
		String str = "aabbaccc";
//		String str = "ababcdcdababcdcd";
//		String str = "abcabcdede";
//		String str = "abcabcabcabcdededededede";
//		String str = "xababcdcdababcdcd";
//		String str = "a";
		System.out.println(Solution(str));
	}

	static int Solution(String s) {
		int answer = Integer.MAX_VALUE;
		if(s.length()==1) { // 문자열의 길이는 1이상 1000이하이다!
			answer = 1;
			return 1;
		}
		int len = s.length() / 2; // 압축시도 길이 (1~전체길이의 절반)
		for (int l = 1; l <= len; l++) {
			String chkStr = "";
			int i = 0;
			for (; i < s.length() - l + 1;) {
				String tmp = s.substring(i, i + l);
				int cnt = putStr(tmp, i + l, s, l);
				if (cnt - 1 == 0) {
					chkStr += tmp;
					i += l;
					continue;
				}
				chkStr += (cnt + tmp);
				i += (cnt * l);
			}
			chkStr += (s.substring(i));
			answer = Math.min(answer, chkStr.length());
		}

		return answer;
	}

	static int putStr(String tmp, int idx, String s, int len) {
		// 마지막 도달
		if (idx >= s.length())
			return 1;

		int cnt = 1;
		for (int i = idx; i < s.length() && i + len <= s.length(); i += len) {
			if (!s.substring(i, i + len).equals(tmp))
				break;
			cnt++;
		}
		return cnt;
	}
}
