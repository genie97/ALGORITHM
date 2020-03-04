import java.util.ArrayList;
import java.util.Scanner;

public class SWEA8275_D4_햄스터 {

	public static int N; // 우리 개수
	public static int X; // 한 공간에 0이상 X마리 이하
	public static int M; // 기록 개수
	public static ArrayList<int[]> record; // M개의 기록
	public static int[] cage; // 햄스터 담을 우리
	public static int mCnt; // 최대 합
	public static ArrayList<Integer> ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			X = sc.nextInt();
			M = sc.nextInt();
			record = new ArrayList<int[]>();
			for (int i = 0; i < M; i++) {
				int l = sc.nextInt();
				int r = sc.nextInt();
				int s = sc.nextInt();
				record.add(new int[] { l, r, s });
			}
			mCnt = Integer.MIN_VALUE;
			ans = new ArrayList<Integer>();
			cage = new int[N + 1];
			makePerm(1, 0);
			System.out.print("#" + tc + " ");
			if (mCnt == Integer.MIN_VALUE) {
				System.out.println(-1);
			}else {
				for (int i = 0; i < ans.size(); i++) {
					System.out.print(ans.get(i)+" ");
				}
				System.out.println();
			}
		}
	}

	public static void makePerm(int cur, int sum) {
		if (cur == cage.length) { // cage를 다 채웠다면? 기록에 맞는지 확인하자!
			if (rightRecord()) { // 기록이 다 맞다면?
				if (mCnt < sum) {
					ans.clear();
					mCnt = sum;
					for (int i = 1; i < cage.length; i++) {
						ans.add(cage[i]);
					}
				}
			}
			return;
		}
		for (int i = 0; i <= X; i++) {
			cage[cur] = i;
			makePerm(cur + 1, sum + i);
		}
	}
	
	public static boolean rightRecord() {
		for (int i = 0; i < record.size(); i++) {
			int l = record.get(i)[0];
			int r = record.get(i)[1];
			int s = record.get(i)[2];
			int sum = 0;
			for (int j = l; j <= r; j++) {
				sum += cage[j];
			}
			if (sum != s)
				return false; // 기록이 하나라도 오류라면 false
		}
		return true; // 기록이 모두 맞다면 true
	}
}
