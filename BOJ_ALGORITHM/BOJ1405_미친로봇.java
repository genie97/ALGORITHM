import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_미친로봇 {
	static double[] percentage;// 동 서 남 북
	static boolean[][] visit;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	static int N;
	static double res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		percentage = new double[4];
		for (int i = 0; i < 4; i++) {
			percentage[i] = Integer.parseInt(st.nextToken())/100.0;
		}
		visit = new boolean[29][29];
		visit[14][14] = true;
		dfs(14,14,0, 1.0);
		System.out.println(res);
	}
	static void dfs(int x, int y, int cnt, double per_sum) {
		if(cnt == N) {
			res+=per_sum;
			return;
		}
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(visit[nx][ny]) continue;
			visit[nx][ny] = true;
			dfs(nx, ny, cnt+1, per_sum * percentage[i]);
			visit[nx][ny] = false;
		}
	}

}
