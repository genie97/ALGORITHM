#include<cstdio>
#include<cstring>
using namespace std;
const int U = 0, D = 1, L = 2, R = 3;
int T, H, W, N, x, y;
char map[23][23], move[101];
void init() {
	memset(map, 0, sizeof(map));
	memset(move, 0, sizeof(move));
	x = 0, y = 0;
}
void up() {
	if (x - 1 >= 0 && map[x - 1][y] == '.') {
		map[x][y] = '.';
		x = x - 1;
	}
	map[x][y] = '^';
}
void down() {
	if (x + 1 >= 0 && map[x + 1][y] == '.') {
		map[x][y] = '.';
		x = x + 1;
	}
	map[x][y] = 'v';
}
void left() {
	if (y - 1 >= 0 && map[x][y - 1] == '.') {
		map[x][y] = '.';
		y = y - 1;
	}
	map[x][y] = '<';
}
void right() {
	if (y + 1 >= 0 && map[x][y + 1] == '.') {
		map[x][y] = '.';
		y = y + 1;
	}
	map[x][y] = '>';
}
void shoot() {
	switch (map[x][y]) {
	case '^':
		for (int i = x - 1; i >= 0; i--) {
			if (map[i][y] == '#')
				break;
			else if (map[i][y] == '*') {
				map[i][y] = '.';
				break;
			}
		}
		break;
	case 'v':
		for (int i = x + 1; i < H; i++) {
			if (map[i][y] == '#')
				break;
			else if (map[i][y] == '*') {
				map[i][y] = '.';
				break;
			}
		}
		break;
	case '<':
		for (int i = y - 1; i >= 0; i--) {
			if (map[x][i] == '#')
				break;
			else if (map[x][i] == '*') {
				map[x][i] = '.';
				break;
			}
		}
		break;
	case '>':
		for (int i = y + 1; i <W; i++) {
			if (map[x][i] == '#')
				break;
			else if (map[x][i] == '*') {
				map[x][i] = '.';
				break;
			}
		}
		break;
	}
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &H, &W);
		init();
		for (int i = 0; i < H; i++)
			scanf("%s", map[i]);
		scanf("%d", &N);
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
					x = i, y = j;
					break;
				}
			}
		}
		scanf("%s", move);
		for (int i = 0; i < N; i++) {
			switch (move[i]) {
			case 'U':
				up();
				break;
			case 'D':
				down();
				break;
			case 'L':
				left();
				break;
			case 'R':
				right();
				break;
			case 'S':
				shoot();
				break;
			}
		}
		printf("#%d ", tc);
		for (int i = 0; i < H; i++) {
			printf("%s\n", map[i]);
		}
	}
}