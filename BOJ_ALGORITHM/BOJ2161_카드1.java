import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2161_카드1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		while (!q.isEmpty()) {
			int pop_item = q.poll();
			System.out.print(pop_item + " ");
			if (q.size() == 0)
				break;
			int back_item = q.poll();
			q.add(back_item);
		}
	}
}
