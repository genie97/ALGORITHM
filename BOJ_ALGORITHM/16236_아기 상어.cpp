#include<cstdio>
#include<vector>
#include<cstring>
#include<queue>
#include<algorithm>
using namespace std;
//상어의 정보: 위치, 크기, 먹은 물고기 수, 이동시간
typedef struct SHARK {
	int x, y, z, fish=0, time = 0; 
}SHARK;
int map[21][21], visit[21][21] = { 0, };
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
int N;
SHARK shark;
vector<SHARK> vt;
bool comp(SHARK a, SHARK b) {
	if (a.z <= b.z) {
		if (a.z == b.z) {  //크기가 같다면 이동시간이 작은 물고기
			if (a.time <= b.time) { 
				if (a.time == b.time) { //이동시간이 같다면 x축이 작은 물고기 (가장 위쪽)
					if (a.x <= b.x) {
						if (a.x == b.x) { //x축이 같다면 y축이 작은 물고기 (가장 왼쪽)
							return a.y < b.y;
						}
						return a.x < b.x;
					}
				}
				return a.time < b.time;
			}
		}
		return a.z < b.z;
	}
	return a.z > b.z;
}
int main() {
	scanf("%d", &N);
	queue<SHARK> q;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 9) { //상어 위치를 shark변수에 저장
				shark = { SHARK{i,j,2,0,0} };
				map[i][j] = 0; //map은 0으로 초기화
			}
		}
	}
	int res = 0;
	while (1) {
		vt.clear();
		memset(visit, 0, sizeof(visit));
		q.push(shark);
		visit[shark.x][shark.y] = 1;

		while (!q.empty()) {
			int x = q.front().x;
			int y = q.front().y;
			int z = q.front().z;
			int fish = q.front().fish;
			int time = q.front().time;
			q.pop();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (!visit[nx][ny]) {
					if (map[nx][ny] == 0 || map[nx][ny] == z) { //상어랑 크기가 같거나, 0이면 그냥 지나갈 수 있음
						visit[nx][ny] = 1;
						q.push({ nx,ny,z,fish,time + 1 });
					} 
					else if (map[nx][ny] < z) { //크기가 작으면 상어가 먹을 수 있음
						visit[nx][ny] = 1;
						vt.push_back({ nx,ny,z,fish + 1,time + 1 });
					}
				}
			}
		}
		if (vt.size() == 0) break;
		sort(vt.begin(), vt.end(), comp); //제일 먼저 먹을 수 있는 물고기를 구하기 위해 sort
		if (vt[0].z == vt[0].fish) { //z와 먹은 fish의 수가 같다면 z가 증가함
			vt[0].z++;
			vt[0].fish = 0; //다시 원상태로 돌려주기
		}
		map[vt[0].x][vt[0].y] = 0;
		res += vt[0].time; //가지고있는 시간 더하기
		shark = { vt[0].x,vt[0].y,vt[0].z,vt[0].fish,0 }; //time도 다시 0으로 초기화(새로운 위치에서 재시작!)
	}
	printf("%d", res);
}
