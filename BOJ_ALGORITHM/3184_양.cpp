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
// . �ʵ� # ��Ÿ�� o�� v����
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
	//bfs ���� �ѹ� �� ������ ���� ������ �Ǵ�
	if (curS > curW)
		v_num -= curW;
	else
		o_num -= curS;
}

int main() {
	scanf("%d%d", &R, &C);
	for (int i = 0; i < R; i++) {
		cin >> str[i];
		//���߿� ������̸� ���� �̸� �� ����α�
		for (int j = 0; j < C; j++) {
			if (str[i][j] == 'v')
				v_num++;
			else if (str[i][j] == 'o')
				o_num++;
		}
	}
	//bfs�ϴ� ��ġ�� �� Ȥ�� ����� �����������ϱ� ���ʿ��� ���� ���̱�
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if ((str[i][j] == 'v' || str[i][j] == 'o') && !visit[i][j])
				bfs(i, j);
		}
	}
	printf("%d %d\n", o_num, v_num);
}