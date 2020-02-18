import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2660_회장뽑기 {
	final static int INF = 999999999;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				map[i][j] = INF;
			}
		}
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from == -1 && to == -1)
				break;
			map[to][from] = 1;
			map[from][to] = 1;
		}

		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				for (int k = 1; k < map.length; k++) {
					if (map[j][k] > map[j][i] + map[i][k]) {
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}
		int[] friendShip = new int[N + 1];
		int relation = Integer.MAX_VALUE;
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				if(i==j) continue;
				friendShip[i] = Integer.max(friendShip[i], map[i][j]);
			}
			relation = Integer.min(friendShip[i], relation);
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < friendShip.length; i++) {
			if (friendShip[i] == relation) {
				list.add(i);
			}
		}
		System.out.println(relation + " " + list.size());
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}
}
