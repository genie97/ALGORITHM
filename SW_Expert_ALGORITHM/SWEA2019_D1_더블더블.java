import java.util.Arrays;
import java.util.Scanner;

public class SWEA2019_D1_더블더블 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		for (int i = 0; i <= a; i++) {
			System.out.print((1<<i) + " ");
		}
	}
}
