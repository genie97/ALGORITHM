#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
using namespace std;
int C, N, res;
string num;
bool check[1000000][30];
void solve(int cur, int cnt) {
	if (cnt == N) {
		res = max(res, stoi(num));
		return;
	}
	for (int i = cur; i < num.length(); i++) {
		for (int j = i + 1; j < num.length(); j++) {
			if (i == j) continue;
			swap(num[i], num[j]);
			if (!check[stoi(num)][cnt + 1]) {
				check[stoi(num)][cnt + 1] = true;
				solve(i, cnt + 1);
			}
			swap(num[i], num[j]);
		}
	}
}
int main() {
	scanf("%d", &C);
	for (int tc = 1; tc <= C; tc++) {
		char in[6];
		scanf("%s %d", in, &N);
		num = in;
		res = 0;
		memset(check, 0, sizeof(check));
		solve(0, 0);
		printf("#%d %d\n", tc, res);
	}
}
