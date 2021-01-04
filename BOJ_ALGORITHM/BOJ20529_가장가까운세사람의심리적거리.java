import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20529_가장가까운세사람의심리적거리 {
	static String[] people;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			people = new String[N];
			people = str.split(" ");

			// N이 32보다 크다면, 무조건 어떤 종류는 3개 이상이다
			if (N > 32) {
				sb.append(0).append("\n");
				continue;
			}

			int answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					for (int k = j + 1; k < N; k++) {
						int sum = check(people[i], people[j], people[k]);
						answer = Math.min(answer, sum);
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	static int check(String p1, String p2, String p3) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (p1.charAt(i) != p2.charAt(i))
				result++;
			if (p1.charAt(i) != p3.charAt(i))
				result++;
			if (p2.charAt(i) != p3.charAt(i))
				result++;
		}
		return result;
	}
}
