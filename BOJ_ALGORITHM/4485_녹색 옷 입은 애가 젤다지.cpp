#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#include<functional>
#define INF 1000000000
using namespace std;
int N, k;
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
typedef pair<int, pair<int, int>> pr;

int main() {
	int cnt = 0;
	while (1) {
		scanf("%d", &N);
		if (N == 0)
			break;
		int map[130][130], dis[130][130];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%d", &map[i][j]);
				dis[i][j] = INF;
			}
		}
		dis[0][0] = map[0][0];
		priority_queue<pr, vector<pr>, greater<pr>>pq; //min-heap으로 바꿔주는 게 중요!
		pq.push({ map[0][0],{ 0, 0 } });
		while (!pq.empty()) {
			int cx = pq.top().second.first;
			int cy = pq.top().second.second;
			pq.pop();
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx >= 0 && nx < N&&ny >= 0 && ny < N) {
					if (dis[nx][ny] > dis[cx][cy] + map[nx][ny]) {
						dis[nx][ny] = dis[cx][cy] + map[nx][ny];
						pq.push({ map[nx][ny],{ nx, ny } });
					}
				}
			}
		}
		printf("Problem %d: %d\n", ++cnt, dis[N-1][N-1]);
	}
}