import java.util.Scanner;

public class BOJ1244_스위치켜고끄기 {
	static int[] sArr;
	static int N;

	public static void female(int idx) {
		int offset = 1;
		while (true) {
			int s = idx - offset;
			int e = idx + offset;
			if (1 > s || e > N ||sArr[s]!=sArr[e])
				break;
			
			sArr[s] ^= 1;
			sArr[e] ^= 1;
			offset++;
		}
		sArr[idx] ^= 1;
	}

	public static void male(int idx) {
		for (int i = 1; i < sArr.length; i++) {
			if (i % idx == 0)
				sArr[i] ^= 1;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sArr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			sArr[i] = sc.nextInt();
		}
		int S = sc.nextInt();
		for (int i = 0; i < S; i++) {
			int sex = sc.nextInt();
			int idx = sc.nextInt();
			if (sex == 1) {
				male(idx);
			} else {
				female(idx);
			}
		}
		for (int i = 1; i <= N; i++) {
			if (i > 1 && i % 20 == 1)
				System.out.println();
			System.out.print(sArr[i] + " ");
		}
	}
}
