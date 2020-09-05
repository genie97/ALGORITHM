import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874_스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> in = new Stack<>();
		StringBuilder sb = new StringBuilder();

		int cur = 1;

		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(br.readLine());
			while (cur < num) {
				in.push(cur);
				sb.append('+').append('\n');
				cur++;
			}
			if (cur == num) {
				in.push(num);
				sb.append('+').append('\n');
				cur++;
			}
			if (in.peek() == num) {
				in.pop();
				sb.append('-').append('\n');
			}
		}
		
		if (in.isEmpty())
			System.out.println(sb);
		else
			System.out.println("NO");
	}
}
