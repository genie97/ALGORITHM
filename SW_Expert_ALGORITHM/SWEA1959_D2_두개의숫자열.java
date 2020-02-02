import java.util.Arrays;
import java.util.Scanner;

public class SWEA1959_D2_두개의숫자열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] A = new int[N];
			int[] B = new int[M];
			for (int i = 0; i < A.length; i++) {
				A[i] = sc.nextInt();
			}
			for (int i = 0; i < B.length; i++) {
				B[i] = sc.nextInt();
			}
			int maxV = 0;
			for (int i = 0; i < Math.abs(N-M) + 1 ; i++) {
				int sum = 0;
				if(N < M) {
					for (int j = 0; j < N; j++) {
						sum+= (A[j] * B[i+j]);	
					}
				}else {
					for (int j = 0; j < M; j++) {
						sum+= (A[i+j] * B[j]);	
					}
				}
				maxV = Integer.max(maxV, sum);
			}
			System.out.println("#" + tc +" "+ maxV + " ");
		}
	}
}
