import java.util.Scanner;

public class SWEA1926_D2_간단한369게임 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			if(find(i)==0) System.out.print(i);
			else {
				for (int j = 0; j < find(i); j++) {
					System.out.print("-");
				}
			}
			System.out.print(" ");
		}
	}

	private static int find(int i) {
		int cnt = 0;
		String line = Integer.toString(i);
		
		for (int j = 0; j < line.length(); j++) {
			if(line.charAt(j) == '3') cnt++;
			if(line.charAt(j) == '6') cnt++;
			if(line.charAt(j) == '9') cnt++;
		}
		return cnt;
	}
}
