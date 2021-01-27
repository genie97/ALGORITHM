import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1935_후위표기식2 {
	static double[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		alpha = new double[26];
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			alpha[i] = Double.parseDouble(br.readLine());
		}

		Stack<Double> s = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isAlphabetic(ch)) {
				s.add(alpha[(int) (ch - 'A')]);
			} else {
				double a = s.pop();
				double b = s.pop();

				switch (ch) {
				case '+':
					s.add(a + b);
					break;
				case '-':
					s.add(b - a);
					break;
				case '*':
					s.add(a * b);
					break;
				case '/':
					s.add(b / a);
					break;
				}
			}
		}
		double res = s.pop();
		System.out.println(String.format("%.2f", res));
	}
}
