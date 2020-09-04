import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10773_제로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());

		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < K; i++) {
			int cur = Integer.parseInt(br.readLine());
			if (cur == 0) {
				s.pop();
			} else {
				s.push(cur);
			}
		}

		int sum = 0;

		while (!s.isEmpty()) {
			sum += s.pop();
		}
		System.out.println(sum);
	}
}
