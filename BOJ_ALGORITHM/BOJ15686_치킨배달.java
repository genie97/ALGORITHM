import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 크기는 N*N
 * 빈칸(0), 치킨집(2), 집(1) 중 하나
 * 치킨거리: 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨 거리는 모든 집의 치킨 거리의 합
 * 치킨집 개수는 최대 M개
 */
public class BOJ15686_치킨배달 {
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> chicken;
	static int ans;
	static int[] comb;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
				if (map[i][j] == 2) {
					chicken.add(new int[] { i, j });
				}
			}
		}
		comb = new int[M];
		visit = new boolean[chicken.size()];
		ans = Integer.MAX_VALUE;
		delete(0, 0);
		System.out.println(ans);
	}

	static void delete(int idx, int cnt) {
		if (cnt == M) {
			int dis = calDis();
			ans = Math.min(ans, dis);
			return;
		}
		for (int i = idx; i < chicken.size(); i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			comb[cnt] = i;
			delete(i, cnt + 1);
			visit[i] = false;
		}
	}

	static int calDis() {
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					int min = Integer.MAX_VALUE;
					for (int k = 0; k < M; k++) {
						min = Math.min(min, Math.abs(chicken.get(comb[k])[0] - i) + Math.abs(chicken.get(comb[k])[1] - j));
					}
					sum += min;
				}
			}
		}
		return sum;
	}
}
