import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] block = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;

		for (int i = 1; i < W; i++) {
			int right = 0;
			int left = 0;
			for (int j = 0; j < i; j++) {
				left = Math.max(left, block[j]);
			}
			for (int j = W - 1; j > i; j--) {
				right = Math.max(right, block[j]);
			}
			sum += Math.max(0, Math.min(right, left) - block[i]);
		}

		System.out.println(sum);
	}
}
