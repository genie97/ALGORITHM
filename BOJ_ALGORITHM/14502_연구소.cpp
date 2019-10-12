//32ms (2019-10-13 update)
#include<cstdio>
#include<vector>
#include<algorithm>

using namespace std;

typedef struct Pos {
	int x, y;
}Pos;

int N, M, ans = 0;
int map[8][8], temp[8][8];
int num[64];
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
vector<Pos> vt; //조합용 벡터
vector<Pos> virus, space;

int virus_cnt = 0;

void map_copy() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			temp[i][j] = map[i][j];
		}
	}
}
void dfs(int x, int y) {
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
		if (temp[nx][ny] == 0) {
			temp[nx][ny] = 2;
			virus_cnt++;
			dfs(nx, ny);
		}
	}
}
void make_comb(int idx, int cnt) {
	if (cnt == 3) {
		map_copy(); //기존 맵 유지시키기 위해 카피하기
		for (int i = 0; i < vt.size(); i++) { // 벽으로 세우려고 했던 곳 1로 변경
			int x = vt[i].x;
			int y = vt[i].y;
			temp[x][y] = 1;
		}
		virus_cnt = 0; //바이러스 개수 초기화!
		for (int i = 0; i < virus.size(); i++) {
			dfs(virus[i].x, virus[i].y); //바이러스 있는 위치만 보면서 퍼뜨리기
		}
		int safe = space.size() - (3 + virus_cnt); //전체 0인 공간 - 벽세운3칸 - 바이러스 퍼진 칸
		ans = max(ans, safe);
		return; //return을 안하면 시간초과가 발생하므로 꼭 할 것!
	}

	for (int i = idx; i < space.size(); i++) {
		int x = space[i].x;
		int y = space[i].y;
		if (num[i] == 1) continue;
		num[i] = 1;
		vt.push_back(space[i]); //공간 조합 벡터에 넣기
		make_comb(i, cnt + 1);
		num[i] = 0;
		vt.pop_back();
	}
}

int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 2) { // 바이러스 위치 저장
				Pos point;
				point.x = i;
				point.y = j;
				virus.push_back(point);
			}
			else if (map[i][j] == 0) { // 벽을 놓을 수 있는 공간만 따로 빼기
				Pos point;
				point.x = i;
				point.y = j;
				space.push_back(point);
			}
		}
	}
	make_comb(0, 0); //공간에 대한 조합 (순열을 안하는 이유: 순서상관없이 3개만 뽑으면 된다!)
	printf("%d", ans);
}

/* 276ms
#include<cstdio>
#include<algorithm>
#include<vector>
#include<queue>
using namespace std;
typedef pair<int, int> p;
int N, M, maxV=0;
int map[9][9], temp[9][9];
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };

void copyMap(int (*a)[9], int (*b)[9]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			a[i][j] = b[i][j];
		}
	}
}
void bfs() {
	int new_map[9][9];
	copyMap(new_map, temp);
	queue<p> virus;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (new_map[i][j] == 2) {
				virus.push({ i,j });
			}
		}
	}
	while (!virus.empty()) {
		int x = virus.front().first;
		int y = virus.front().second;
		virus.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (new_map[nx][ny] == 0) {
				new_map[nx][ny] = 2;
				virus.push({ nx, ny });
			}
		}
	}
	 int area=0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (new_map[i][j] == 0)
				area++;
		}
	}
	maxV = max(maxV, area);
}
void make_wall(int cnt) {
	if (cnt == 3) {
		bfs();
		return;
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (temp[i][j] == 0) {
				temp[i][j] = 1;
				make_wall(cnt + 1);
				temp[i][j] = 0;
			}
		}
	}
}
//0은 빈칸 1은 벽 2는 바이러스 벽은 3개를 세워야함
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 0) {
				copyMap(temp, map);
				temp[i][j] = 1;
				make_wall(1);
				temp[i][j] = 0;
			}
		}
	}
	printf("%d\n", maxV);
}
*/
