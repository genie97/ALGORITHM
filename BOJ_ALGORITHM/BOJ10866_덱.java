import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ10866_덱 {
	static Deque<Integer> dq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		dq = new LinkedList<Integer>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int item = 0;
			if (command.contains("push")) {
				item = Integer.parseInt(st.nextToken());
			}
			int n = action(command, item);
			if (n == -2)
				continue;
			sb.append(n).append('\n');
		}
		System.out.println(sb);
	}

	static int action(String command, int item) {
		int value = -2;
		switch (command) {
		case "push_front":
			dq.addFirst(item);
			break;
		case "push_back":
			dq.addLast(item);
			break;
		case "pop_front":
			if (dq.isEmpty()) {
				value = -1;
			} else {
				value = dq.pollFirst();
			}
			break;
		case "pop_back":
			if (dq.isEmpty()) {
				value = -1;
			} else {
				value = dq.pollLast();
			}
			break;
		case "size":
			value = dq.size();
			break;
		case "empty":
			if (dq.isEmpty()) {
				value = 1;
			} else {
				value = 0;
			}
			break;
		case "front":
			if (dq.isEmpty()) {
				value = -1;
			} else {
				value = dq.getFirst();
			}
			break;
		case "back":
			if (dq.isEmpty()) {
				value = -1;
			} else {
				value = dq.getLast();
			}
			break;
		}
		return value;
	}
}
