import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PRO_불량사용자 {

	static ArrayList<Integer> permList;
	static HashSet<String> set;

	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "*rodo", "******", "******" };
		int res = solution(user_id, banned_id);
		System.out.println(res);
	}

	// user_id 배열 크기 1이상 8이하
	// banned_id 배열 크기 1이상 user_id 배열 크기 이하
	static int solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		int N = user_id.length;
		int M = banned_id.length;
		// nPm 을 구한다
		boolean[] visit = new boolean[N];
		set = new HashSet<>();
		permList = new ArrayList<>();
		dfs(user_id, banned_id, N, M, visit, 0);

		answer = set.size();

		return answer;
	}

	static void dfs(String[] user_id, String[] banned_id, int n, int m, boolean[] visit, int cnt) {
		if (cnt == m) {
			String str = makeAnswer(permList);
			set.add(str);
			return;
		}

		String bid = banned_id[cnt];
		for (int i = 0; i < user_id.length; i++) {
			String uid = user_id[i];
			if (uid.length() != bid.length())
				continue;
			if (visit[i])
				continue;
			if (!right(bid, uid))
				continue;

			visit[i] = true;
			permList.add(i);
			dfs(user_id, banned_id, n, m, visit, cnt + 1);
			permList.remove(cnt);
			visit[i] = false;
		}
	}

	static String makeAnswer(ArrayList<Integer> permList) {
		ArrayList<Integer> list = new ArrayList<>();
		list.addAll(permList);
		Collections.sort(list);
		
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str += list.get(i);
		}
		return str;
	}

	static boolean right(String bid, String uid) {
		for (int len = 0; len < uid.length(); len++) {
			if (bid.charAt(len) != '*' && uid.charAt(len) != bid.charAt(len)) // 와일드 카드가 아닐 때, 둘이 같지 않으면 다름
				return false;
		}
		return true;
	}
}
