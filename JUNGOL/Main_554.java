import java.util.Scanner;

public class Main_554 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = 1;
		char ch = 'A';
		for(int i=1;i<=n;i++) {
			for(int x = n; x>0; x--) {
				System.out.print(num++ + " ");
				if(x==i) break;
			}
			for(int y = 0; y<n; y++) {
				if(y==i) break;
				System.out.print(ch++ + " ");
			}
			System.out.println();
		}
	}
}
