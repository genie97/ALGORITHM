#include<cstdio>
#include<algorithm>
#include<vector>
#include<queue>
using namespace std;

typedef struct Pos {
	int x, y, dir, type;
}Pos;
int N, M;
int map[8][8];
int tmp[8][8];
int res;
int cctv_size = 0;
vector<Pos> vt;
void dfs0(int x, int y) {
	for (int j = y; j < M; j++) {
		if (tmp[x][j] == 6) break;
		if (tmp[x][j] == 0) tmp[x][j] = -1;
	}
}
void dfs1(int x, int y) {
	for (int i = x; i < N; i++) {
		if (tmp[i][y] == 6) break;
		if (tmp[i][y] == 0) tmp[i][y] = -1;
	}
}
void dfs2(int x, int y) {
	for (int j = y; j >= 0; j--) {
		if (tmp[x][j] == 6) break;
		if (tmp[x][j] == 0) tmp[x][j] = -1;
	}
}
void dfs3(int x, int y) {
	for (int i = x;  i >=0; i--) {
		if (tmp[i][y] == 6) break;
		if (tmp[i][y] == 0) tmp[i][y] = -1;
	}
}

void move() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			tmp[i][j] = map[i][j];
		}
	}

	for (int i = 0; i < vt.size(); i++) {
		if (vt[i].type == 1) { //보는 방향 4가지
			if (vt[i].dir == 0) dfs0(vt[i].x, vt[i].y);
			if (vt[i].dir == 1) dfs1(vt[i].x, vt[i].y);
			if (vt[i].dir == 2) dfs2(vt[i].x, vt[i].y);
			if (vt[i].dir == 3) dfs3(vt[i].x, vt[i].y);
		}
		if (vt[i].type == 2) { //보는 방향 상하 혹은 좌우
			if (vt[i].dir == 0 || vt[i].dir == 2) {
				dfs0(vt[i].x, vt[i].y);
				dfs2(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 1 || vt[i].dir == 3) { 
				dfs1(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
		}
		if (vt[i].type == 3) { //90도 각을 이루게
			if (vt[i].dir == 0) { 
				dfs2(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 1) {
				dfs0(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 2) {
				dfs0(vt[i].x, vt[i].y);
				dfs1(vt[i].x, vt[i].y);
			}	
			if (vt[i].dir == 3) {
				dfs1(vt[i].x, vt[i].y);
				dfs2(vt[i].x, vt[i].y);
			}
		}
		if (vt[i].type == 4) { //3방향
			if (vt[i].dir == 0) {
				dfs0(vt[i].x, vt[i].y);
				dfs1(vt[i].x, vt[i].y);
				dfs2(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 1) {
				dfs1(vt[i].x, vt[i].y);
				dfs2(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 2) {
				dfs0(vt[i].x, vt[i].y);
				dfs2(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
			if (vt[i].dir == 3) {
				dfs0(vt[i].x, vt[i].y);
				dfs1(vt[i].x, vt[i].y);
				dfs3(vt[i].x, vt[i].y);
			}
		}
		if (vt[i].type == 5) {
			dfs0(vt[i].x, vt[i].y);
			dfs1(vt[i].x, vt[i].y);
			dfs2(vt[i].x, vt[i].y);
			dfs3(vt[i].x, vt[i].y);
		}
	}

	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (tmp[i][j] == 0) cnt++;
		}
	}
	res = min(res, cnt);
}

void make_perm(int cnt) {
	if (cnt == cctv_size) {
		move(); //vt벡터가지고 move하기
		return;
	}
	vt[cnt].dir = 0;
	make_perm(cnt + 1);
	vt[cnt].dir = 1;
	make_perm(cnt + 1);
	vt[cnt].dir = 2;
	make_perm(cnt + 1);
	vt[cnt].dir = 3;
	make_perm(cnt + 1);

}

int main() {
	res = 987654321;
	scanf("%d%d", &N, &M);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] > 0 && map[i][j] < 6) {
				vt.push_back({ i,j,0,map[i][j] });
			}
		}
	}
	cctv_size = vt.size();
	make_perm(0);
	printf("%d", res);
}