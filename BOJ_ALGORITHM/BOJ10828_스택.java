import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ10828_스택 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			switch (command) {
			case "push":
				int x = Integer.parseInt(st.nextToken());
				s.add(x);
				break;
			case "pop":
				if (!s.isEmpty()) {
					sb.append(s.pop()).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
				break;
			case "size":
				sb.append(s.size()).append('\n');
				break;
			case "empty":
				sb.append(!s.isEmpty() ? 0 : 1).append('\n');
				break;
			case "top":
				if (!s.isEmpty()) {
					sb.append(s.peek()).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
				break;
			}
		}
		System.out.println(sb);
	}
}
