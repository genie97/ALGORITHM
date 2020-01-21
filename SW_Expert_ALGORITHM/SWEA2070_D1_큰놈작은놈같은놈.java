import java.util.Scanner;

public class SWEA2070_D1_Å«³ğÀÛÀº³ğ°°Àº³ğ {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			char sign=' ';
			if(a<b) {
				sign = '<';
			} else if(a>b) {
				sign = '>';
			} else {
				sign = '=';
			}
			System.out.println("#"+testCase + " "+sign);
		}
	}
}
