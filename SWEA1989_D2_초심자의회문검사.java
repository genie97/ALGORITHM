import java.util.Scanner;

public class SWEA1989_D2_초심자의회문검사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String line = sc.next();
			int mid = line.length()/2;
			String frontStr = line.substring(0,  mid);
			String backStr = "";
			if(line.length()%2 == 1)
				backStr = line.substring(mid+1, line.length());
			else
				backStr = line.substring(mid, line.length());
			String backReverse = new StringBuffer(frontStr).reverse().toString();
			if (backStr.equals(backReverse)) {
				System.out.println("#" + tc + " " + 1);
			}
			else {
				System.out.println("#" + tc + " " + 0);
			}	
		}
	}
}
