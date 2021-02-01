import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// 수학 문제는 N개의 단어로 이루어짐
// 각 단어는 알파벳 대문자
// 알파벳 대문자를 0~9까지의 숫자로 바꿔서 N개의 수를 합하는 문제

// N개의 단어가 주어짐
// 단어는 알파벳 대문자
// 알파벳은 최대 10개
// 수의 최대 길이는 8

// (풀이1) 수학 + 그리디
// 1. 주어진 알파벳을 자릿수로 생각하고 더해본다
// EX) ABC + BCD = 100A + 10B +C + 100B + 10C + D
//	   110B + 100A + 11C + D
//     앞에 쓴 숫자가 큰 숫자대로 소팅하면 B, A, C, D이다
//     순서래도 9,8,7,6으로 할당하고 각 문자를 숫자로 치환해서 계산한다

public class BOJ1339_단어수학 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int digit = (int) Math.pow(10, str.length() - 1);

			for (int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				if (!map.containsKey(ch)) {
					map.put(ch, digit);
				} else {
					map.put(ch, map.get(ch) + digit);
				}
				digit /= 10;
			}
		}

		ArrayList<Character> list = new ArrayList<>(map.keySet());
		Collections.sort(list, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return Integer.compare(map.get(o2), map.get(o1));
			}
		});

		int ans = 0;
		int num = 9;
		for (int i = 0; i < list.size(); i++) {
			char ch = list.get(i);
			ans += (num * map.get(ch));
			num--;

		}

		System.out.println(ans);
	}

}


//(풀이 1) 브루트포스
//1. 구할 수 있는 알파벳 갯수를 구한다
//2. 각 알파벳에 0~9숫자를 겹치지 않게 정한다
//3. 합을 구해본다


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;

//public class BOJ1339_단어수학 {
//	static int[] alpha;
//	static boolean[] used;
//	static int[] candidate;
//
//	static int total;
//	static String[] word;
//	static long ans;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		alpha = new int[26];
//
//		// 사용하지 않음을 -2로 초기화
//		for (int i = 0; i < alpha.length; i++) {
//			alpha[i] = -2;
//		}
//
//		used = new boolean[10];
//		total = 0;
//
//		int N = Integer.parseInt(br.readLine());
//
//		word = new String[N];
//		for (int i = 0; i < N; i++) {
//			word[i] = br.readLine();
//			// 숫자를 지정해줘야하는 알파벳은 -1로 표기
//			for (int j = 0; j < word[i].length(); j++) {
//				char ch = word[i].charAt(j);
//				if (alpha[(int) (ch - 'A')] == -2) {
//					alpha[(int) (ch - 'A')] = -1;
//					total++;
//				}
//			}
//		}
//		ans = Integer.MIN_VALUE;
//		candidate = new int[total];
//		make_comb(0);
//		System.out.println(ans);
//	}
//
//	static void make_comb(int cnt) {
//		if (cnt == total) {
//			long sum = 0;
//
//			for (int i = 0, idx = 0; i < alpha.length; i++) {
//				if (alpha[i] == -2)
//					continue;
//				alpha[i] = candidate[idx++];
//			}
//
//			for (int i = 0; i < word.length; i++) {
//				sum += calculate(word[i]);
//			}
//			ans = Math.max(ans, sum);
//
//			return;
//		}
//		for (int i = 0; i < 10; i++) {
//			if (used[i])
//				continue;
//			used[i] = true;
//			candidate[cnt] = i;
//			make_comb(cnt + 1);
//			used[i] = false;
//		}
//	}
//
//	static long calculate(String str) {
//		int exp = str.length() - 1;
//		long digit = (long) (Math.pow(10, exp));
//		int sum = 0;
//		for (int i = 0; i < str.length(); i++) {
//			int num = alpha[(int) (str.charAt(i) - 'A')];
//			sum += num * digit;
//			digit /= 10;
//		}
//		return sum;
//	}
//}
