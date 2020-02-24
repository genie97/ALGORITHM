import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2164_카드2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		int pop_item = 0;
		while (!q.isEmpty()) {
			pop_item = q.poll();
			if (q.size() == 0)
				break;
			int back_item = q.poll();
			q.add(back_item);
		}
		System.out.println(pop_item);
	}
}
