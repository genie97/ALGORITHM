import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 현재 위치에서 최단거리가 짧은 승객을 고른다.
// 그 승객이 여러명인 경우면, 행 번호가 가장 작은 승객을 고른다
// 행 번호가 같은 승객이 여러명인 경우면, 열 번호가 작은 승객을 고른다.
// 한칸 당 연료 1 소모
// 성공적으로 이동시키면, 소모한 연료 양의 두배가 충전된다.
// 이동하는 도중 연료가 바닥나면 이동에 실패 => -1 출력
// 승객을 목적지에 이동시키고 0이 되면 실패가 아님
// 1. 현재 택시 위치에서 dfs해서 거리가 최소인 승객 위치 찾기
// 2. 그 승객 데려다주기
// 2-1. 승객을 데려다주다가 연료가 0이하가 되면 실패 => -1
public class BOJ19238_스타트택시 {
	static int N, M, L; // 맵의 크기 N*N, M명의 승객, 초기 연료
	static int[][] map;

	static class pos {
		int x;
		int y;

		public pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class navi implements Comparable<navi> {
		int srcX;
		int srcY;
		int destX;
		int destY;
		int dis;

		public navi(int srcX, int srcY, int destX, int destY, int dis) {
			super();
			this.srcX = srcX;
			this.srcY = srcY;
			this.destX = destX;
			this.destY = destY;
			this.dis = dis;
		}

		@Override
		public int compareTo(navi o) {
			if (this.dis > o.dis) {
				return 1;
			} else if (this.dis == o.dis) {
				if (this.srcX > o.srcX) {
					return 1;
				} else if (this.srcX == o.srcX) { // 만약 행도 같다면
					if (this.srcY > o.srcY) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
	}

	static pos taxi;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		PriorityQueue<navi> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		st = new StringTokenizer(br.readLine(), " ");
		taxi = new pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			navi n = new navi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
			pq.offer(n);
		}

		while (true) {
			PriorityQueue<navi> people = new PriorityQueue<>();

			// 현재 택시 위치에서 거리 계산
			while (!pq.isEmpty()) {
				navi n = pq.poll();
				int dis = bfs(n);
				if (dis == -1) {
					L = -1;
					break;
				}
				n.dis = dis;
				people.offer(n);
			}

			if (people.isEmpty())
				break;
			navi n = people.poll();
			// 승객을 태워서 이동하는 거리
			taxi.x = n.destX;
			taxi.y = n.destY;
			int dis = bfs(n);

			if (dis == -1) {
				L = -1;
				break;
			}

			// 현재 남은 연료가 가장 가까운 곳을 가는데 필요한 연료보다 작을 떄! (=을 안붙이는건, 도착해서 0이 되는건 성공이라서)
			if (L < n.dis + dis) { // 승객을 태워서 도착지까지 갈 수 없는 경우 업무가 끝남
				L = -1;
				break;
			}

			// 연료로 이동이 가능하다면? (taxi위치를 이동하는 위치까지 이동시키기)
			L -= n.dis; // 택시가 승객이 있는 곳 까지 가는 연료
			L += dis;
			pq.clear();
			pq = people;
		}
		System.out.println(L);
	}

	static int bfs(navi n) {
		boolean[][] visit = new boolean[N][N];
		int x = taxi.x;
		int y = taxi.y;
		visit[x][y] = true;
		int dis = 0;
		Queue<pos> q = new LinkedList<>();
		q.add(new pos(x, y));

		while (!q.isEmpty()) {
			int sz = q.size();

			while (sz-- > 0) {
				int cx = q.peek().x;
				int cy = q.peek().y;
				q.poll();
				if (cx == n.srcX && cy == n.srcY)
					return dis;

				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (!isIn(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					q.add(new pos(nx, ny));
				}
			}
			dis++;
		}
		return -1;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}

/* 
시간초과 코드 
=> 승객들을 어레이로 관리하고 계속해서 sorting해주는 곳에서 시간초과 발생 => 이런 경우는 priority queue로 한 번에 관리 가능!! (잊지 말것!)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19238_스타트택시_시간초과 {
	static int N, M, L; // 맵의 크기 N*N, M명의 승객, 초기 연료
	static int[][] map;

	static class pos {
		int x;
		int y;

		public pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class navi implements Comparable<navi> {
		int srcX;
		int srcY;
		int destX;
		int destY;
		int dis;

		public navi(int srcX, int srcY, int destX, int destY, int dis) {
			super();
			this.srcX = srcX;
			this.srcY = srcY;
			this.destX = destX;
			this.destY = destY;
			this.dis = dis;
		}

		@Override
		public int compareTo(navi o) {
			if (this.dis <= o.dis) {
				if (this.dis < o.dis)
					return -1;
				else {
					if (this.srcX <= o.srcX) {
						if (this.srcX < o.srcX)
							return -1;
						else {
							if (this.srcY < o.srcY)
								return -1;
							else
								return 1;
						}
					}
				}
			}
			return 1;
		}
	}

	static navi[] people;
	static pos taxi;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		people = new navi[M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		st = new StringTokenizer(br.readLine(), " ");
		taxi = new pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			people[i] = new navi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		}

		int moveP = M; // 총 승객 수 체크
		
		while (true) {
			if (moveP == 0) { // 승객을 다 데려다 줬거나, 이동 중 연료가 0보다 작아졌을 때
				break;
			}

			// 현재 택시 위치에서 거리 계산
			for (int i = 0; i < M; i++) {
				if(people[i].dis == Integer.MAX_VALUE) continue; 
				int dis = bfs(i);
				if(dis == -1) {
					L = -1;
					break;
				}
				people[i].dis = dis;
			}

			// 거리가 작은 순, 거리가 같다면 행 번호가 작은 순, 행 번호도 같다면 열 번호가 작은 순으로 소팅
			Arrays.sort(people);

			// 승객을 태워서 이동하는 거리
			taxi.x = people[0].destX;
			taxi.y = people[0].destY;
			int dis = bfs(0);

			if(dis == -1) {
				L = -1;
				break;
			}
			
			// 현재 남은 연료가 가장 가까운 곳을 가는데 필요한 연료보다 작을 떄! (=을 안붙이는건, 도착해서 0이 되는건 성공이라서)
			if (L < people[0].dis + dis) { // 승객을 태워서 도착지까지 갈 수 없는 경우 업무가 끝남
				L = -1;
				break;
			}

			// 연료로 이동이 가능하다면? (taxi위치를 이동하는 위치까지 이동시키기)
			L -= people[0].dis; // 택시가 승객이 있는 곳 까지 가는 연료
			L += dis;
			people[0].dis = Integer.MAX_VALUE; // 이미 도착지에 도착한 승객
			moveP--;
		}
		System.out.println(L);
	}

	static int bfs(int idx) {
		boolean[][] visit = new boolean[N][N];
		int x = taxi.x;
		int y = taxi.y;
		visit[x][y] = true;
		int dis = 0;
		Queue<pos> q = new LinkedList<>();
		q.add(new pos(x, y));

		while (!q.isEmpty()) {
			int sz = q.size();

			while (sz-- > 0) {
				int cx = q.peek().x;
				int cy = q.peek().y;
				q.poll();
				if (cx == people[idx].srcX && cy == people[idx].srcY)
					return dis;

				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (!isIn(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					q.add(new pos(nx, ny));
				}
			}
			dis++;
		}
		return -1;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
*/
