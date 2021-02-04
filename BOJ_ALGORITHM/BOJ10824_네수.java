import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10824_네수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String AB = Integer.parseInt(st.nextToken()) + "" + Integer.parseInt(st.nextToken());
		String CD = Integer.parseInt(st.nextToken()) + "" + Integer.parseInt(st.nextToken());
		System.out.println(Long.parseLong(AB) + Long.parseLong(CD));
	}
}
