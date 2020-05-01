import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658_AD_보물상자비밀번호 {
	static int N, K;
	static int len;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			len = N / 4; // 4의 배수 (숫자 하나의 길이)
			String num = br.readLine();
			TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2) * -1;
				}
			}); // 트리셋
			int idx = 0;
			int cnt = len;
			while (cnt-- > 0) {
//				System.out.println("제일 맨 처음에 보는 idx값 idx: " + idx);
				int rotate = 4;
				int i = idx;
				while (rotate-- > 0) { // 길이가 len인 글자의 첫 인덱스
//					System.out.println("길이가 len인 글자의 첫 인덱스: " + i);
					String temp = "";
					for (int j = 0; j < len; j++) {
						temp += num.charAt((i + j) % N);
					}
					set.add(Integer.parseInt(temp, 16));
//					System.out.println(temp);
					i = (i + len) % N;
				}
				idx = (idx + (len - 1)) % N;
			}
//			System.out.println(Arrays.toString(set.toArray()));
			sb.append('#').append(tc).append(' ').append(set.toArray()[K-1]).append('\n');
		}
		System.out.println(sb);
	}

}
