import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1266_D6_소수완제품확률 {
	final static long facto18 = factorial(18);

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			double aPossible = Double.parseDouble(st.nextToken()) / 100; // a가 발생할 확률
			double bPossible = Double.parseDouble(st.nextToken()) / 100; // b가 발생할 확률
			double aIndPossible = computeInd(aPossible);
			double bIndPossible = computeInd(bPossible);
			double res = aIndPossible + bIndPossible - aIndPossible * bIndPossible; // n(A) + n (B) - {n(A) * n(B)}
			System.out.format("#%d %.6f\n", tc, res);

		}

	}

	/** nCr 계산 할 때, nCr = n! / { r! * (n-r)! } */
	public static long factorial(int n) {
		if (n == 1) {
			return 1;
		}
		long res = n * factorial(n - 1);
		return res;
	}

	/** nCr 계산하기 */
	public static long combination(int r) {
		if (r == 0 || r == 18)
			return 1;
		long res = facto18 / (factorial(r) * factorial(18 - r));
		return res;
	}

	public static double computeInd(double p) {
		if (p == 0)
			return 0;
		int[] prime = { 2, 3, 5, 7, 11, 13, 17 }; // 소수 배열 (90분, 5분동안 한개 => 총 18개)
		double P[] = new double[19]; // 어떤 사건이 발생할 확률
		double Q[] = new double[19]; // 어떤 사건이 발생하지 않을 확률
		P[1] = p;
		Q[1] = 1 - p;
		for (int i = 2; i < 18; i++) {
			P[i] = P[i - 1] * P[1];
			Q[i] = Q[i - 1] * Q[1];
		}

		double res = 0;
		for (int i = 0; i < prime.length; i++) {
			res += (combination(prime[i]) * P[prime[i]] * Q[18 - prime[i]]);
		}
		return res;
	}
}
