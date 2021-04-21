import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// N*N 격자에 파이어볼 M개
// 각자 위치에서 이동 대기
// i번의 위치 (r,c) 질량 m

// 파이어볼 방향
// 7 0 1
// 6   2
// 5 4 3

// 모든 상어가 자신의 방향 d로 속력 s만큼 이동
// 같은 칸에 여러개의 파이어볼이 있을 수 있다

// 격자의 행과열은 1~n까지
// 1번행은 n과 연결
// 1번 열은 n과 연결
// 하나의 공간 같은 느낌이라는 뜻

// 이동이 끝난 후에는 2개 이상의 파이어볼이 있는 칸에서 일어나는 일
// 1. 같은 칸에 있는 파이어볼은 하나로 합쳐짐
// 2. 파이어볼은 4개로 나누어짐
// 3. 나누어진 파이어볼
// 질량 => 질량합/5
// 속력 => 속력합/합쳐진개수
// 합쳐지는 파이어볼의 방향이 모두 홀수거나 짝수면 0246
// 아니면 1357
// 질량이 0이면 사라짐

// K번 명령후 남아있는 파이어볼 질량의 합 구하기

public class BOJ20056_마법사상어와파이어볼 {

	static class FireBall {
		int r;
		int c;
		int m;
		int s;
		int d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int N, M, K;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<FireBall> ball_list;
	static ArrayList<FireBall>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());

		ball_list = new ArrayList<>();
		map = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ball_list.add(new FireBall(r, c, m, s, d));
		}

		while (K-- > 0) {
			// 1. 모든 파이어볼 이동
			move_fireball();
			// 2. 이동 후
			change_status();
		}

		// 남아있는 파이어볼의 질량합
		int ans = 0;
		for (int i = 0; i < ball_list.size(); i++) {
			ans += ball_list.get(i).m;
		}
		System.out.println(ans);

	}

	static void move_fireball() {
		for (FireBall fb : ball_list) {

			int nr = fb.r + (dx[fb.d] * (fb.s % N));
			int nc = fb.c + (dy[fb.d] * (fb.s % N));

			if (nr < 0) {
				nr += N;
			} else if (nr >= N) {
				nr -= N;
			}

			if (nc < 0) {
				nc += N;
			} else if (nc >= N) {
				nc -= N;
			}
			// 새로운 위치에 추가
			fb.r = nr;
			fb.c = nc;

			map[nr][nc].add(fb);
		}
	}

	static void change_status() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].size() <= 1) {
					if (map[i][j].size() == 1)
						map[i][j].clear();
					continue;
				}

				// 파이어볼 하나로 나누기
				int new_m = 0;
				int new_s = 0;

				boolean ODD = false;
				boolean EVEN = false; 

				for (FireBall new_ball : map[i][j]) {
					if (new_ball.d % 2 == 0) {
						EVEN = true;
					} else {
						ODD = true;
					}

					new_m += new_ball.m;
					new_s += new_ball.s;
					ball_list.remove(new_ball);
				}

				new_m = (int) (Math.floor((double) new_m / 5));
				new_s = (int) (Math.floor((double) new_s / map[i][j].size()));

				// 해당 위치는 초기화
				map[i][j].clear();

				// 질량이 0이면 제거됨
				if (new_m == 0)
					continue;

				if (ODD && EVEN) {
					for (int k = 1; k <= 7; k += 2) {
						ball_list.add(new FireBall(i, j, new_m, new_s, k));
					}
				} else { // 모두 짝수 혹은 모두 홀수
					for (int k = 0; k <= 6; k += 2) {
						ball_list.add(new FireBall(i, j, new_m, new_s, k));
					}
				}
			}
		}
	}

}
