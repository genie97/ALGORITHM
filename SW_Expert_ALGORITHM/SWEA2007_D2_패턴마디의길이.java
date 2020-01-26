import java.util.Scanner;

public class SWEA2007_D2_패턴마디의길이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String line = sc.next();
			int ans = 0;
			for (int i = 1; i <= 10; i++) {
				String checkStr = line.substring(0, i);
				String nextStr = line.substring(i, i+i);
				if(checkStr.equals(nextStr)) {
					ans = i;
					break;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		
	}
}
