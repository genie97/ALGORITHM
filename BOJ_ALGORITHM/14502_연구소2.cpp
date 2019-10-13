#include<cstdio>
#include<vector>
#include<queue>
#include<cstring>
#include<algorithm>
using namespace std;

typedef struct Pos {
	int x, y;
}Pos;

vector<Pos> vt; //���տ� ����
vector<Pos> virus; //���̷��� ���� ��ġ ���� ��

int check[11]; //���տ� üũ
int map[51][51]; //���� ��
int temp[51][51]; //���� ��=>���̷��� ���� ��
int N, M; //N�� ��ũ��, M�� ���̷��� ����
int res = 987654321; //�����
int space = 0; // 0�ΰ��� ����
int flag = 0; //���̷����� ��ü ���۶߸��� ��� 

//�����̴� ����
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

void copy_map() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 2)
				temp[i][j] = 0;
			else
				temp[i][j] = map[i][j];
		}
	}
}

int bfs() {
	queue<Pos> q;
	int virus_num = M;
	int spread_num = 0;
	int spread_time = 0; //���̷��� ������ �ð�

	for (int i = 0; i < N; i++) {
		if (virus_num == 0) break;
		for (int j = 0; j < N; j++) {
			if (temp[i][j] == 2) {
				q.push(Pos{ i,j });
				virus_num--;
			}
		}
	}
	while (!q.empty()) {
		int size = q.size();
		if (spread_num == (space + virus.size()-M)) 
			return spread_time;

		for (int i = 0; i < size; i++) {
			int x = q.front().x;
			int y = q.front().y;
			q.pop();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (temp[nx][ny] == 0) {
					temp[nx][ny] = 2;
					q.push(Pos{ nx, ny });
					spread_num++;
				}
			}
		}
		spread_time++;
	}
	return 987654321;
}

void make_comb(int idx, int cnt) {
	if (cnt == M) {
		copy_map(); //���� �ʿ� ���� �� �Ҵ����ֱ�
		//���̷��� �ɱ�
		for (int i = 0; i < vt.size(); i++) {
			int x = vt[i].x;
			int y = vt[i].y;
			temp[x][y] = 2;
		}
		int spread_ans= 0; //ó�� 0���� �ʱ�ȭ�ؼ� ����
		//�ּҰ� ���ϱ�
		spread_ans = bfs();
		res = min(res, spread_ans);
	
		return;
	}
	for (int i = idx; i < virus.size(); i++) {
		if (check[i] == 1) continue;
		check[i] = 1;
		vt.push_back(virus[i]);
		make_comb(i, cnt + 1);
		check[i] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d%d", &N, &M);
	//�ʱ�ȭ
	memset(map, 0, sizeof(map));
	memset(temp, 0, sizeof(temp));
	res = 987654321;
	space = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 2) {
				virus.push_back(Pos{ i,j });
			}
			if (map[i][j] == 0)
				space++; //�� �����
		}
	}
	make_comb(0, 0);
	printf("%d", (res==987654321)?-1:res);
}
