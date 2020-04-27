import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17837_새로운게임2 {
	static int N, K;
	static int[][] map;
	static ArrayList<Integer>[][] horse_map;
	static int[] dx = { 0, 0, -1, 1 }; // 우 좌 상 하 순으로 0,1,2,3
	static int[] dy = { 1, -1, 0, 0 };
	static Horse[] list;

	static class Horse {
		int r;
		int c;
		int d;

		public Horse(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		list = new Horse[K + 1];
		horse_map = new ArrayList[N + 1][N + 1]; // 맵에 말의 번호를 저장한다
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1, idx = 0; j <= N; j++, idx += 2) {
				map[i][j] = str.charAt(idx)-'0';
				horse_map[i][j] = new ArrayList<>();
 			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			list[i] = (new Horse(r, c, d));
			horse_map[r][c].add(i);
		}

		int turn = 0; // 종료 횟수
		int rmnum = 0; // 위치 제거 번호
		ArrayList<Integer> tempList = new ArrayList<>();
ex:		while (true) {
			turn++;
			// 1000보다 커지거나 절대로 종료되지 않는 경우
			if (turn > 1000) {
				turn = -1;
				break;
			}
			for (int i = 1; i <= K; i++) {
				int r = list[i].r;
				int c = list[i].c;
				int d = list[i].d;

				// 앞으로 이동하려는 곳
				int nr = r + dx[d];
				int nc = c + dy[d];
				int nd = d;
				
				if (!isRange(nr, nc) || map[nr][nc] == 2) { // 파란색이나 칸을 넘는 경우
					// 방향을 바꾸고 한칸 이동한다
					nd = changeDirection(d);
					list[i].d = nd;	
					// 새로운 이동방향이 흰색이나, 빨간색인 경우 위치가 변경
					// 파란색인 경우 그대로 있음
					nr =  r + dx[nd];
					nc =  c + dy[nd];
				}
				if(isRange(nr, nc) && map[nr][nc] != 2){
					if (map[nr][nc] == 0) { // 흰색인 경우 그대로 위로 쌓임
						rmnum = horse_map[r][c].remove(horse_map[r][c].size()-1);
						while(rmnum != i) {
							tempList.add(rmnum);
							rmnum = horse_map[r][c].remove(horse_map[r][c].size()-1);
						}
						tempList.add(rmnum);
						
						while(!tempList.isEmpty()) {
							rmnum = tempList.remove(tempList.size()-1);
							// 위치 이동
							list[rmnum].r = nr;
							list[rmnum].c = nc;
							
							horse_map[nr][nc].add(rmnum);
						}
					} else { // 빨간색인 경우 순서가 뒤집혀서 쌓임
						rmnum = horse_map[r][c].remove(horse_map[r][c].size()-1);
						while(rmnum != i) {
							list[rmnum].r = nr;
							list[rmnum].c = nc;
							horse_map[nr][nc].add(rmnum);
							rmnum = horse_map[r][c].remove(horse_map[r][c].size()-1);
						}
						list[rmnum].r = nr;
						list[rmnum].c = nc;
						horse_map[nr][nc].add(rmnum);
					}
					
					if(horse_map[nr][nc].size() >= 4) {
						break ex;
					}
				}
			}
		}
		System.out.println(turn);
	}

	static int changeDirection(int d) {
		switch (d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return -1;
	}

	static boolean isRange(int nr, int nc) {
		return nr > 0 && nc > 0 && nr <= N && nc <= N;
	}
}
