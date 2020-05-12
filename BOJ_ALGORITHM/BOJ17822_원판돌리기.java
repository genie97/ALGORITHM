import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17822_원판돌리기 {
	static int N, M, T;
	static int[][] pan;
	static int[] x, d, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 원판의 개수
		M = Integer.parseInt(st.nextToken()); // 하나의 원판에 적힌 정수 개수
		T = Integer.parseInt(st.nextToken()); // 원판을 돌릴 횟수

		pan = new int[N + 1][M + 1]; // 원판 모양
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		x = new int[T]; // 번호 (xi의 배수인 원판을 돌릴 것이다)
		d = new int[T]; // 0: 시계방향, 1: 반시계방향
		k = new int[T]; // 몇 칸 회전 시킬지!
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
			k[i] = Integer.parseInt(st.nextToken());
		}

		// 1. T번 회전
		for (int i = 0; i < T; i++) {
			simulation(i);
		}

		// 2. 총합 구하기
		int ans = sum();
		System.out.println(ans);
	}

	static int sum() {
		int total = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				total += pan[i][j];
			}
		}
		return total;
	}

	static void simulation(int idx) {
		// 1. 회전하기
		int cnt = k[idx];
		for (int i = 0; i < cnt; i++) {
			rotate(x[idx], d[idx]);
		}

//		print();

		// 2-1. 인접한 수를 지우기
		boolean removable = remove();
//		print();
//		System.out.println(removable);

		// 2-2. (지우는 수가 없다면!) 평균을 확인해서 +-1하기
		if (!removable) {
			checkMin();
		}
	}

	static void checkMin() {
		double total = 0;
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (pan[i][j] == 0)
					continue;
				total += pan[i][j];
				cnt++;
			}
		}
		double avg = total / cnt;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (pan[i][j] == 0)
					continue;
				if (pan[i][j] > avg) {
					pan[i][j] -= 1;
				} else if (pan[i][j] < avg) {
					pan[i][j] += 1;
				}
			}
		}

	}

	static boolean remove() {
		// 한 원판에서 인접하면서 같은 숫자
		// 인접한 숫자가 있는 인덱스를 저장
		ArrayList<int[]> adjList = new ArrayList<>();
		// 가로로 보기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int num = pan[i][j];
				boolean flag = false;
				if (num == 0)
					continue;
				if (j == 1) {
					if (num == pan[i][M]) {
						adjList.add(new int[] { i, M });
						flag = true;
					}
					if (num == pan[i][j + 1]) {
						adjList.add(new int[] { i, j + 1 });
						flag = true;
					}
				} else if (j == M) {
					if (num == pan[i][1]) {
						adjList.add(new int[] { i, 0 });
						flag = true;
					}
					if (num == pan[i][j - 1]) {
						adjList.add(new int[] { i, j - 1 });
						flag = true;
					}
				} else {
					if (num == pan[i][j - 1]) {
						adjList.add(new int[] { i, j - 1 });
						flag = true;
					}
					if (num == pan[i][j + 1]) {
						adjList.add(new int[] { i, j + 1 });
						flag = true;
					}
				}
				if (flag)
					adjList.add(new int[] { i, j });
			}
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				int num = pan[j][i];
				if (num == 0)
					continue;
				int nj = j + 1;
				boolean flag = false;
				if (nj <= N && num == pan[nj][i]) {
					adjList.add(new int[] { nj, i });
					num = pan[nj][i];
					nj++;
					flag = true;
				}
				if (flag)
					adjList.add(new int[] { j, i });
			}
		}

		if (adjList.size() == 0)
			return false;

		for (int i = 0; i < adjList.size(); i++) {
			int x = adjList.get(i)[0];
			int y = adjList.get(i)[1];
			if (pan[x][y] != 0) {
				pan[x][y] = 0;
			}
		}
		return true;
	}

	static void rotate(int pan_num, int dir) {
		if (dir == 0) {
			for (int i = pan_num; i <= N; i += pan_num) {
				int[] temp = new int[M + 1];
				for (int j = 1; j <= M; j++) {
					if (j == M) {
						temp[1] = pan[i][j];
					} else {
						temp[j + 1] = pan[i][j];
					}
				}
				for (int j = 1; j <= M; j++) {
					pan[i][j] = temp[j];
				}
			}
		} else {
			for (int i = pan_num; i <= N; i += pan_num) {
				int[] temp = new int[M + 1];
				for (int j = 1; j <= M; j++) {
					if (j == 1) {
						temp[M] = pan[i][j];
					} else {
						temp[j - 1] = pan[i][j];
					}
				}
				for (int j = 1; j <= M; j++) {
					pan[i][j] = temp[j];
				}
			}
		}
	}
//
//	static void print() {
//		for (int i = 1; i <= N; i++) {
//			System.out.println(Arrays.toString(pan[i]));
//		}
//		System.out.println("==========================");
//	}
}
