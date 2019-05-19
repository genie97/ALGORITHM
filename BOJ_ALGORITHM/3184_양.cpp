#include<iostream>
#include<string>
#include<vector>
#include<queue>
#include<cstring>
using namespace std;
string str[251];
int visit[251][251];
int R, C;
queue <pair<int, int>> q;
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
// . 필드 # 울타리 o양 v늑대
int o_num = 0, v_num = 0, area = 0;
void bfs(int i, int j) {
	q.push({ i,j });
	visit[i][j] = 1;
	int curS = 0, curW = 0;
	if (str[i][j] == 'v')
		curW++;
	else if (str[i][j] == 'o')
		curS++;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			if (!visit[nx][ny] && str[nx][ny] != '#') {
				if (str[nx][ny] == 'v')
					curW++;
				else if (str[nx][ny] == 'o')
					curS++;
				visit[nx][ny] = 1;
				q.push({ nx, ny });
			}
		}
	}
	//bfs 실행 한번 당 영역에 누가 많은지 판단
	if (curS > curW) //양이 많을 때
		v_num -= curW;
	else //늑대가 많을 
		o_num -= curS;
}

int main() {
	scanf("%d%d", &R, &C);
	for (int i = 0; i < R; i++) {
		cin >> str[i];
		//계산 편이를 위해 미리 수 세어두기
		for (int j = 0; j < C; j++) {
			if (str[i][j] == 'v')
				v_num++;
			else if (str[i][j] == 'o')
				o_num++;
		}
	}
	//bfs하는 위치가 양 혹은 늑대로 정해져있으니까 불필요한 연산 줄이기
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if ((str[i][j] == 'v' || str[i][j] == 'o') && !visit[i][j])
				bfs(i, j);
		}
	}
	printf("%d %d\n", o_num, v_num);
}
