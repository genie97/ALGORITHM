import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2902_KMP는왜KMP일까 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		String[] strArr = str.split("-");
		String res = "";
		for (int i = 0; i < strArr.length; i++) {
			char ch = strArr[i].toUpperCase().charAt(0);
			res += ch;
		}
		System.out.println(res);
	}

}
