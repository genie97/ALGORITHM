import java.util.Scanner;

public class SWEA4408_D4_자기방으로돌아가기 {
	public static int[] visit;
	public static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			visit = new int[201];
			for (int i = 0; i < N; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				if(from > to) {
					int tmp = from;
					from = to;
					to = tmp;
				}
				if (from % 2 == 1)
					from += 1;
				if (to % 2 == 1)
					to += 1;
				for (int j = from / 2; j <= to / 2; j++) {
					visit[j]++;
				}
			}
			int max = 1;
			for (int i = 1; i < visit.length; i++) {
				max = Math.max(max, visit[i]);
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}
