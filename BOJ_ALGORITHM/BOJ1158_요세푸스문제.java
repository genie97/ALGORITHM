import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1158_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		int index = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (!list.isEmpty()) {
			index = (index + (K - 1)) % list.size();
			int item = list.remove(index);
			if (list.size() >= 1)
				sb.append(item).append(", ");
			else
				sb.append(item);
		}
		sb.append(">\n");
		System.out.println(sb);
	}
}
