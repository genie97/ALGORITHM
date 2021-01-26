import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ18258_큐2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> dq = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String action = st.nextToken();

			switch (action) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				dq.add(num);
				break;
			case "pop":
				if (dq.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dq.pollFirst()).append("\n");
				}
				break;
			case "size":
				sb.append(dq.size()).append("\n");
				break;
			case "empty":
				if (dq.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case "front":
				if (dq.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dq.peekFirst()).append("\n");
				}
				break;
			case "back":
				if (dq.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dq.peekLast()).append("\n");
				}
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
