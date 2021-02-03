// 거울 개수가 최소인 위치를 먼저 꺼내기 위해 우선순위큐 사용
// 2번 풀이 최적화

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087_레이저통신 {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int mirror;
		int dir;

		public Pos(int x, int y, int mirror, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.mirror = mirror;
			this.dir = dir;
		}

		// 거울수가 최소인게 우선
		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.mirror, o.mirror);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sx = 0, sy = 0;

		char[][] map = new char[M][N];
		int[][] visit = new int[M][N];
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
				// 방문 배열 초기화
				visit[i][j] = Integer.MAX_VALUE;
				// 첫 시작점을 잡는다
				if (map[i][j] == 'C') {
					sx = i;
					sy = j;
				}
			}
		}

		int ans = 0;
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.add(new Pos(sx, sy, 0, -1)); // 위치, 거울 개수, 방향
		visit[sx][sy] = 0;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			if ((p.x != sx || p.y != sy) && map[p.x][p.y] == 'C') {
				// 도착점으로 갱신해주기
				ans = p.mirror;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위 초과시 이동불가
				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;
				// 벽인 경우 이동 불가
				if (map[nx][ny] == '*')
					continue;

				// 같은 방향 아닐때만 거울 개수 추가
				if (p.dir == -1 || p.dir == i) {
					visit[nx][ny] = p.mirror;
					q.add(new Pos(nx, ny, p.mirror, i));
				} else {
					if (visit[nx][ny] >= p.mirror + 1) {
						visit[nx][ny] = p.mirror + 1;
						q.add(new Pos(nx, ny, p.mirror + 1, i));
					}
				}
			}
		}

		System.out.println(ans);
	}
}

// 거울 개수를 memo하면서 최소값을 갱신해 나감
// 방문 처리 => 방문한 위치일 때, 거울을 최소로 가질때만 업데이트하면서 큐에 삽입

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class BOJ6087_레이저통신 {
// 	static int[] dx = { -1, 0, 1, 0 };
// 	static int[] dy = { 0, 1, 0, -1 };

// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
// 		int N = Integer.parseInt(st.nextToken());
// 		int M = Integer.parseInt(st.nextToken());
// 		int sx = 0, sy = 0;

// 		char[][] map = new char[M][N];
// 		for (int i = 0; i < map.length; i++) {
// 			String str = br.readLine();
// 			for (int j = 0; j < map[i].length; j++) {
// 				map[i][j] = str.charAt(j);
// 				// 첫 시작점을 잡는다
// 				if (map[i][j] == 'C') { 
// 					sx = i;
// 					sy = j;
// 				}
// 			}
// 		}

// 		boolean[][] visit = new boolean[M][N];
// 		int[][] memo = new int[M][N]; // 최소 거울에 대한 memoization배열
// 		Queue<int[]> q = new LinkedList<>();
// 		q.add(new int[] { sx, sy, 0, -1 }); // 위치, 거울 개수, 방향
// 		visit[sx][sy] = true;
// 		memo[sx][sy] = 0; // 처음에는 거울이 0개

// 		while (!q.isEmpty()) {
// 			int size = q.size();
// 			for (int sz = 0; sz < size; sz++) {
// 				int[] cur = q.poll();
// 				if ((cur[0] != sx || cur[1] != sy) && map[cur[0]][cur[1]] == 'C') {
// 					// 도착점으로 갱신해주기
// 					sx = cur[0];
// 					sy = cur[1];
// 					break;
// 				}
// 				for (int i = 0; i < 4; i++) {
// 					int nx = cur[0] + dx[i];
// 					int ny = cur[1] + dy[i];
// 					int nm = cur[2]; // 현재 거울 갯수

// 					// 범위 초과시 이동불가
// 					if (nx < 0 || ny < 0 || nx >= M || ny >= N)
// 						continue;
// 					// 벽인 경우 이동 불가
// 					if (map[nx][ny] == '*')
// 						continue;
// 					// 90도 꺾은 방향이 아니면 이동불가
// 					if (cur[3] > -1) {
// 						if (cur[3] == 0 && i == 2 || cur[3] == 1 && i == 3 || cur[3] == 2 && i == 0
// 								|| cur[3] == 3 && i == 1)
// 							continue;
// 					}

// 					// 같은 방향이 아닌 경우에는 거울 수 증가
// 					if (cur[3] > -1 && cur[3] != i) {
// 						nm += 1;
// 					}
// 					// 방문 지점인데 거울 개수가 많으면 제외 => 우선순위큐로 해결가능!
// 					if (visit[nx][ny] && nm > memo[nx][ny])
// 						continue;
// 					// 방문 및 거울 개수 업데이트
// 					visit[nx][ny] = true;
// 					memo[nx][ny] = nm;
// 					q.add(new int[] { nx, ny, nm, i });
// 				}
// 			}

// 		}

// 		System.out.println(memo[sx][sy]);
// 	}
// }
