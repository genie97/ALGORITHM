#include<cstdio>
#include<stack>
#include<algorithm>
#include<utility>
using namespace std;

#define row 12
#define col 6

typedef pair <int, int> p;
stack <p> s;
char board[row][col];
bool visited[row][col];

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };


void arrange() {
	for (int i = 0; i < col; i++) {
		for (int j = row - 2; j >= 0; j--) {
			for (int k = 11; k > j; k--) {
				if (board[j][i] != '.' && board[k][i] == '.') {
					swap(board[k][i], board[j][i]);
					break;
				}
			}
		}
	}
}

void dfs(int y, int x, char color) {
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (0 <= ny || ny < row || 0 <= nx || nx <= col) {
			if (!visited[ny][nx] && board[ny][nx] == color) {
				s.push({ ny,nx });
				visited[ny][nx] = true;
				dfs(ny, nx, color);
			}
		}
	}
}
int main() {
	for (int i = 0; i < row; i++) {
		scanf("%s", &board[i]);
	}

	int comb = 0;
	
	while (1) {
		bool flag = false;
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visited[i][j] && board[i][j] != '.') {
					dfs(i, j, board[i][j]);
					if (s.size() >= 4) {
						flag = true;
						
						while (!s.empty()) {
							p top;
							top = s.top();
							board[top.first][top.second] = '.';
							s.pop();
						}
					}
				}
			}
		}
		if (!flag)
			break;
		else
			comb++;
		arrange();
	}
	printf("%d\n", comb);
}
