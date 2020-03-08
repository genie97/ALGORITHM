import java.util.Arrays;
import java.util.Scanner;

public class BOJ10815_숫자카드 {
	public static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			binarySearch(num);
		}
	}

	public static void binarySearch(int num) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (num < arr[mid]) { // 작은 값이면
				right = mid - 1;
			} else { // 크거나 같은 값
				if (num == arr[mid]) {
					System.out.print(1 + " ");
					return;
				} else {
					left = mid + 1;
				}
			}
		}
		System.out.print(0 + " ");
	}
}
