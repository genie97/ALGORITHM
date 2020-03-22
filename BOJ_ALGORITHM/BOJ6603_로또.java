import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6603_로또 {
	public static int[] num;
	public static int[] cArr;
	public static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			num = new int[k];
			for (int i = 0; i < k; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			cArr = new int[6];
			visit = new boolean[k];
			comb(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void comb(int cnt, int idx) {
		if (cnt == 6) {
			for (int i = 0; i < cArr.length; i++) {
				sb.append(cArr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = idx; i < num.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			cArr[cnt] = num[i];
			comb(cnt + 1, i);
			visit[i] = false;
		}
	}
}
