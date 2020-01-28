import java.util.Scanner;

public class Main_1175 {
	public static int M, N;
	public static int[] arr;
	public static int index;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();   // 던진 횟수
		M = sc.nextInt();// 눈의 합
		arr = new int[N];
		dice_sum(0);
	}
	public static void dice_sum(int cnt) {
		if(cnt==N) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum+=arr[i];
			}
			if(sum==M) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println();
			}
		}
		else {
			for (int i = 1; i <= 6; i++) {
				arr[index++] = i;
				dice_sum(cnt + 1);
				index--;
			}
		}
	}
}
