#include<cstdio>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;
typedef struct Pos {
	int x, y;
};

int N, M;
int res=987654321; //답
int map[55][55], temp[55][55];
int clean_space = 0; //바이러스 없는 공간
int virus_check[11]; //조합용 체크
vector<Pos> virus_pos; //바이러스 위치 (활성+비활성)
vector<Pos> vt; //조합용 벡터
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };

void copy_map() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			temp[i][j] = map[i][j];
		}
	}
}

int bfs() {
	queue<Pos> q;
	for (int i = 0; i < vt.size(); i++)
		q.push(Pos{ vt[i].x,vt[i].y });
	int size = 0;
	int space = 0; //바이러스 퍼진 곳
	int time = 0; //바이러스 퍼지는 시간
	
	while (!q.empty()) {
		if (space == clean_space) 
			return time;
		size = q.size();
		for (int i = 0; i < size; i++) {
			int x = q.front().x;
			int  y = q.front().y;
			q.pop();
			for (int j = 0; j < 4; j ++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (temp[nx][ny] == 0 || temp[nx][ny] == 2) {
					if (temp[nx][ny] == 0) space++;
					temp[nx][ny] = 3; //퍼진곳
					q.push(Pos{ nx,ny });
				}
			}
		}
		time++;
	}
	return 987654322;
}
void make_comb(int idx, int cnt) {
	if (cnt == M) {
		copy_map();
		for (int i = 0; i < vt.size(); i++) {
			int x = vt[i].x;
			int y = vt[i].y;
			temp[x][y] = 3;
		}
		int spread_time = 0;
		spread_time = bfs();
		res = min(spread_time, res);
		return;
	}
	for (int i = idx; i < virus_pos.size(); i++) {
		if (virus_check[i] == 1) continue;
		virus_check[i] = 1;
		vt.push_back(virus_pos[i]);
		make_comb(i, cnt + 1);
		virus_check[i] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 2) 
				virus_pos.push_back(Pos{ i,j });
			if (map[i][j] == 0)
				clean_space++;
		}
	}
	make_comb(0, 0);
	printf("%d", res == 987654321 ? -1 : res);
}