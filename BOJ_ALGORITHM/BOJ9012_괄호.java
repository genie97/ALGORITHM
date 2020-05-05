import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012_괄호 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			sb.append(isVPS(str) ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}

	static boolean isVPS(String str) {
		if (!rightString(str))
			return false;

		Stack<Character> s = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				s.push('(');
			} else {
				if (!s.isEmpty()) {
					s.pop();
				} else {
					s.push(')');
				}
			}
		}
		if (s.isEmpty())
			return true;
		else
			return false;
	}

	// 열린 괄호, 닫힌 괄호 개수가 동일하다면 VPS인지 확인하자
	static boolean rightString(String str) {
		int openBracket = 0;
		int closeBracket = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(')
				openBracket++;
			else
				closeBracket++;
		}
		if (openBracket == closeBracket)
			return true;
		else
			return false;
	}

}
