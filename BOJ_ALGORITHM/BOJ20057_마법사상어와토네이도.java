import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 격자의 가운데칸부터 토네이도 이동
// 토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 a가 적혀있는 칸으로 이동
// 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼 (소수점은 버림)
// a로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같음
// 모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양이 더해짐
// 격자밖으로 나간 모래의 양

public class BOJ20057_마법사상어와토네이도 {
	static int N;
	static int[][] A;
	static int[] t_dx = { 0, 1, 0, -1 };
	static int[] t_dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//
		int origin_sum = cal_sum();

		// 토네이도의 이동
		move_tornado();

		int change_sum = cal_sum();
//		System.out.println(origin_sum);
//		System.out.println(change_sum);
		System.out.println(origin_sum - change_sum);
	}

	static int cal_sum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += A[i][j];
			}
		}
		return sum;
	}

	// 좌 0 하 1 우 2 상 3
	static void move_tornado() {
		// 토네이도의 시작점은 격자의 정중앙
		int t_x = N / 2;
		int t_y = N / 2;
		
		// 첫 방향은 좌측
		int dir = 0;
		
		// 방향 전환하는 step수) 1 1 2 2 3 3 ... N-1 N-1 N-1 의 규칙을 갖는다
		// 결국 step수가 변화하는 규칙)  2 4 6 ... 2(N-1) 
		int cnt = 0;
		int origin_step = 1; 
		int copy_step = 1;

		// 토네이도 방향에 따라 모래가 흩뿌려지는 기본 방향과 퍼센트 세팅
		int[] s_dx = setup(dir)[0];
		int[] s_dy = setup(dir)[1];
		int[] s_percentage = setup(dir)[2];

		// 토네이도가 (0,0)자리에 도착하면 종료
		while (t_x != 0 || t_y != 0) {
			cnt++;
	
			int sum = 0;

			// 모래가 있는 위치 y
			int s_x = t_x + t_dx[dir];
			int s_y = t_y + t_dy[dir];

			// 비율이 있는 곳
			for (int i = 0; i < s_percentage.length; i++) {
				int nx = t_x + s_dx[i];
				int ny = t_y + s_dy[i];
				int amount = (int) (A[s_x][s_y] * s_percentage[i] / 100);
				sum += amount;

				// 격자를 넘어가는 경우에는 날라가는 모래임
				// 넘어가지 않는 경우에만 판에 표기
				if (isIn(nx, ny)) {
					A[nx][ny] += amount;
				}
			}

			// 알파가 있는 곳 (모래가 있던 y칸 - 비율이 있는 모래의 총 합)
			int a_x = t_x + (2 * t_dx[dir]);
			int a_y = t_y + (2 * t_dy[dir]);
			if (isIn(a_x, a_y)) {
				A[a_x][a_y] += A[s_x][s_y] - sum;
			}

			// 원래 모래가 있던 곳은 리셋
			A[s_x][s_y] = 0;

			// 토네이도 한 칸 전진
			t_x += t_dx[dir];
			t_y += t_dy[dir];

			// 방향 전환
			copy_step--;
			if (copy_step == 0) { // 정해진 step수만큼 이동했으므로 방향전환 일어남
				dir = (dir + 1) % 4;
				copy_step = origin_step;
				
				// 방향 전환 일어나면 기본 세팅 변경
				s_dx = setup(dir)[0];
				s_dy = setup(dir)[1];
				s_percentage = setup(dir)[2];
			}

			// 방향 전환 두번했으므로 step수 하나 늘려주기
			if (cnt == 2 * origin_step) {
				copy_step = origin_step + 1;
				origin_step = copy_step;
				cnt = 0;
			}
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static int[][] setup(int dir) {
		if (dir == 0) {
			int[][] value = { { 0, -1, 1, -1, 1, -1, 1, -2, 2 }, { -3, 0, 0, -1, -1, -2, -2, -1, -1 },
					{ 5, 1, 1, 7, 7, 10, 10, 2, 2 } };
			return value;
		} else if (dir == 1) {
			int[][] value = { { 3, 0, 0, 1, 1, 2, 2, 1, 1 }, { 0, -1, 1, -1, 1, -1, 1, -2, 2 },
					{ 5, 1, 1, 7, 7, 10, 10, 2, 2 } };
			return value;
		} else if (dir == 2) {
			int[][] value = { { 0, -1, 1, -1, 1, -1, 1, -2, 2 }, { 3, 0, 0, 1, 1, 2, 2, 1, 1 },
					{ 5, 1, 1, 7, 7, 10, 10, 2, 2 } };
			return value;

		} else {
			int[][] value = { { -3, 0, 0, -1, -1, -2, -2, -1, -1 }, { 0, -1, 1, -1, 1, -1, 1, -2, 2 },
					{ 5, 1, 1, 7, 7, 10, 10, 2, 2 } };
			return value;
		}
	}
}
