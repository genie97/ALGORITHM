import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ14225_부분수열의합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> sum_list = new ArrayList<>();
		for (int i = 1; i < 1 << N; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				if ((i & (1 << j)) != 0) {
					sum += arr[j];
				}
			}
			sum_list.add(sum);
		}
		Collections.sort(sum_list);
		int v = 1;
		for (int i = 0; i < sum_list.size(); i++) {
			if (sum_list.get(i) == v) {
				while (i + 1 < sum_list.size() && sum_list.get(i + 1) == v) {
					i++;
				}
				v++;
			} else {
				break;
			}
		}
//		for (int i = 0; i < sum_list.size(); i++) {
//			System.out.println(sum_list.get(i));
//		}
		System.out.println(v);
	}
}
