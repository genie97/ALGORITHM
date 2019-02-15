#include<cstdio>
#include<cstring>
#include<queue>
using namespace std;

priority_queue<pair<int, pair<int, int>>> pq; //first: weight second.first : xรเ second.second:yรเ 

int N, M;
int maze[101][101];
int weight[101][101];
int moveX[4] = { 0,0,-1,1 };
int moveY[4] = { -1,1,0,0 };

int main() {
	scanf("%d %d", &N, &M);
	memset(weight, -1, sizeof(weight));
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &maze[i][j]);
		}
	}
	pq.push({ 0,{ 0,0 } });

	while (!pq.empty()) {
		int x = pq.top().second.first;
		int y = pq.top().second.second;
		int w = -pq.top().first;
		pq.pop();
		if (weight[x][y] != -1)
			continue;

		weight[x][y] = w;

		for (int i = 0; i < 4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
				if (weight[nextX][nextY] == -1) {
					if (maze[nextX][nextY] == 1) {
						pq.push({ -(w + 1),{ nextX,nextY } });
					}
					else
						pq.push({ -w,{ nextX,nextY } });
				}
			}
		}
	}
	printf("%d\n", weight[M - 1][N - 1]);
}