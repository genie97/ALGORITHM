import java.util.HashMap;
import java.util.Map;

public class PRO_완주하지못한선수 {
	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < completion.length; i++) {
			if (!map.containsKey(completion[i])) {
				map.put(completion[i], 1);
			} else {
				map.put(completion[i], map.get(completion[i]) + 1);
			}
		}
		for (int i = 0; i < participant.length; i++) {
			if (map.containsKey(participant[i])) {
				map.put(participant[i], map.get(participant[i]) - 1);
			} else {
				return participant[i];
			}
		}
		for (String key : map.keySet()) {
			int cnt = map.get(key);
			if (cnt < 0)
				return key;
		}
		return answer;
	}
}
