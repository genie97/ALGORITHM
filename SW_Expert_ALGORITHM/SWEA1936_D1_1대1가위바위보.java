import java.util.Arrays;
import java.util.Scanner;

public class SWEA1936_D1_1대1가위바위보 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		if((a==2&&b==3) || (a==3&&b==2)) {
			if(a-b>0) System.out.println("A");
			else System.out.println("B");
		}
		else {
			if(a-b<0) System.out.println("A");
			else System.out.println("B");
		}
	}
}
