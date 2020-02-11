import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1952_AD_수영장 {
	public static int[] fee;
	public static ArrayList<Integer> usage;
	public static int minFee;
	public static int SIZE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int TC = Integer.parseInt(st.nextToken());
		;
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			fee = new int[4];
			for (int i = 0; i < fee.length; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			usage = new ArrayList<>();
			for (int i = 0; i < 12; i++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 0)
					continue;
				usage.add(a);
			}
			minFee = fee[3]; // 1년권을 max로 세팅
			SIZE = usage.size();
			dfs(0, 0);
			System.out.println("#" + tc + " " + minFee);
		}
	}

	public static void dfs(int total, int m) {
		if (m == SIZE) {
			if (minFee <= total)
				return;
			minFee = total;
			return;
		}

		for (int i = 0; i < fee.length; i++) {

			if (i == 0) { // 하루권
				total += ((usage.get(m).intValue()) * fee[i]);
				dfs(total, m + 1);
				total -= ((usage.get(m).intValue()) * fee[i]);
			} else if (i == 1) { // 한달권
				total += fee[i];
				dfs(total, m + 1);
				total -= fee[i];
			} else if (i == 2) { // 3개월권
				total += fee[i];
				if (m == SIZE - 1 || m == SIZE - 2) { // 11월 혹은 12월에 사용하는 경우
					dfs(total, m + (SIZE - m));
				} else {
					dfs(total, m + 3);
				}
				total -= fee[i];
			}
		}
	}

}
