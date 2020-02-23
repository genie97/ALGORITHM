import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1475_방번호 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		// 한세트는 0부터 9까지
		// 방번호가 주어졌을 때 필요한 세트의 개수
		// 9가 2개인 경우는 1세트 가능
		// 6이 2개인 경우에는 1세트 가능
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N.length(); i++) {
			list.add(N.charAt(i) - '0');
		}
		int[] visit = new int[10];
		for (int i = 0; i < list.size(); i++) {
			int item = list.get(i);
			visit[item]++;
		}
		int cnt = (int)(Math.ceil((visit[6] + visit[9]) / 2.0)); // 6이랑 9는 바꿔쓸 수 있음
		int max = visit[0];
		for (int i = 0; i < visit.length; i++) {
			if (i == 6 || i == 9)
				continue;
			if (max < visit[i])
				max = visit[i];
		}
		System.out.println(Math.max(max, cnt));
	}
}
