import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2161_카드1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> stack = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			stack.add(i);
		}
		while (!stack.isEmpty()) {
			int pop_item = stack.remove(0);
			System.out.print(pop_item + " ");
			if (stack.size() == 0)
				break;
			int back_item = stack.remove(0);
			stack.add(back_item);
		}
	}
}
