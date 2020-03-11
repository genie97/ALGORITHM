import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17828_문자열화폐 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		if (N * 26 < X || X < N) {
			System.out.println("!");
			return;
		}

		char[] str = new char[N];

		for (int i = 0; i < N; i++) {
			str[i] = 'A';
		}

		X -= N;

		for (int i = N - 1; i >= 0 && X > 0; i--) {
			int ch = Math.min(X, 25);
			str[i] += (char) ch;
			X -= ch;
		}
		StringBuilder sb = new StringBuilder(N);
		for (int i = 0; i < N; i++) {
			sb.append(str[i]);
		}
		System.out.println(sb);
	}
}
