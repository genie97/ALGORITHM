import java.util.Scanner;

public class SWEA2072_D1_홀수만더하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {
			int sum = 0;
			for(int i=0;i<10;i++) {
				int num = sc.nextInt();
				if(num%2 == 1) {
					sum = sum + num;
				}
			}
			System.out.println("#" + testCase + " " + sum);
		}
	} // end of main
}
