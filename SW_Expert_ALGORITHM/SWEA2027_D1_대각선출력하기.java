import java.util.Arrays;
import java.util.Scanner;

public class SWEA2027_D1_대각선출력하기 {
	public static void main(String[] args) {
		char[] arr = {'+', '+', '+', '+', '+'};
		for (int i = 0; i < arr.length; i++) {
			arr[i] = '#';
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[j]);
			}
			System.out.println();
			arr[i] = '+';
		}
	}
}
