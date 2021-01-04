import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ6549_히스토그램에서가장큰직사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			long[] arr = new long[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			Stack<Integer> s = new Stack<>();
			long area = 0;

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

			sb.append(area).append("\n");
		}
		System.out.println(sb);
	}
}
