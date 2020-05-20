import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {
	static int L, C;
	static char[] arr;
	static boolean[] check;
	static StringBuilder sb;
	static char[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); //4개 구성이다
		C = Integer.parseInt(st.nextToken()); //6개 구성이다
		arr = new char[C];
		String str = br.readLine();
		for (int i = 0, idx = 0; i < C; i++, idx += 2) {
			arr[i] = str.charAt(idx);
		}
		Arrays.sort(arr);
		check = new boolean[C];
		res = new char[L];
		sb = new StringBuilder();
		makeComb(0, 0);
		System.out.println(sb);
	}

	static void makeComb(int cnt, int idx) {
		if (cnt == L) {
			if (right()) {
				for (int i = 0; i < L; i++) {
					sb.append(res[i]);
				}
				sb.append('\n');
			}
			return;
		}
		for (int i = idx; i < C; i++) {
			if (check[i])
				continue;
			check[i] = true;
			res[cnt] = arr[i];
			makeComb(cnt + 1, i + 1);
			check[i] = false;
		}
	}

	static boolean right() {
		int mo = 0;
		int ja = 0;
		for (int i = 0; i < L; i++) {
			if (res[i] == 'a' || res[i] == 'e' || res[i] == 'i' || res[i] == 'o' || res[i] == 'u')
				mo++;
			else
				ja++;
			if (mo >= 1 && ja >= 2)
				return true;
		}
		return false;
	}
}
