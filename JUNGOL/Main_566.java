import java.util.Scanner;

public class Main_566 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[101];
		arr[0] = 100;
		arr[1] = sc.nextInt();
		int num = 1;
		while (true) {
			int diff = arr[num - 1] - arr[num];
			arr[++num] = diff;
			if (diff < 0)
				break;
		}
		for (int i = 0; i <= num; i++) {
			System.out.print(arr[i] + " ");
		}

	}
}
