import java.util.Scanner;

public class BOJ2579_계단오르기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] score = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			score[i] = sc.nextInt();
		}
		int[] F = new int[N + 1];
		if (N == 1) { // N = 1인경우
			F[1] = score[1];

		} else if (N == 2) { // N = 2인경우
			F[2] = Math.max(score[1] + score[2], score[2]);

		} else if (N == 3) { // N = 3인경우
			F[3] = Math.max(score[1] + score[3], score[2] + score[3]);
		} else { // N >= 4인경우
			F[1] = score[1];
			F[2] = Math.max(score[1] + score[2], score[2]);
			F[3] = Math.max(score[1] + score[3], score[2] + score[3]);
			// N = 4인 경우
			// 1->3->4 
			// 2->4 
			for (int i = 4; i <= N; i++) {
				F[i] = Math.max(F[i - 3] + score[i - 1] + score[i], F[i - 2] + score[i]);
			}
		}
		System.out.println(F[N]);
	}

}
