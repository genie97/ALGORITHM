import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ2751_수정렬하기2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append('\n');
		}
		System.out.println(sb);
	}
}
