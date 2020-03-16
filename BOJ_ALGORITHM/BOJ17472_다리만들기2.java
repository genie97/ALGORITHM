import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ17472_다리만들기2 {
	public static class Edge implements Comparable<Edge> {
		int u, v, dis;

		public Edge(int u, int v, int dis) {
			this.u = u;
			this.v = v;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dis, o.dis);
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + ", dis=" + dis + "]";
		}
	}

	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[][][] visit;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static ArrayList<Edge> list = new ArrayList<>();
	public static int[] p;
	public static int[] r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < M; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}
		int idx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = idx;
					makeIsland(i, j, idx);
					idx++;
				}
			}
		}
		visit = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || visit[i][j][0] || visit[i][j][1])
					continue;
				visit[i][j][0] = true;
				makeBridge(i, j, 0, map[i][j], 0); // 0 가로
				visit[i][j][1] = true;
				makeBridge(i, j, 1, map[i][j], 0); // 1 세로
			}
		}
		
		/* 최단 경로 크루스칼 */
		Collections.sort(list);
		idx -= 2;
		p = new int[idx];
		r = new int[p.length];
		for (int i = 0; i < idx; i++) {
			makeSet(i);
		}
		int min = 0;
		int link = 0;
		for (int i = 0; i < list.size(); i++) {
			int px = findParent(list.get(i).u);
			int py = findParent(list.get(i).v);
			if (px != py) {
				union(px, py);
				min += list.get(i).dis;
				link++;
			}
		}
		System.out.println((link == idx - 1) ? min : -1);
	}

	public static void union(int px, int py) {
		link(px, py);
	}

	public static void link(int px, int py) {
		if (r[px] < r[py]) {
			p[px] = p[py];
		} else {
			if (r[px] == r[py]) {
				r[px]++;
			}
			p[py] = p[px];
		}
	}

	public static int findParent(int v) {
		if (p[v] == v)
			return p[v];
		else
			return p[v] = findParent(p[v]);
	}

	public static void makeSet(int v) {
		p[v] = v;
	}

	public static void makeBridge(int x, int y, int dir, int idx, int cnt) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return;
		if (map[x][y] != idx && map[x][y] != 0) {
			visit[x][y][dir] = false;
			if (cnt - 1 >= 2) {
				list.add(new Edge(idx - 2, map[x][y] - 2, cnt - 1));
			}
			return;
		}
		if (dir == 0) {
			for (int i = 2; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (ny < 0 || ny >= M)
					continue;
				if (visit[nx][ny][dir])
					continue;
				if (map[nx][ny] == idx)
					continue;
				visit[nx][ny][0] = true;
				makeBridge(nx, ny, 0, idx, cnt + 1);
			}
		} else {
			for (int i = 0; i < 2; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N)
					continue;
				if (visit[nx][ny][dir])
					continue;
				if (map[nx][ny] == idx)
					continue;
				visit[nx][ny][1] = true;
				makeBridge(nx, ny, 1, idx, cnt + 1);
			}
		}
	}

	public static void makeIsland(int x, int y, int idx) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			if (map[nx][ny] == 0 || map[nx][ny] == idx)
				continue;
			map[nx][ny] = idx;
			makeIsland(nx, ny, idx);
		}
	}

}
