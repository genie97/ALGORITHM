import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11576_BaseConversion {
	public static void main(String[] args) throws IOException {
		// 진법 A와 진법 B
		// A진법으로 나타낸 숫자의 자리수 m
		// A진법을 이루고 있는 숫자 m개
		// A진법으로 나타낸 수를 B진법으로 변환하여 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		long sum = 0;
		for (int i = 0; i < M; i++) {
			int bit = Integer.parseInt(st.nextToken());
			sum += (long) (Math.pow(A, M - i - 1)) * bit;
		}
		StringBuilder sb = new StringBuilder();
		while (sum != 0) {
			sb.insert(0, (sum % B) + " ");
			sum /= B;
		}
		System.out.println(sb);

	}
}
