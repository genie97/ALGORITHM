import java.util.Scanner;

public class BOJ16987_계란으로계란치기 {

	static class Egg {
		int s; // 내구도
		int w; // 무게

		Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}

	static int N;
	static int breakCnt; // 최대횟수
	static boolean[] visit;
	static Egg[] list;
	static Egg[] permList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		list = new Egg[N];
		for (int i = 0; i < N; i++) {
			int a = sc.nextInt(); // 무게
			int b = sc.nextInt(); // 내구도
			list[i] = new Egg(a, b);
		}
		visit = new boolean[N];
		permList = new Egg[N];
		breakCnt = 0;
		checkEgg(0); // 순열로 확인하기
		System.out.println(breakCnt);
	}

	public static void checkEgg(int cnt) {
		if (cnt == N) {
			breakCnt = Integer.max(breakCnt, count());
			return;
		}
		if (list[cnt].s <= 0) {
			checkEgg(cnt + 1);
			return;
		}
		boolean stop = true;

		for (int i = 0; i < list.length; i++) {
			if (i == cnt || list[i].s <= 0)
				continue;
			list[cnt].s -= list[i].w;
			list[i].s -= list[cnt].w;
			stop = false;
			checkEgg(cnt + 1);
			list[cnt].s += list[i].w;
			list[i].s += list[cnt].w;
		}
		if (stop)
			checkEgg(cnt + 1);

	}

	public static int count() {
		int isBreak = 0;
		for (int i = 0; i < list.length; i++) {
			if(list[i].s<= 0) isBreak++;
		}
		return isBreak;
	}
}
