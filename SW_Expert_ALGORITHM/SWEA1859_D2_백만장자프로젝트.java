import java.util.Scanner;

public class SWEA1859_D2_백만장자프로젝트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int [] sales = new int[N];
			int [] dp = new int[N];
			for (int i = 0; i < sales.length; i++) {
				sales[i] = sc.nextInt();
			}
			long sum = 0L;
			int max = 0;
			
			for (int i =  sales.length-1; i >= 0; i--) {
				if(max < sales[i]) {
					max = sales[i];
				}
				sum += (max-sales[i]);
			}
			System.out.println("#" + tc +  " " + sum);
		}
	}
}

