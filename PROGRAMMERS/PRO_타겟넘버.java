
public class PRO_타겟넘버 {
	static int ans;
	static boolean[] visit;

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		int a = solution(numbers, target);
		System.out.println(a);
	}

	public static int solution(int[] numbers, int target) {
		int answer = 0;
		ans = 0;
		visit = new boolean[numbers.length];
		dfs(numbers, target, 0, 0);
		answer = ans;
		return answer;
	}

	static void dfs(int[] numbers, int target, int cnt, int sum) {
		if (cnt == numbers.length) {
			if (sum == target) {
				ans++;
			}
			return;
		}
		dfs(numbers, target, cnt + 1, sum + numbers[cnt]);
		dfs(numbers, target, cnt + 1, sum - numbers[cnt]);
	}
}
