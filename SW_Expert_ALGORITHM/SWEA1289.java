/**
 * �޶����� �κ��� üũ�� ��!
 * */
import java.util.Scanner;
public class SWEA1289{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {	
			char arr[] = sc.next().toCharArray();
			int cnt = 0;
			char cur = '0';
			for (int len = 0; len < arr.length; len++) {	
				if(arr[len]!=cur) {
					cnt++;
					cur = arr[len];
				}
			}
			System.out.println("#" + testCase + " " + cnt);
		}
	}
}
