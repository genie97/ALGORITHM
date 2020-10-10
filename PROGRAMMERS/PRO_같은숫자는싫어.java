import java.util.ArrayList;
import java.util.Arrays;

public class PRO_같은숫자는싫어 {
	public static void main(String[] args) {
		int[] arr = { 4, 4, 4, 3, 3 };
		System.out.println(Arrays.toString(solution(arr)));
	}

	public static int[] solution(int[] arr) {
		int[] answer = {};
		ArrayList<Integer> tmp = new ArrayList<>();
		int cur = arr[0];
		tmp.add(cur);
		for (int i = 1; i < arr.length; i++) {
			if (cur == arr[i])
				continue;
			tmp.add(arr[i]);
			cur = arr[i];
		}
		answer = new int[tmp.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = tmp.get(i);
		}
		return answer;
	}
}
