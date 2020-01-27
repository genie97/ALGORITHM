import java.util.Arrays;
import java.util.Scanner;

public class PR04_SubsetZeroSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		boolean flag = false;
		for (int i = 0; i < (1 << arr.length); i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				if ((i & (1 << j)) != 0) {
					sum += arr[j];
					if (sum == 0)
						flag = true;
				}
			}
		}
		if(flag) System.out.println("부분집합의 합이 0이 되는 것이 존재한다.");
		else System.out.println("부분집합의 합이 0이 되는 것이 존재하지 않는다.");
	}
}
