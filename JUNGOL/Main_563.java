import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main_563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//int[] arr = new int[10];
		Integer[] arr = new Integer[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = i; j < arr.length; j++) {
//				if(arr[i]<arr[j]) {
//					int tmp = arr[i];
//					arr[i] = arr[j];
//					arr[j] = tmp;
//				}
//			}
//		}
		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}

}
