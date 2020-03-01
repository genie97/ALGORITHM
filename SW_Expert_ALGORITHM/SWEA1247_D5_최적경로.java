import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1247_D5_최적경로 {
	public static ArrayList<int[]> cusH;
	public static int N;
	public static int sx;
	public static int sy;
	public static int ex;
	public static int ey;
	public static int ans;
	public static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			cusH = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cusH.add(new int[] { x, y });
			}
			visit = new boolean[N];
			ans = Integer.MAX_VALUE;
			makePerm(0, 0, sx, sy);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void makePerm(int cnt, int sum, int x1, int y1) {
		if (ans < sum) // 최적 답보다 sum이 작으면 가지치기
			return;
		if (cnt == N) {
			int disLast = Math.abs(x1 - ex) + Math.abs(y1 - ey); //
			sum += disLast;
			if (ans > sum) {
				ans = sum;
			}
			return;
		}
		for (int i = 0; i < cusH.size(); i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			int x = cusH.get(i)[0];
			int y = cusH.get(i)[1];
			int dis = Math.abs(x1 - x) + Math.abs(y1 - y);
			makePerm(cnt + 1, sum + dis, x, y);
			visit[i] = false;
		}
	}
}
