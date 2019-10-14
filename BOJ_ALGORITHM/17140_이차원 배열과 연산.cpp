#include<cstdio>
#include<vector>
#include<algorithm>
#include<cstring>

using namespace std;
typedef struct array_cal {
	int num = 0;
	int cnt = 0;
}ARRAY;

int A[101][101] = { 0, }, temp_map[101][101] = { 0, };
int row = 3, col = 3;
int r, c, k;

bool comp(ARRAY &a, ARRAY &b) {
	if (a.cnt < b.cnt)
		return true;
	else if (a.cnt == b.cnt)
		return a.num < b.num;
	else
		return false;
}
void copy_map(int(*a)[101], int(*b)[101]) {
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			a[i][j] = b[i][j];
		}
	}
}
void R() {
	int row_size = 0;
	for (int i = 0; i < row; i++) {
		ARRAY check[101];

		for (int j = 0; j < col; j++) {
			if (A[i][j] == 0) continue;
			check[A[i][j]].num = A[i][j];
			check[A[i][j]].cnt++;
		}
		vector<ARRAY> temp;

		for (int k = 0; k < 101; k++) {
			if (check[k].cnt == 0) continue;
			temp.push_back(check[k]);
		}
		sort(temp.begin(), temp.end(), comp);
		row_size = max(row_size, (int)(temp.size()) * 2);
		int size = 0;
		for (int l = 0; l < temp.size() * 2; l = l + 2) {
			temp_map[i][l] = temp[size].num;
			temp_map[i][l + 1] = temp[size].cnt;
			size++;
		}
	}
	col = row_size;
	memset(A, 0, sizeof(A));
	copy_map(A, temp_map);
	memset(temp_map, 0, sizeof(temp_map));
}
void C() {
	int col_size = 0;
	for (int i = 0; i < col; i++) {
		ARRAY check[101];

		for (int j = 0; j < row; j++) {
			if (A[j][i] == 0) continue;
			check[A[j][i]].num = A[j][i];
			check[A[j][i]].cnt++;
		}
		vector<ARRAY> temp;

		for (int k = 0; k < 101; k++) {
			if (check[k].cnt == 0) continue;
			temp.push_back(check[k]);
		}
		sort(temp.begin(), temp.end(), comp);
		col_size = max(col_size, (int)(temp.size()) * 2);
		int size = 0;
		for (int l = 0; l < temp.size() * 2; l = l + 2) {
			temp_map[l][i] = temp[size].num;
			temp_map[l + 1][i] = temp[size].cnt;
			size++;
		}
	}
	row = col_size;
	memset(A, 0, sizeof(A));
	copy_map(A, temp_map);
	memset(temp_map, 0, sizeof(temp_map));
}

int main() {
	int time = 0;
	scanf("%d%d%d", &r, &c, &k);
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			scanf("%d", &A[i][j]);
		}
	}
	while (A[r-1][c-1] != k) {
		if (time > 100) {
			printf("-1");
			return 0;
		}
		else if (row >= col) R();
		else if (row < col)C();
		time++;
	}
	printf("%d", time);
}