import java.util.Arrays;

public class PRO_가사검색 {
	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?" };
		int[] data = solution(words, queries);
		System.out.println(Arrays.toString(data));
	}

	// words 개수: 2개~100000
	// 각 가사 길이: 1~10000
	// 검색 키워드는 중복 가능
	// ?? 접두사 혹은 접미사
	static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		// 1. 접미사 트라이
		Trie[] suffix = new Trie[10001];
		// 2. 접두사 트라이
		Trie[] prefix = new Trie[10001];

		for (String word : words) {
			int len = word.length();
			// 접미사
			if (suffix[len] == null) {
				suffix[len] = new Trie();
				suffix[len].insert(word.toCharArray());
			} else {
				suffix[len].insert(word.toCharArray());
			}
			// 접두사
			String reverse_word = new StringBuilder(word).reverse().toString();
			if (prefix[len] == null) {
				prefix[len] = new Trie();
				prefix[len].insert(reverse_word.toCharArray());
			} else {
				prefix[len].insert(reverse_word.toCharArray());
			}
		}

		for (int i = 0; i < queries.length; i++) {
			String query = queries[i];
			if (query.indexOf('?') == 0) { // prefix
				String reverse_query = new StringBuilder(query).reverse().toString();
				if (prefix[reverse_query.length()] == null) {
					answer[i] = 0;
				} else {
					answer[i] = prefix[reverse_query.length()].search(reverse_query.toCharArray());
				}
			} else { // suffix
				if (suffix[query.length()] == null) {
					answer[i] = 0;
				} else {
					answer[i] = suffix[query.length()].search(query.toCharArray());
				}
			}
		}
		return answer;
	}

	// 트라이 자료구조
	static class Trie {
		int count; // 중복 문자 체크
		Trie[] childList;

		public Trie() {
			this.count = 0;
			this.childList = new Trie[26];
		}

		// 삽입
		void insert(char[] word) {
			Trie cur = this;
			for (char ch : word) {
				++cur.count;
				if (cur.childList[ch - 'a'] != null) { // 이미 한 번 나온 글자
					cur = cur.childList[ch - 'a'];
				} else {
					cur.childList[ch - 'a'] = new Trie();
					cur = cur.childList[ch - 'a'];
				}
			}
		}

		// 검색
		int search(char[] query) {
			Trie cur = this;
			for (char ch : query) {
				if (ch == '?') {
					return cur.count;
				}

				if (cur.childList[ch - 'a'] != null) {
					cur = cur.childList[ch - 'a'];
				} else {
					return 0;
				}
			}
			return cur.count;
		}

	}
}
