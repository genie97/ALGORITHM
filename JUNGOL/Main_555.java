import java.util.Arrays;
import java.util.Scanner;

public class Main_555 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
//		char[] arr = new char[10];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = sc.next().charAt(0);
//		}
//		for(char c: arr) {
//			System.out.print(c);
//		}
		String strLine = sc.nextLine();
		String[] strArr = strLine.split(" ");

		for(String s: strArr) {
			System.out.print(s);
		}
//		String line = sc.nextLine();
//		arr = line.toCharArray();
//		for (int i = 0; i < arr.length; i++) {
//			if(arr[i]==' ') continue;
//			System.out.print(arr[i]);	
//		}
	}
}
