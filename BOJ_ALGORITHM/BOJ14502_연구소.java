import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {
	public static int N;
	public static int M;
	public static int[][] map;
	public static int[][] copyMap;
	public static ArrayList<int[]> virus; // 바이러스 위치 담을 어레이리스트
	public static ArrayList<int[]> empty; // 벽을 세울 수 있는 위치 담을 어레이
	public static int ans;

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		virus = new ArrayList<int[]>(); // 미리 바이러스 위치를 큐에 담아뒀다가 퍼뜨리기
		empty = new ArrayList<int[]>(); // 벽 세울 수 있는 후보 위치

		int safeCnt = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				map[i][j] = str.charAt(index) - '0';
				if (map[i][j] == 2) // 바이러스 위치는 미리 저장해두고 시작
					virus.add(new int[] { i, j });
				if (map[i][j] == 0) {
					// 벽을 세울 수 있는 공간 정해두기
					empty.add(new int[] { i, j });
					safeCnt++;
				}
			}
		}
		ans = Integer.MIN_VALUE;
		makeWall(0, 0, safeCnt); // 벽을 세운다(벽조합을 위한 cnt, idx / 바이러스가 퍼지기 전 안전구역개수)
		System.out.println(ans);
	}

	public static void makeWall(int cnt, int idx, int safeCnt) {
		if (cnt == 3) { // 벽을 다세웠다 바이러스를 퍼뜨리자! -> 안전구역 넓이 구하기 -> 최댓값!
			init(); // 벽세운 상태로 copyMap을 쓰기 (바이러스 퍼뜨리기 위해서)
			int safe = spreadVirus(safeCnt - 3 + virus.size()); // 바이러스 퍼뜨리기 (벽세운곳 제거) (기존 바이러스개수는 더해서 보내주기 나중에 뺄것!)
			if (ans >= safe)
				return; // 영역이 최대보다 작으면 그냥 바로 리턴
			ans = safe; // 아니면 영역 최댓값
			return;
		}
		for (int i = idx; i < empty.size(); i++) {
			int x = empty.get(i)[0];
			int y = empty.get(i)[1];
			if (map[x][y] == 1) // 이미 벽이 세워져있으면 넘기기!
				continue;
			map[x][y] = 1; // 벽세우기
			makeWall(cnt + 1, i, safeCnt);
			map[x][y] = 0; // 다시 벽 세웠던거 되돌리기
		}
	}

	private static void init() { // 오리지널 맵 사용
		copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}
	}

	public static int spreadVirus(int safeCnt) {
		Queue<int[]> copyVirus = new LinkedList<int[]>();
		for (int i = 0; i < virus.size(); i++) {
			copyVirus.add(virus.get(i));
		}

		// bfs로 바이러스를 퍼뜨리자
		while (!copyVirus.isEmpty()) {
			int size = copyVirus.size();
			safeCnt -= size; // 바이러스 들어있는 만큼 안전 구역 지우기
			for (int sz = 0; sz < size; sz++) {
				int x = copyVirus.peek()[0];
				int y = copyVirus.peek()[1];
				copyVirus.poll(); // 큐에서 제거
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M) 
						continue;
					if (copyMap[nx][ny] == 1 || copyMap[nx][ny] == 2) // 벽이거나 기존에 바이러스가 있는 공간인 경우 제외
						continue;
					copyMap[nx][ny] = 2; // 바이러스 퍼뜨리기
					copyVirus.add(new int[] { nx, ny });

				}
			}

		}
		return safeCnt;
	}
}
