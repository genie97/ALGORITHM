import java.util.Scanner;

public class Main_539 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int cnt = 0;
		int n = 0;
		while(n<100) {
			n = sc.nextInt();
			sum+=n;
			cnt++;
		}
		System.out.println(sum);
		System.out.printf("%.1f", (float)(sum)/cnt);
		System.out.println();
		System.out.println(Math.round(((double)sum / cnt) * 10) / 10.0);		
	}
}
