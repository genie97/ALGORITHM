import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// L 커서를 왼쪽으로 한칸 (맨앞이면 무시)
// R 커서를 오른쪽으로 한칸 (맨뒤면 무시)
// B 커서 왼쪽에 있는 문자 삭제 (맨앞이면 무시)
// p$ $문자를 왼쪽에 추가
public class BOJ1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> left_s = new Stack<>(); // 커서 기준 왼쪽에 있는 수들
		Stack<Character> right_s = new Stack<>(); // 커서 기준 오른쪽에 있는 수들
		for (int i = 0; i < str.length(); i++) {
			left_s.add(str.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());

		for (int c = 0; c < M; c++) {
			String[] command = br.readLine().split(" ");
			if (command[0].charAt(0) == 'L') {
				if (left_s.isEmpty()) // 커서가 가장 맨앞이라는 것
					continue;
				right_s.add(left_s.pop());
			} else if (command[0].charAt(0) == 'D') {
				if (right_s.isEmpty()) // 커서가 맨뒤라는 것
					continue;
				left_s.add(right_s.pop());
			} else if (command[0].charAt(0) == 'B') {
				if (left_s.isEmpty())
					continue;
				left_s.pop();
			} else {
				// 커서 왼쪽에 추가
				left_s.add(command[1].charAt(0));
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < left_s.size(); i++) {
			sb.append(left_s.get(i));
		}
		// 오른쪽 수들은 거꾸로 출력
		for (int i = right_s.size() - 1; i >= 0; i--) {
			sb.append(right_s.get(i));
		}

		System.out.println(sb);
	}
}
