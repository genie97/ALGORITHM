import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// iSharp 언어 정의
// 기본 변수형
// [] 배열
// & 참조
// * 포인터
// 배열, 참조, 포인터는 순서에 상관없이 혼합 사용 가능
// int의 참조의 참조의 배열의 포인터도 올바른 타입 int&&[]*
// 여러 개의 변수를 한줄에 정의할 수 있다
// 변수의 오른편에 있는 변수명은 뒤집어서 붙힌다

public class BOJ3568_iSharp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), ",");
		StringBuilder sb = new StringBuilder();

		ArrayList<String> values = new ArrayList<>();
		String type = "";
		int i = 0;
		while (st.hasMoreElements()) {
			String str = st.nextToken().replace(";", "");

			if (i == 0) {
				int idx = str.indexOf(" ");
				type = str.substring(0, idx);
				String v = str.substring(idx + 1);
				values.add(v);
				i++;
				continue;
			}
			str = str.trim();
			values.add(str);
		}
		for (int j = 0; j < values.size(); j++) {
			String value = values.get(j);
			String convertValue = convert(value);
			sb.append(type).append(convertValue).append(';').append('\n');
		}
		System.out.println(sb);
	}

	static String convert(String value) {
		String alpha = "";
		String another = "";
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			if ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z') {
				alpha += ch;
			} else {
				another += ch;
			}
		}
		another = another.trim();
		StringBuilder tmp = new StringBuilder(another).reverse();

		for (int i = 0; i < tmp.length(); i++) {
			char ch = tmp.charAt(i);
			if (ch == '[') {
				tmp.setCharAt(i - 1, '[');
				tmp.setCharAt(i, ']');
			}
		}
		return tmp.append(' ').append(alpha).toString();
	}

}
