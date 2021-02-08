import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 문자열 S가 주어졌을 때 단어만 뒤집는다
// 알파벳 소문자, 숫자, 공백, 특수문자로만 이루어짐
// 문자열의 시작과 끝은 공백이 아님
// < > 가 문자열에 있는 경우 번갈아가면서 등장
// 
public class BOJ17413_단어뒤집기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		Stack<Character> st = new Stack<>();
		// <> 안에 있는 문자들은 안 뒤집힘
		// 띄어쓰기 기준 한단어
		for (int i = 0; i < S.length(); i++) {
			st.add(S.charAt(i));
		}

		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty()) {
			char ch = st.peek();
			if (ch == '>') {
				StringBuilder res = new StringBuilder();
				while (!st.isEmpty() && st.peek() != '<') {
					res.append(st.pop());
				}
				res.append(st.pop());
				sb.insert(0, res.reverse());
			} else {
				StringBuilder res = new StringBuilder();
				while (!st.isEmpty() && st.peek() != ' ' && st.peek() != '>') {
					res.append(st.pop());
				}
				sb.insert(0, res);
				if (!st.isEmpty() && st.peek() == ' ') {
					sb.insert(0, st.pop());
				}
			}
		}
		System.out.println(sb.toString());
	}
}
