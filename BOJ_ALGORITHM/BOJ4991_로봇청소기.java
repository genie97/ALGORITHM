/*
1. bfs로 현재 위치 -> 다음 위치 까지의 최소 거리를 구하여 거리에 관한 table을 만든다 : time_table
2. 순열을 이용하여 이동 순서를 정하면서 time_table에 저장해둔 시간으로 sum을 구한다
2-1. sum이 minTime보다 큰 경우는 가지치기를 한다
2-2. time_table에 들어있는 값이 -1(도착할 수 없는 곳)인 경우도 가지치기 한다
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// . 깨끗한 칸
// * 더러운 칸
// x 가구
// o 로봇 청소기 시작 위치
// 가구 있는 곳은 못감
// 더러운 칸을 모두 깨끗하게 바꾸는데 필요한 최소값

public class BOJ4991_로봇청소기 {
	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int w, h;
	static char[][] map;
	static int[][] time_table;

	static int[] order;

	static ArrayList<Pos> list;
	static boolean[] selected;
	static int minTime;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			map = new char[h][w];
			list = new ArrayList<>();

			Pos robot = null;
			ArrayList<Pos> dirty = new ArrayList<>();

			for (int i = 0; i < map.length; i++) {
				String str = br.readLine();
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'o') {
						robot = new Pos(i, j, 0);
					} else if (map[i][j] == '*') {
						dirty.add(new Pos(i, j, 0));
					}
				}
			}
			list.add(robot);
			for (int i = 0; i < dirty.size(); i++) {
				list.add(dirty.get(i));
			}

			// 로봇 위치에서 각 먼지 위치까지 걸리는 시간 구하기
			time_table = new int[list.size()][list.size()];
			for (int i = 0; i < time_table.length; i++) {
				for (int j = 0; j < time_table.length; j++) {
					if (i == j) {
						time_table[i][j] = 0;
						continue;
					}
					time_table[i][j] = bfs(list.get(i), list.get(j));
				}
			}

			minTime = Integer.MAX_VALUE;
			selected = new boolean[dirty.size() + 1];
			order = new int[list.size()];
			order[0] = 0;

			makePerm(1, 0);
			sb.append(minTime == Integer.MAX_VALUE ? -1 : minTime).append('\n');
		}
		System.out.println(sb);
	}

	static void makePerm(int cnt, int sum) {
		if (sum > minTime)
			return;
		if (cnt == list.size()) {
			minTime = Math.min(minTime, sum);
			return;
		}
		for (int i = 1; i < list.size(); i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			order[cnt] = i;
			if (time_table[order[cnt - 1]][order[cnt]] == -1)
				return;
			makePerm(cnt + 1, sum + time_table[order[cnt - 1]][order[cnt]]);
			selected[i] = false;
		}
	}

	static int bfs(Pos p1, Pos p2) {
		boolean[][] visit = new boolean[h][w];
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(p1.x, p1.y, p1.cnt));
		visit[p1.x][p1.y] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			if (p.x == p2.x && p.y == p2.y)
				return p.cnt;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (!isIn(nx, ny) || map[nx][ny] == 'x' || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				q.add(new Pos(nx, ny, p.cnt + 1));
			}
		}
		return -1;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}
}
