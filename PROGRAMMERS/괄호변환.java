import java.util.Stack;

public class 괄호변환 {
	public static void main(String[] args) {
		System.out.println(solution("()))((()"));
	}

	public static String solution(String p) {
		String answer = "";

		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
		if (p == "")
			return "";
		// 이미 올바른 문자열 이라면 그대로 return 한다
		if (rightString(p))
			return p;

		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u,v로 분리한다
		// u는 균형잡힌 괄호 문자열로 더 이상 분리 할 수 없다
		// v는 빈 문자열이 될 수 있다.
		String u = makeBalance(p);
		int idx = u.length();
		String v = p.substring(idx);

		// 3. u가 올바른 문자열이면 다시 y,v로 나눈다
		// 3-1. u에 이어붙힌 후 반환
		if (rightString(u)) {
			u += solution(v);
			return u;
		}

		// 4. u가 올바른 문자열이 아니라면?
		else {
			// 4-1. 빈문자열에 '(' 붙이기
			String tmp = "";
			tmp += "(";
			// 4-2. 문자열 v에 대해 1부터 재귀 수행
			tmp += solution(v);
			// 4-3. ')' 다시 붙이기
			tmp += ")";
			// 4-4. u의 처음, 마지막 문자 제거, 나머지 문자열의 괄호 방향 뒤집어서 뒤에 붙이기
			u = u.substring(1); // 처음 문자 제거
			u = u.substring(0, u.length()-1); // 나머지 문자 제거
			for (int i = 0; i < u.length(); i++) {
				tmp += (u.charAt(i)) == '(' ? ')' : '(';
			}
			answer = tmp;
		}
		// 4-5. 반환
		return answer;
	}

	static boolean rightString(String p) {
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < p.length(); i++) {
			if (s.isEmpty()) {
				s.push(p.charAt(i));
				continue;
			}
			char a = s.peek();
			char b = p.charAt(i);
			if (a == '(' && b == ')')
				s.pop();
			else
				s.push(b);
		}
		if (s.isEmpty())
			return true;
		else
			return false;
	}

	public static String makeBalance(String p) {
		int openCnt = 0, closeCnt = 0;
		String tmp = "";
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(') {
				openCnt++;
				tmp += '(';
			}
			if (p.charAt(i) == ')') {
				tmp += ')';
				closeCnt++;
			}
			if (openCnt == closeCnt)
				return tmp;
		}
		return tmp;
	}
}
