import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 길이가 n인 컨베이어 벨트 / 2n이 위아래로 감싸서 돌고 있음
// 2n번칸은 1번으로 이동
// i번 칸의 내구도는 Ai

// 1번칸이 있는 곳이 올라가는 위치, n번칸이 있는 위치를 내려가는 위치
// 어떤 칸으로 올라가거나 이동하면 그 칸의 내구도는 1만큼 감소, 0인칸에는 로봇이 갈 수없다

// 1번 단계) 벨트가 한 칸 회전
// 2번 단계) 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동할 수 있다면 이동
// 이동할 수 없다면 가만히
// 로봇이 이동할 수 있는 조건 - 로봇이 없고, 내구도가 1이상 남아있어야함
// 3번 단계) 올라가는 위치에 로봇이 없다면 로봇을 하나 올림
// 4번 단계) 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
// 종료되었을 때 몇번째 단계가 진행중이었는지 구하기

// 3 2 N, K
// 1 2 1 2 1 2 2N개의 내구도

public class BOJ20055_컨베이어벨트위의로봇 {
	static int N, K;
	static Pos[] A;

	static class Pos {
		int value;
		boolean isHere;

		public Pos(int value) {
			this.value = value;
			this.isHere = false;
		}

		public Pos(int value, boolean isHere) {
			this.value = value;
			this.isHere = isHere;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");

		A = new Pos[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			A[i] = new Pos(Integer.parseInt(st.nextToken()));
		}
		int step = 0;

		int zero_space = 0;
		while (zero_space < K) {
			zero_space = 0;

			// 1. 벨트가 한칸 회전
			A = move_belt();

			// 2. 로봇 이동
			move_robot();

			// 3. 올라가는 위치에 로봇이 없으면 올리기
			if (!A[0].isHere && A[0].value > 0) {
				A[0].isHere = true;
				A[0].value--;
			}
			step++;

			// 4. 내구도 0인 곳 확인하기
			for (int i = 0; i < A.length; i++) {
				if (A[i].value <= 0)
					zero_space++;
			}
		}
		System.out.println(step);
	}

	static void move_robot() {
		if (A[N - 1].isHere) {
			A[N - 1].isHere = false;
		}
		for (int i = N - 2; i >= 0; i--) {
			if (!A[i].isHere) // 로봇이 없으면 제외
				continue;
			int npos = (i + 1) % N; // 다음에 갈 자리
			// 다음에 갈자리에 로봇이 있거나 내구도가 0이면 갈 수 없음
			if (A[npos].isHere || A[npos].value < 1)
				continue;

			// 만약에 갈 수 있다면?
			A[npos].isHere = true;
			A[i].isHere = false;
			A[npos].value--;
		}
	}

	static Pos[] move_belt() {
		Pos[] copy = new Pos[A.length];
		for (int i = 0; i < copy.length; i++) {
			copy[(i + 1) % (2 * N)] = A[i];
			if (i == N - 1 && A[i].isHere) { // 내려가는 위치
				copy[N].isHere = false;
			}
		}
		return copy;
	}
}
