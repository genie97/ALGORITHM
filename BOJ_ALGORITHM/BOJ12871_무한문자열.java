import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// s와 t가 같은 문자열을 만들수있는지?
// 1. s와 t가 동일하면? 무조건 1
// 2. s와 t가 동일하지 않다면, 길이의 최소 공배수만큼 늘려본다
  
public class BOJ12871_무한문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		int s_len = s.length();
		int t_len = t.length();
		int lcm = s_len * t_len / get(s_len, t_len);
		boolean isEqual = true;
		for (int i = 0; i < lcm; i++) {
			if (s.charAt(i % s_len) != t.charAt(i % t_len)) {
				isEqual = false;
				break;
			}
		}
		System.out.println(isEqual ? 1 : 0);
	}

	static int get(int a, int b) {
		if (b == 0)
			return a;
		return get(b, a % b);
	}
}
