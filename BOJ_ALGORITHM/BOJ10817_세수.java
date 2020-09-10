import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10817_세수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[3];

		int max = 0;
		int maxIdx = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (max < arr[i]) {
				max = arr[i];
				maxIdx = i;
			}
		}

		int second = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == maxIdx)
				continue;
			second = Math.max(second, arr[i]);
		}
		
		System.out.println(second);

	}
}
