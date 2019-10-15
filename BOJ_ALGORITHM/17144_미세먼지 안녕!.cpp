#include<cstdio>
#include<vector>
#include<cmath>
#include<cstring>

using namespace std;
typedef struct Pos { int x, y; }Pos;
int R, C, T;
int map[51][51], temp_map[51][51] = { 0, };
int ccw[4] = { 2,0,3,1 }, cw[4] = { 2,1,3,0 }; //위 0 아래 1 오른쪽 2 왼쪽 3 
vector<Pos> air;
int dx[4] = { -1,1,0,0 }, dy[4] = { 0,0,1,-1 };

void map_copy(int(*a)[51], int(*b)[51]) {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			a[i][j] = b[i][j];
		}
	}
}
void print_map() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}
void spread() {
	int temp[51][51] = { 0, };
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if ((i == air[0].x&&j == air[0].y) || (i == air[1].x&&j == air[1].y)) temp[i][j] = -1;
			if (map[i][j] == 0 || map[i][j] == -1) continue;
			int space = 4;
			for (int k = 0; k < 4; k++) {
				int nx = i + dx[k];
				int ny = j + dy[k];
				if ((nx == air[0].x&&ny == air[0].y) || (nx == air[1].x&&ny == air[1].y)) {
					space--;
					continue;
				}
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					space--;
					continue;
				}
				temp[nx][ny] += (int)floor(map[i][j] / 5); //4방향으로 확산
			}
			temp[i][j] += (map[i][j] - (space * (int)floor(map[i][j] / 5))); //확산되고 빠진거 남기기
		}
	}
	map_copy(map, temp);
}

void rotate(int r, int c, int direction[]) {
	int x = r;
	int y = c + 1;
	map[x][y] = 0;

	for (int k = 0; k < 4; k++) {
		while (1) {
			int nx = x + dx[direction[k]];
			int  ny = y + dy[direction[k]];
			if (nx <0 || nx >= R || ny < 0 || ny >= C) break;
			if (r == nx&&c == ny) break;
			map[nx][ny] = temp_map[x][y];
			x = nx;
			y = ny;
		}
	}
}
void Input() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == -1)
				air.push_back(Pos{ i,j });
		}
	}
}
int main() {
	scanf("%d%d%d", &R, &C, &T);
	Input();

	while (T--) {
		spread();
		memset(temp_map, 0, sizeof(temp_map));
		map_copy(temp_map, map);
		rotate(air[0].x, air[0].y, ccw);
		rotate(air[1].x, air[1].y, cw);
	}
	long long res = 0;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (map[i][j] == -1) continue;
			res += map[i][j];
		}
	}
	printf("%lld", res);
}