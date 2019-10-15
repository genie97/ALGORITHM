#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

typedef struct SHARK {
	int s = 0, d = 0;
	long long z = 0;
	bool move = false;
}SHARK;

int R, C, M;
int shark_cnt = 0;
vector<SHARK> map[100][100];
int check[100][100] = { 0, };

/*void printMap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%d ", check[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}*/

bool comp(SHARK a, SHARK b) {
	if (a.z > b.z)
		return true;
	else
		return false;
}
void eat() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (check[i][j] <= 1) continue;
			sort(map[i][j].begin(), map[i][j].end(), comp);
			for (int k = 0; k < check[i][j] - 1; k++) {
				map[i][j].pop_back();
			}
			check[i][j] = 1;
		}
	}
}

void change_state() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (check[i][j] == 0) continue;
			map[i][j][0].move = false;
		}
	}
}
//�����̴� ����
//���� ��ġ���� �����뼱���� ���� �� �� �ִ� ĭ �� Ȯ��
//���� ĭ�� �� ���� �ӷ¿��� ����
//���� �ӷ��� index=0~index=R-1 Ȥ�� C-1������ ���̷� ������
//�������� ��, ���� Ȧ���� ������ �ٲ�� ��, ¦���� �״�� �����Ǵ� ��
//���� ����� ��ġ ���ϱ�

void move() {
	int shark_num = 0;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (check[i][j] == 0) continue;
			if (shark_cnt == shark_num) return;
			if (map[i][j][0].move == true) {
				shark_num++;
				continue;
			}

			int d = map[i][j][0].d; //����
			int s = map[i][j][0].s; //�ӷ�
			long long z = map[i][j][0].z;
			int rem_dis = 0;

			switch (d) {
			case 1:
				rem_dis = i;
				if (s <= rem_dis) {
					check[i][j] --; //�ش� ��ġ���� ��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i - s][j].push_back(SHARK{ s,d,z });
					check[i - s][j] ++;
					map[i - s][j][map[i - s][j].size() - 1].move = true;
				}
				else {
					int rem_speed = s - rem_dis;
					int even_check = rem_speed / (R-1);
					int idx = 0;
					if (even_check % 2 == 0) {
						d = 2;
						idx = (rem_speed % (R - 1));
					}
					else {
						idx = (R - 1) - (rem_speed % (R - 1));
					}
					check[i][j] --;//��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[idx][j].push_back(SHARK{ s,d,z });
					check[idx][j] ++;
					map[idx][j][map[idx][j].size() - 1].move = true;
				}
				break;
			case 2:
				rem_dis = R - 1 - i;
				if (s <= rem_dis) {
					check[i][j] --; //�ش� ��ġ���� ��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i + s][j].push_back(SHARK{ s,d,z });
					check[i + s][j] ++;
					map[i + s][j][map[i + s][j].size() - 1].move = true;
				}
				else {
					int rem_speed = s - rem_dis;
					int even_check = rem_speed / (R - 1);
					int idx = 0;
					if (even_check % 2 == 0) {
						d = 1;
						idx = R - 1 - (rem_speed % (R - 1));
					}
					else {
						idx = rem_speed % (R - 1);
					}
					check[i][j] --;//��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[idx][j].push_back(SHARK{ s,d,z });
					check[idx][j] ++;
					map[idx][j][map[idx][j].size() - 1].move = true;
				}
				break;
			case 3:
				rem_dis = C - 1 - j;
				if (s <= rem_dis) {
					check[i][j] --; //�ش� ��ġ���� ��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i][j + s].push_back(SHARK{ s,d,z });
					check[i][j + s] ++;
					map[i][j + s][map[i][j + s].size() - 1].move = true;
				}
				else {
					int rem_speed = s - rem_dis;
					int even_check = rem_speed / (C-1);
					int idx = 0;
					if (even_check % 2 == 0) {
						d = 4;
						idx = C - 1 - (rem_speed % (C-1));
					}
					else {
						idx = (rem_speed % (C - 1));
					}
					check[i][j] --;//��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i][idx].push_back(SHARK{ s,d,z });
					check[i][idx] ++;
					map[i][idx][map[i][idx].size() - 1].move = true;
				}
				break;
			case 4:
				rem_dis = j;
				if (s <= rem_dis) {
					check[i][j] --; //�ش� ��ġ���� ��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i][j - s].push_back(SHARK{ s,d,z });
					check[i][j - s]++;
					map[i][j - s][map[i][j - s].size() - 1].move = true;
				}
				else {
					int rem_speed = s - rem_dis;
					int even_check = rem_speed / (C-1);
					int idx = 0;
					if (even_check % 2 == 0) {
						d = 3;
						idx = rem_speed % (C - 1);
					}
					else {
						idx = C - 1 - (rem_speed % (C - 1));
					}
					check[i][j] --;//��� ���ֱ�
					map[i][j].erase(map[i][j].begin() + 0);
					map[i][idx].push_back(SHARK{ s,d,z });
					check[i][idx]++;
					map[i][idx][map[i][idx].size() - 1].move = true;
				}
				break;
			}
		}
	}
}
int main() {
	int res = 0;
	scanf("%d%d%d", &R, &C, &M);
	for (int i = 0; i < M; i++) {
		int r, c, s, d;
		long long z;
		scanf("%d%d%d%d%lld", &r, &c, &s, &d, &z);
		map[r - 1][c - 1].push_back(SHARK{ s,d,z });
		check[r - 1][c - 1] = 1;
	}
	shark_cnt = M;
	//���ò��� ���η� ������!
	for (int i = 0; i < C; i++) {
		if (shark_cnt == 0) break; //�� �ƿ� �� ��� ���
		//�� ��´�
		for (int j = 0; j < R; j++) {
			if (check[j][i] == 1) {
				check[j][i] = 0;
				res += map[j][i][0].z;
				map[j][i].pop_back(); //������ ���Ϳ��� ���ֱ�
				shark_cnt--;
				break;
			}
		}
		//�������� �� �� �����δ�
		move();
		//printMap();

		//���� ��ġ�� �ִ� ������ �Դ´�
		eat();
		//printMap();
		
		//�ٵ� �̵����¸� �ʱ�ȭ�Ѵ�
		change_state();
	}
	printf("%d", res);
}