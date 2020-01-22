import java.util.Scanner;

public class SWEA2029_D1_몫과나머지출력하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println("#"+ tc + " " + a/b + " " + a%b);
		}
	}
}
