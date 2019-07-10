#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int dx[2] = { 2, 0 }, dy[2] = { 0,2 };
int visit[1005][1005] = { 0, };
int total, N, M;

int main() {
	int T, cnt = 0;
	scanf("%d", &T);
	while (T--) {
		cnt++;
		scanf("%d%d", &N, &M);
		total = N*M;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					for (int k = 0; k < 2; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx >= 0 && nx < N&&ny >= 0 && ny < M&&visit[nx][ny] == 0) {
							total--;
							visit[nx][ny] = 1;
						}
					}
				}
			}
		}
		printf("#%d %d\n", cnt, total);
		memset(visit, 0, sizeof(visit));
	}
}