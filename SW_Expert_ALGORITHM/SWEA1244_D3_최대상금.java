import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1244_D3_최대상금 {
	public static int change;
	public static int[] nArr;
	public static int res;
	public static int maxM;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			nArr = new int[(num + "").length()];
			change = Integer.parseInt(st.nextToken());
			for (int i = 0; i < nArr.length; i++) {
				nArr[i] = (num + "").charAt(i) - '0';
			}
			maxM = Integer.MIN_VALUE;
			maxMoney(0, 0);
			System.out.println("#" + tc + " " + maxM);
		}
	}

	public static void maxMoney(int cur, int cnt) {
		if (cnt == change) {
			String temp = "";
			for (int i = 0; i < nArr.length; i++) {
				temp += nArr[i];
			}
			if (maxM < Integer.parseInt(temp)) {
				maxM = Integer.parseInt(temp);
			}
			return;
		}
		for (int i = cur; i < nArr.length; i++) {
			for (int j = i + 1; j < nArr.length; j++) {
				if (nArr[i] <= nArr[j]) {
					swap(i, j);
					maxMoney(i, cnt + 1);
					swap(i, j);
				}
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = nArr[i];
		nArr[i] = nArr[j];
		nArr[j] = temp;
	}
}
