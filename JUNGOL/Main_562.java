import java.util.Scanner;

public class Main_562 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		int even_sum = 0;
		int odd_sum = 0;
		for (int i = 0; i < 10; i++) {
			if ((i + 1) % 2 == 0) {
				even_sum += arr[i];
			}
			else {
				odd_sum += arr[i];
			}
		}
		System.out.println("sum : " + even_sum);
		System.out.println("avg : " + Math.round((float)odd_sum/5*10)/10.0);
	}
}
