import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class PRO_두개뽑아서더하기 {
	public static void main(String[] args) {
		int[] numbers = { 2, 1, 3, 4, 1 };
		System.out.println(Arrays.toString(solution(numbers)));
	}

	public static int[] solution(int[] numbers) {
		int[] answer = {};
		Set<Integer> sum = new TreeSet<>();

		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				sum.add(numbers[i] + numbers[j]);
			}
		}
		answer = new int[sum.size()];
		ArrayList<Integer> tmp = new ArrayList<>(sum);
		for (int i = 0; i < tmp.size(); i++) {
			answer[i] = tmp.get(i);
		}
		return answer;
	}
}
