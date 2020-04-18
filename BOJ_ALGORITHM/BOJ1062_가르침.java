import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_가르침 {
	static boolean[] alpha;
	static int ans;
	static int N, K;
	static String[] str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 남극언어의 모든 단어는 anta로 시작되고 tica로 끝난다
		// antic는 무조건 가르쳐야함
		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		if (K < 5) {
			System.out.println(0);
		} else {
			alpha = new boolean[26];
			alpha['a' - 'a'] = true;
			alpha['n' - 'a'] = true;
			alpha['t' - 'a'] = true;
			alpha['i' - 'a'] = true;
			alpha['c' - 'a'] = true;
			K -= 5; // 5개 가르침
			// 나머지 글자 가르치기
			ans = 0;
			dfs(0, 0);
			System.out.println(ans);
		}
	}

	static void dfs(int idx, int cnt) {
		if (cnt == K) {
			int word = readWord();
			if (ans < word) {
				ans = word;
			}
			return;
		}
		for (int i = idx; i < alpha.length; i++) {
			if (alpha[i])
				continue;
			alpha[i] = true;
			dfs(i, cnt + 1);
			alpha[i] = false;
		}
	}

	static int readWord() {
		int word = 0;
		for (int i = 0; i < str.length; i++) {
			boolean possible = true;
			for (int j = 0; j < str[i].length(); j++) {
				if (!alpha[str[i].charAt(j) - 'a']) {
					possible = false;
					break;
				}
			}
			if (possible)
				word++;
		}
		return word;
	}
}
