import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1725_히스토그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();
		long area = 0;
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			while (!s.isEmpty() && arr[i] < arr[s.peek()]) {
				long h = arr[s.pop()];
				int w = i;
				if (!s.isEmpty()) {
					w = i - s.peek() - 1;
				}
				area = Math.max(area, h * w);
			}
			s.add(i);
		}

		while (!s.isEmpty()) {
			int cw = s.pop();
			int w = N;
			if (!s.isEmpty()) {
				w = N - s.peek() - 1;
			}
            area = Math.max(area, arr[cw] * w);
		}

		System.out.println(area);
	}
}
