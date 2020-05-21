/*비트연산 방식*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//S는 집합
// add x S에 추가
// remove x S에서 제거 없으면 무시
// check x x가 있으면 1 없으면 0 => 연산 결과 출력
// toggle x x가 있으면 x제거 없으면 추가
// all S를 {1,2,...20}으로 변경
// empty: S를 공집합으로

public class BOJ11723_집합 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = 0;

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String comm = st.nextToken();
			int x = 0;
			switch (comm) {
			case "add":
				x = Integer.parseInt(st.nextToken()) - 1;
				s = (s | 1 << x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken()) - 1;
				s = (s & ~(1 << x));
				break;
			case "check":
				x = Integer.parseInt(st.nextToken()) - 1;
				sb.append((s & (1 << x)) != 0 ? 1 : 0).append('\n');
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken()) - 1;
				s = s ^ (1 << x);
				break;
			case "all":
				s = (1 << 20) - 1;
				break;
			case "empty":
				s = 0;
				break;
			}
		}
		System.out.println(sb);
	}
}


/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

//S는 집합
// add x S에 추가
// remove x S에서 제거 없으면 무시
// check x x가 있으면 1 없으면 0 => 연산 결과 출력
// toggle x x가 있으면 x제거 없으면 추가
// all S를 {1,2,...20}으로 변경
// empty: S를 공집합으로

public class BOJ11723_집합 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> set = new TreeSet<>();

		// 전체 집합
		ArrayList<Integer> allSet = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			allSet.add(i);
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String comm = st.nextToken();
			int x = 0;
			switch (comm) {
			case "add":
				x = Integer.parseInt(st.nextToken());
				if (!set.contains(x))
					set.add(x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				if (set.contains(x)) {
					set.remove(x);
				}
				break;
			case "check":
				x = Integer.parseInt(st.nextToken());
				sb.append(set.contains(x) ? 1 : 0).append('\n');
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				if (set.contains(x)) {
					set.remove(x);
				} else {
					set.add(x);
				}
				break;
			case "all":
				set.clear();
				set.addAll(allSet);
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		System.out.println(sb);
	}
}
*/
