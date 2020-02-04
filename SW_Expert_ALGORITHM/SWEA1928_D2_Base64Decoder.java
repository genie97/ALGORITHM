import java.util.Base64;
import java.util.Scanner;
import java.util.Base64.Decoder;

public class SWEA1928_D2_Base64Decoder {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String line = sc.next();
			Decoder decoder = Base64.getDecoder();
			String answer = new String(decoder.decode(line.getBytes()));
			System.out.println("#" + tc + " " + answer);
		}
	}
}
