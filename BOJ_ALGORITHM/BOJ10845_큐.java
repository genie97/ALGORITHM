import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10845_큐 {
	static Queue<Integer> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		q = new LinkedList<Integer>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int item = 0;
			if (command.equals("push")) {
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
		case "push":
			q.offer(item);
			break;
		case "pop":
			if (q.isEmpty()) {
				value = -1;
			} else {
				value = q.poll();
			}
			break;
		case "size":
			value = q.size();
			break;
		case "empty":
			if (q.isEmpty()) {
				value = 1;
			} else {
				value = 0;
			}
			break;
		case "front":
			if (q.isEmpty()) {
				value = -1;
			} else {
				value = q.peek();
			}
			break;
		case "back":
			Queue<Integer> tmp = new LinkedList<>();
			if (q.isEmpty()) {
				value = -1;
			} else {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int n = q.poll();
					tmp.offer(n);
					if (i == size - 1) {
						value = n;
					}
				}
				q = tmp;
			}
			break;
		}
		return value;
	}
}
