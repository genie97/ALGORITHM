#include<cstdio>
#include<cmath>
using namespace std;
int map[31][31], visit[31][31];
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
int N;
double P[4];
double dfs(int x, int y, int count) {
	if (count == N)
		return 1;
	double easy = 0;
	visit[x][y] = 1;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (!visit[nx][ny]) {
			easy+=dfs(nx, ny, count+1)*P[i];
		}
	}
	visit[x][y] = 0;
	return easy;
}
int main() {
	scanf("%d", &N);
	for (int i = 0; i < 4; i++) { 
		scanf("%lf", &P[i]);
		P[i] /= 100;
	}
	double ans = dfs(15, 15, 0);
	printf("%.9lf",ans);
}