#include <stdio.h>
#include <cstdlib>
#include <iostream>
#include <memory.h>

using namespace std;
char map[21][21];
bool ans;
int visit[21][21][16][4];
int dx[] = { 1,0,-1,0 };
int dy[] = { 0,1,0,-1 };
int r, c;
bool move(int mx, int my, int point, int memory) {
	if (map[mx][my] == '@') {
		return true;
	}
	else if (map[mx][my] == '<') {
		point = 3;
	}
	else if (map[mx][my] == '>') {
		point = 1;
	}
	else if (map[mx][my] == '^') {
		point = 2;
	}
	else if (map[mx][my] == 'v') {
		point = 0;
	}
	else if (map[mx][my] == '_') {
		if (memory == 0)
			point = 1;
		else
			point = 3;
	}
	else if (map[mx][my] == '|') {
		if (memory == 0)
			point = 0;
		else
			point = 2;
	}


	else if (map[mx][my] - '0' >= 0 && map[mx][my] - '0' <= 9) {
		memory = map[mx][my] - '0';

	}
	else if (map[mx][my] == '+') {
		if (memory == 15)
			memory = 0;
		else
			memory += 1;
	}
	else if (map[mx][my] == '-') {
		if (memory == 0)
			memory = 15;
		else
			memory -= 1;
	}
	if (map[mx][my] == '?') {
		for (int i = 0; i < 4; ++i) {
			if (visit[mx][my][memory][i]) {
				return false;
			}
			else
				visit[mx][my][memory][i] = 1;
			int nx = mx + dx[i];
			int ny = my + dy[i];
			if (ny > c)
				ny = 0;
			else if (ny < 0)
				ny = c - 1;
			else if (nx > r)
				nx = 0;
			else if (nx < 0)
				nx = r - 1;
			if (move(nx, ny, i, memory) == true)
				return true;

		}
	}
	else {
		if (visit[mx][my][memory][point]) {
			return false;
		}
		else visit[mx][my][memory][point] = 1;

		int nx = mx + dx[point];
		int ny = my + dy[point];
		if (ny > c)
			ny = 0;
		else if (ny < 0)
			ny = c - 1;
		else if (nx > r)
			nx = 0;
		else if (nx < 0)
			nx = r - 1;
		if (move(nx, ny, point, memory) == true)
			return true;

	}
}
int main() {
	int num;

	scanf("%d", &num);
	for (int a = 0; a < num; ++a) {
		ans = false;
		scanf("%d %d", &r, &c);
		for (int i = 0; i < r; ++i) {
			scanf("%s", map[i]);
		}

		ans = move(0, 0, 1, 0);
		if (ans == 1) {
			printf("#%d YES\n", a + 1);
		}
		else {
			printf("#%d NO\n", a + 1);
		}
		memset(visit, 0, sizeof(visit));
	}
	return 0;
}
/*#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int T, R, C;
char map[23][23];
int check[23][23][4][16];
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
int memData;
bool dfs(int x, int y, int dir, int depth) {
	bool flag = false;
	//0~9인 숫자인 경우 memory값으로 저장
	if ('0' <= map[x][y] && map[x][y] <= '9')
		memData = map[x][y] - '0';
	//오른쪽 변경 혹은 0이면 오른쪽
	else if ((map[x][y] == '>') || ((map[x][y] == '_') && (memData == 0))) dir = 0;
	//왼쪽변경 혹은 0이 아니면 왼쪽
	else if (map[x][y] == '<' || map[x][y] == '_') dir = 1;
	//아래로 변경 혹은 0이면 아래
	else if ((map[x][y] == 'v') || ((map[x][y] == '|')&&(memData == 0))) dir = 2;
	//위로 변경혹은 0이 아니면 위로
	else if (map[x][y] == '^' || map[x][y] == '|') dir = 3;
	//덧셈
	else if (map[x][y] == '+')
		memData = (memData + 1) % 16;
	//뺄셈
	else if (map[x][y] == '-') {
		if (memData == 0)
			memData = 15;
		else
			memData -= 1;
	}
	//종료
	else if (map[x][y] == '@')
		return true;
	//방문 조건 확인
	if (!check[x][y][dir][memData])
		check[x][y][dir][memData] = 1;
	else
		return false;
	int nx = x + dx[dir];
	int ny = y + dy[dir];
	//맵에서 범위 넘어가면 방향 전환
	if (nx == -1)
		nx = R - 1;
	else if (nx == R)
		nx = 0;
	if (ny == -1)
		ny = C - 1;
	else if (ny == C)
		ny = 0;

	//무작위 랜덤
	if (map[x][y] == '?') {
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx == -1)
				nx = R - 1;
			else if (nx == R)
				nx = 0;
			if (ny == -1)
				ny = C - 1;
			else if (ny == C)
				ny = 0;
			flag = max(dfs(nx, ny, i, depth + 1), flag);
		}
		return flag;
	}
	else
		return max(dfs(nx, ny, dir, depth + 1), flag);
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		memset(map, 0, sizeof(map));
		memset(check, 0, sizeof(check));
		memData = 0;
		scanf("%d%d", &R, &C);
		for (int i = 0; i < R; i++) {
			scanf("%s", map[i]);
		}
		if (dfs(0, 0, 0, 0))
			printf("#%d YES\n", tc);
		else
			printf("#%d NO\n", tc);
	}
}*/