import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5622_다이얼 {

	static int[] dial = { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int time = 0;
		for (int i = 0; i < str.length(); i++) {
			int num = (int) (str.charAt(i) - 'A');
			time += (dial[num] + 1);
		}
		System.out.println(time);
	}
}
