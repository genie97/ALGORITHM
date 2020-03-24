import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16235_나무재테크 {
	static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) { // 나이 어린 순으로 우선순위 높음
			return this.age - o.age;
		}

	}

	static int N, M, K;
	static int[][] A, map;
	static PriorityQueue<Tree> treeMap;
	static int dx[] = { -1, 1, 0, 0, 1, 1, -1, -1 };
	static int dy[] = { 0, 0, -1, 1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N*N 땅
		M = Integer.parseInt(st.nextToken()); // 처음 나무 개수
		K = Integer.parseInt(st.nextToken()); // K년
		A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		treeMap = new PriorityQueue<>(); // 나무 저장 (나이 어린 순)
		map = new int[N + 1][N + 1]; // 양분
		// 양분 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			treeMap.add(new Tree(x, y, z));
		}
		for (int year = 1; year <= K; year++) {
			lifeCycle();
		}
		System.out.println(treeMap.size());
	}

	static void lifeCycle() { // 봄&여름 -> 가을 -> 겨울
		springNsummer();
		fall();
		winter();
	}

	static void springNsummer() {
		ArrayList<Tree> dead = new ArrayList<>(); // 죽은 나무
		ArrayList<Tree> live = new ArrayList<>(); // 나이 먹은 나무
		// 봄
        int sz = treeMap.size();
		for (int i = 0; i < sz; i++) {
			Tree t = treeMap.poll();
			if (map[t.x][t.y] < t.age) {// 양분보다 나이가 많은 경우
				dead.add(t);
			} else { // 양분보다 나이가 어린 경우
				map[t.x][t.y] -= t.age;
				live.add(new Tree(t.x, t.y, t.age + 1));
			}
		}
        for (int i = 0; i < live.size(); i++) {
			treeMap.add(live.get(i));
		}
        
        // 여름
		for (int i = 0; i < dead.size(); i++) {
			Tree d = dead.get(i);
			map[d.x][d.y] += (d.age / 2); // 양분 증가시켜주기
		}	
	}

	static void fall() {
		ArrayList<Tree> live = new ArrayList<>();
		int sz = treeMap.size();
		for (int i = 0; i < sz; i++) {
			Tree t = treeMap.poll();
			live.add(t);
			if (t.age % 5 == 0) { // 나이가 5의 배수라면, 8방향으로 번식 가능
				for (int dir = 0; dir < 8; dir++) {
					int nx = t.x + dx[dir];
					int ny = t.y + dy[dir];
					if (nx < 1 || ny < 1 || nx > N || ny > N)
						continue;
					live.add(new Tree(nx, ny, 1));
				}
			}
		}
        // 새롭게 추가된 나무들 
		for (int i = 0; i < live.size(); i++) {
			treeMap.add(live.get(i));
		}
	}

	static void winter() { // 양분 증가
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

}
