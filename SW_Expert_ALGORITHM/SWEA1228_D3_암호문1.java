import java.util.LinkedList;
import java.util.Scanner;

public class SWEA1228_D3_암호문1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt(); // 암호문 총길이
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}
			int C = sc.nextInt(); // 명령어 개수
			for (int i = 0; i < C; i++) {
				char CI = sc.next().charAt(0);
				int x = sc.nextInt();
				int y = sc.nextInt();
				int s[] = new int[y];
				for (int j = 0; j < s.length; j++) {
					int data = sc.nextInt();
					list.add(x + j, data);
				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < 10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}
}
