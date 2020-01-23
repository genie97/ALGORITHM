import java.util.Scanner;

public class Main_556 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] iArr = new int[10];
		for (int i = 0; i < iArr.length; i++) {
			iArr[i] = i+1;
		}
		for(int i: iArr) {
			System.out.print (i + " ");
		}
	}
}
