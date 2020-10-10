import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2908_상수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int max = Integer.MIN_VALUE;

		String s1 = st.nextToken();
		String s2 = st.nextToken();
		StringBuilder sb = new StringBuilder(s1).reverse();
		int n = Integer.parseInt(sb.toString());
		max = Math.max(n, max);

		sb = new StringBuilder(s2).reverse();
		n = Integer.parseInt(sb.toString());
		max = Math.max(n, max);
		
		System.out.println(max);
	}
}
