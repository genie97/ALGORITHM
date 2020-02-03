
public class PR09_PowerSetTenSum {
	public static int[] set = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static boolean[] c;
	public static int sum = 0;
	public static int[] subset;
	public static int num=0;
	public static void main(String[] args) {
		c = new boolean[11];
		for (int i = 1; i <=10; i++) {
			subset = new int [11];
			makeTen(0, 0, i);	
		}
	}

	public static void makeTen(int idx, int cnt, int setNum) {
		if (setNum == cnt) {

//			부분 집합 전체 출력
//			for (int i = 0; i < cnt; i++) {
//				System.out.print(subset[i] + " ");
//			}
//			System.out.println();
			int sum = 0;
			for (int i = 0; i < cnt; i++) {
				sum+= subset[i];
			}
			if (sum == 10) {
				for (int i = 0; i < cnt; i++) {
					System.out.print(subset[i]+" ");
				}
				System.out.println();
			}
		}else {
			for (int i = idx; i < set.length; i++) {
				if(c[i]) continue;
				subset[num++] = set[i];
				c[i] = true;
				makeTen(i, cnt+1, setNum);
				c[i] = false;
				num--;
			}
		}
	}
	/*
		public static void makeTen(int idx, int cnt, int setNum) {
		if (setNum == cnt) {

//			부분 집합 전체 출력
//			for (int i = 0; i < cnt; i++) {
//				System.out.print(subset[i] + " ");
//			}
//			System.out.println();
//			int sum = 0;
//			for (int i = 0; i < cnt; i++) {
//				sum+= subset[i];
//			}
			if (sum == 10) {
				for (int i = 0; i < c.length; i++) {
					if(c[i]) {
						System.out.print(i+1 +" ");
					}
				}
//				for (int i = 0; i < cnt; i++) {
//					System.out.print(subset[i]+" ");
//				}
				System.out.println();
			}
		}else {
			for (int i = idx; i < set.length; i++) {
				if(c[i]) continue;
				//subset[num++] = set[i];
				sum+=set[i];
				c[i] = true;
				makeTen(i, cnt+1, setNum);
				c[i] = false;
				sum-=set[i];
				//num--;
			}
		}
	}
	*/
}
