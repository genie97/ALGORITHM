import java.util.Arrays;
import java.util.Scanner;

public class SWEA1984_D2_중간평균값구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int[]arr = new int[10];
			for (int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			int sum = 0;
			for (int i = 1; i < arr.length-1; i++) {
				sum+=arr[i];
			}
			System.out.println("#" + tc + " " + Math.round(sum/8.0));
		}
	}
}
