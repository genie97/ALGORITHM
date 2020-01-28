import java.util.Scanner;

public class Main_1169 {
	public static int M, N;
	public static int[] arr;
	public static int index;
	public static boolean[] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		index = 0;
		arr = new int[N];
		visit = new boolean[7];
		if(M==1 || M==3)
			dice_perm(0);
		else
			dice_comb(0,1);
	}
	public static void dice_comb(int cnt, int idx) {
		if (cnt == N) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		} else {
			for (int i = idx; i <= 6; i++) {
				arr[index++] = i;
				dice_comb(cnt + 1, i);
				index--;
			}
		}
	}
	public static void dice_perm(int cnt) {
		if (M == 1) {
			if (cnt == N) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			} else {
				for (int i = 1; i <= 6; i++) {
					arr[index++] = i;
					dice_perm(cnt + 1);
					index--;
				}
			}
		}  else if(M==3) {
			if (cnt == N) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			} else {
				for (int i = 1; i <= 6; i++) {
					if(visit[i]) continue;
					arr[index++] = i;
					visit[i] = true;
					dice_perm(cnt + 1);
					index--;
					visit[i] = false;
				}
			}
		}
	}
}
