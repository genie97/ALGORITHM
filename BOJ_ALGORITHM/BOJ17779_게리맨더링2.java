import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재현시 N * N
// 5개의 선거구로 나눠 각 구역은 다섯 선거구 중 하나에 포함되어야 한다
// 선거구는 구역을 최소 한개 이상 포함, 한 선거구에 있는 구역은 모두 연결되어야 한다
// 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있다면 연결되어 있다
// 중간에 통하는 인접한 구역은 0개 이상이어야 하고 모두 같은 선거구에 포함이다
// 선거구 나누기
// (x,y)와 경계 길이 d1,d2정하기
// 경계선
// (x,y)~(x+d1, y-d1)
// (x,y)~(x+d2, y+d2)
// (x+d1,y-d1)~(x+d1+d2, y-d1+d2)
// (x+d2,y+d2)~(x+d2+d1, y+d2-d1)
// 경계선과 경계선 안에 포함되어 있는 곳은 5번 선거구
// 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
// 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
// 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
// 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
public class BOJ17779_게리맨더링2 {
	static int N;
	static int[][] people;
	static int[][] map;
	static int minD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1][N + 1];
		StringTokenizer st = null;
		for (int i = 1; i < people.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < people[i].length; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minD = Integer.MAX_VALUE;

		for (int sum = 2; sum <= N - 1; sum++) {
			// 특정 d1,d2를 선택한다
			for (int d1 = 1; d1 <= sum - 1; d1++) {
				for (int d2 = 1; d2 <= sum - d1; d2++) {
					simulation(2, 2);
				}
			}
		}
		System.out.println(minD);
	}

	static void simulation(int d1, int d2) {
		for (int r = 1; r < people.length; r++) {
			for (int c = 1; c < people[r].length; c++) {
				if (!isRange(2, 4, d1, d2))
					continue;
				map = new int[N + 1][N + 1]; // 구역 나누기 용
				make5(2, 4, d1, d2);
				if (makeAnother(2, 4, d1, d2)) // 구역이 제대로 안나눠지는 경우라 제거
					continue;
				make0To5();
				int diff = findMinSub();
				minD = Math.min(minD, diff);
			}
		}
	}

	static void make0To5() {
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				if (map[i][j] == 0)
					map[i][j] = 5;
			}
		}
	}

	static int findMinSub() {
		int[] sum = new int[6];
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				int v = map[i][j];
				sum[v] += people[i][j];
			}
		}

		int maxV = 0;
		int minV = 0;
		for (int i = 0; i < sum.length; i++) {
			maxV = Math.max(maxV, sum[i]);
			minV = Math.min(minV, sum[i]);
		}
		return maxV - minV;
	}

	static boolean makeAnother(int r, int c, int d1, int d2) {
		int[] area = new int[5];
		// 1 만들기
		for (int i = 1; i < r + d1; i++) {
			for (int j = 1; j <= c; j++) {
				if (map[i][j] == 5)
					break;
				map[i][j] = 1;
				area[1]++;
			}
		}
		// 선거구가 최소 1개씩 가져야 한다는 가정에 위배
		if (area[1] == 0)
			return false;
		// 2만들기
		for (int i = 1; i <= r + d2; i++) {
			for (int j = c + 1; j <= N; j++) {
				if (map[i][j] == 5)
					continue;
				map[i][j] = 2;
				area[2]++;
			}
		}
		// 선거구가 최소 1개씩 가져야 한다는 가정에 위배
		if (area[2] == 0)
			return false;
		// 3만들기
		for (int i = r + d1; i <= N; i++) {
			for (int j = 1; j < c - d1 + d2; j++) {
				if (map[i][j] == 5)
					break;
				map[i][j] = 3;
				area[3]++;
			}
		}
		// 선거구가 최소 1개씩 가져야 한다는 가정에 위배
		if (area[3] == 0)
			return false;
		// 4만들기
		for (int i = r + d2 + 1; i <= N; i++) {
			for (int j = c - d1 + d2; j <= N; j++) {
				if (map[i][j] == 5)
					break;
				map[i][j] = 4;
				area[4]++;
			}
		}
		// 선거구가 최소 1개씩 가져야 한다는 가정에 위배
		if (area[4] == 0)
			return false;
		return true;
	}

	// 5인 구역 부터 만들기
	static void make5(int r, int c, int d1, int d2) {
		// 1세트
		for (int dx = r, dy = c; dx <= r + d1 && dy >= c - d1; dx++, dy--) {
			map[dx][dy] = 5;
		}

		// 2세트
		for (int dx = r, dy = c; dx <= r + d2 && dy <= c + d2; dx++, dy++) {
			map[dx][dy] = 5;
		}

		// 3세트
		for (int dx = r + d1, dy = c - d1; dx <= r + d1 + d2 && dy <= c - d1 + d2; dx++, dy++) {
			map[dx][dy] = 5;
		}

		// 4세트
		for (int dx = r + d2, dy = c + d2; dx <= r + d2 + d1 && dy >= c + d2 - d1; dx++, dy--) {
			map[dx][dy] = 5;
		}
	}

	static boolean isRange(int r, int c, int d1, int d2) {
		if (r + d1 + d2 < 1 || r + d1 + d2 > 7)
			return false;
		if (c - d1 < 1 || c - d1 > 7 || c + d2 < 1 || c + d2 > 7)
			return false;
		return true;
	}
}
