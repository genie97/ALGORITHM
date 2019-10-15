#include<cstdio>
#include<cmath>
#include<vector>
#include<cstring>
using namespace std;
typedef struct Pos { int x, y; }Pos;
int N, L, R;
int A[51][51] = { 0, }, visit[51][51] = {0,};
int n=0; //국경선 연 곳 개수
int people; //사람 수
int dx[4] = { 1,-1,0,0 }, dy[] = { 0,0,1,-1 };
vector<Pos> vt;
void dfs(int x, int y) {
	for (int i = 0; i < 4; i ++ ) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
		int dif = abs(A[x][y] - A[nx][ny]);
		if (L<=dif &&dif <= R && !visit[nx][ny]) {
			visit[nx][ny] = 1;
			vt.push_back({ nx,ny });
			people += A[nx][ny];
			n++;
			dfs(nx, ny);
		}
	}
}
int main() {
	scanf("%d%d%d", &N, &L, &R);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d",&A[i][j]);
		}
	}
	int res = 0;
	while (1) {
		memset(visit, 0, sizeof(visit));
		bool found = false; // 인구이동 불가능으로 시작

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j]) continue;
				visit[i][j] = 1;
				people = A[i][j];
				n = 1; //처음 1로 시작
				vt.clear();
				vt.push_back({ i,j });
				dfs(i, j); //연합국 찾고, 총 사람 수 구하기

				if (n >= 2) { //연합국이 2개 이상이면 인구이동이 발생
					found = true;
					for (int k = 0; k < vt.size(); k++) {
						A[vt[k].x][vt[k].y] = people / n; //인구이동 완료
					}
				}
			}
		}
		if (found) res++;
		else break;
	}
	printf("%d", res);
}