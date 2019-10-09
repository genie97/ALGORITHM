#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;

int N, M, K;
int map[55][55],res[55][55];
int MIN = 987654321;
int cmd[6];
vector<int> vt;

int rotate_cmd[6][3];
void map_copy() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			res[i][j] = map[i][j];
		}
	}
}
void copy(int r, int c, int s, int arr[55][55]) {
	for (int i = r - s; i <= r + s; i++) {
		for (int j = c - s; j <= c + s; j++) {
			res[i][j] = arr[i][j];
		}
	}
}
void rotate_array(int r, int c, int s) {
	int temp[55][55] = { 0, };
	int line = 0;
	for (int line = 0; line < s; line++) {
		for (int i = r - s + line; i <= r + s - line; i++) {
			for (int j = c - s + line; j <= c + s - line; j++) {
				if (i != r - s + line && j != c - s + line && i != r + s - line && j != c + s - line)
					continue;
				if (i == r - s + line) {
					if (j == c - s + line)
						temp[i][j] = res[i + 1][j];
					else
						temp[i][j] = res[i][j - 1];
				}
				else if (i == r + s - line) {
					if (j == c + s - line)
						temp[i][j] = res[i - 1][j];
					else
						temp[i][j] = res[i][j + 1];
				}
				else {
					if (j == c - s + line)
						temp[i][j] = res[i + 1][j];
					else if (j == c + s - line)
						temp[i][j] = res[i - 1][j];
				}
			}
		}
	}
	temp[r][c] = res[r][c];
	copy(r, c, s, temp);
}
void find_Min() {
	for (int i = 1; i <= N; i++) {
		int sum = 0;
		for (int j = 1; j <= M; j++) {
			sum += res[i][j];
		}
		MIN = min(MIN, sum);
	}
}
void make_perm(int cnt) {
	if (cnt == K) {
		map_copy();
		for (int i = 0; i < vt.size(); i++) {
			int r = rotate_cmd[vt[i]][0];
			int c = rotate_cmd[vt[i]][1];
			int s = rotate_cmd[vt[i]][2];
			rotate_array(r, c, s);
		}
		find_Min();
	}
	for (int i = 0; i < K; i++) {
		if (cmd[i] == 1) continue;
		cmd[i] = 1;
		vt.push_back(i);
		make_perm(cnt + 1);
		cmd[i] = 0;
		vt.pop_back();
	}	
}
int main() {
	scanf("%d%d%d", &N, &M, &K);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < K; i++) {
		scanf("%d%d%d", &rotate_cmd[i][0], &rotate_cmd[i][1], &rotate_cmd[i][2]);
	}
	make_perm(0);
	printf("%d", MIN);
}