import java.util.Scanner;

public class SWEA2058_D1_자릿수더하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] num = new char[4];
		String input = sc.next();
		num = input.toCharArray();
		int sum = 0;
		for (int i = 0; i < num.length; i++) {
			sum+=(num[i]-'0');
		}
		System.out.println(sum);
	}
}
