public class PRO_가운데글자가져오기 {
	public static void main(String[] args) {
		String s = "qwer";
		System.out.println(solution(s));
	}

	public static String solution(String s) {
		String answer = "";
		if (s.length() % 2 == 1) {
			answer = s.charAt(s.length() / 2) + "";
		} else {
			answer = s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
		}
		return answer;
	}
}
