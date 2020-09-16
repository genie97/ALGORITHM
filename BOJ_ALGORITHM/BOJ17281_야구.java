import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 하나의 이닝은 공격과 수비
// N이닝 동안 게임 진행
// 한 이닝에 3아웃이 발생하면 이닝 종료 후 공수 교대
// 경기 시작 전 타순 지정 => 한번만 타순을 지정한다는 뜻
// 9까지 공을 쳤는데 3아웃이 아니면 다시 1번타자가 타석
// 이닝이 바껴도 타순은 유지
// 홈으로 도착하면 1득점
// 타자가 공을 쳐서 얻을 수 있는 결과
// (1)안타: 타자와 모든 주자가 한 루씩 진루한다.
// (2)2루타: 타자와 모든 주자가 두 루씩 진루한다.
// (3)3루타: 타자와 모든 주자가 세 루씩 진루한다.
// (4)홈런: 타자와 모든 주자가 홈까지 진루한다.
// (0)아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
// 팀은 1~9번 
// 1번선수 -> 4번타자로 이미 결정
public class BOJ17281_야구 {
	static int[][] scoreMap;
	static int[] order;
	static boolean[] visit;
	static int max;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		scoreMap = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				scoreMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[8];
		visit = new boolean[9];
		// 순열을 만들고 거기서 이닝을 시작해서 계산해봄
		max = Integer.MIN_VALUE;
		makePerm(0);
		System.out.println(max);
	}

	static void makePerm(int cnt) {
		if (cnt == 8) { // 경기 전 타석을 다 정했다
			int score = simultaion();
			if (max >= score)
				return;
			max = Math.max(max, score);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			order[cnt] = i;
			makePerm(cnt + 1);
			visit[i] = false;
		}
	}

	static int simultaion() {
		int score = 0;

		int curHitter = 0; // 현재 타자의 인덱스 저장
		int finish = 0;
		int[] new_order = new int[9];
		// 새로운 순서 (3번째 지정 순서 포함)
		new_order[3] = 0;
		for (int i = 0; i <= 2; i++) {
			new_order[i] = order[i];
		}
		for (int i = 3; i < order.length; i++) {
			new_order[i + 1] = order[i];
		}

		for (int i = 0; i < scoreMap.length; i++) {
			int outCount = 0;
			boolean[] pos = new boolean[4]; //1,2,3
			while (true) {
				curHitter = curHitter % 9; // 타자가 계속 순환 (인덱스)
				int res = scoreMap[i][new_order[curHitter]];

				if (outCount == 3) { // 아웃카운트가 3이면 이닝이 바뀜
					break;
				}

				if (res == 0) {
					outCount++;
					curHitter++;
					continue;
				}

				if (res == 4) { // 모든 타자가 다 나감
					score++; // 현재 타자 점수 받음
					for (int j = 1; j < pos.length; j++) {
						if (pos[j]) {
							score++;
							pos[j] = false;
						}
					}
					curHitter++;
					continue;
				}

				// res가 1,2,3인 경우는 주자들이 먼저 주루한다
				boolean[] tmp = new boolean[5];
				for (int j = 1; j < pos.length; j++) {
					if (pos[j]) {
						int nj = j + res;
						if (nj >= 4) { // 4보다 크면 (홈 이상)
							score++;
						} else {
							tmp[nj] = true;
						}
					}
				}
				pos = tmp;

				// 마지막으로 타자가 주루
				pos[res] = true;
				curHitter++;
			}
		}
		return score;
	}
}
