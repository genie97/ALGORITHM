#include<cstdio>
#include<string>
#include<algorithm>
using namespace std;
char map[51][51];
int check_chess_B(int x, int y) {
	int cnt = 0;
	for (int i = x; i < x + 8; i++) {
		for (int j = y; j < y + 8; j++) {
			if (((i-x) + (j-y)) % 2 == 0 && map[i][j] != 'B') cnt++;
			else if (((i-x) +( j-y)) % 2 == 1 && map[i][j] != 'W')cnt++;
		}
	}
	return cnt;
}
int check_chess_W(int x, int y) {
	int cnt = 0;
	for (int i = x; i < x + 8; i++) {
		for (int j = y; j < y + 8; j++) {
			if (((i - x) + (j - y)) % 2 == 0 && map[i][j] != 'W') cnt++;
			else if (((i - x) + (j - y)) % 2 == 1 && map[i][j] != 'B')cnt++;
		}
	}
	return cnt;
}
int main() {
	int M, N;
	int MIN = 987654321;
	scanf("%d %d", &M, &N);
	for (int i = 0; i < M; i++) {
		scanf("%s", map[i]);
	}
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (i + 7 <= M-1 && j + 7 <= N-1) {
				if (map[i][j] == 'B') { //만약에 B면 확인
					MIN = min(MIN, check_chess_B(i, j));
					map[i][j] = 'W'; //B인걸 W로 바꿔서 확인
					MIN = min(MIN, check_chess_W(i, j) + 1);
					map[i][j] = 'B'; //원래대로 변경
				}
				else {
					MIN = min(MIN, check_chess_W(i, j));
					map[i][j] = 'B'; //W인걸 B로 바꿔서 확인
					MIN = min(MIN, check_chess_B(i, j) + 1);
					map[i][j] = 'W'; //원래대로 변경
				}
			}
		}
	}
	printf("%d\n", MIN);
}
