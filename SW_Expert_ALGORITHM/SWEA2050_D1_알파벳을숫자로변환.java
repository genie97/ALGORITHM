import java.util.Scanner;

public class SWEA2050_D1_알파벳을숫자로변환 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.next();
		char[] arr = new char[220];
		arr = line.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(((int)arr[i]-'A'+1)+" ");
		}
	}
}
