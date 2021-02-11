import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ11656_접미사배열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			list.add(str.substring(i));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append('\n');
		}
		System.out.println(sb);
	}
}
