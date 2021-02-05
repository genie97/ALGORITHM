import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2745_진법변환 {
	// Integer.toString(n, 진법) => 10진법 -> N진법
	// Integer.parseInt(n, 진법) = > N진법 -> 10진법
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String str = st.nextToken();
		int N = Integer.parseInt(st.nextToken());
		System.out.println(Integer.parseInt(str, N));
	}
}
