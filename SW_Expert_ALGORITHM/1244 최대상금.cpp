#include<cstdio>
#include<algorithm>
#include<string>
using namespace std;
int C, N, res;
string num;
void solve(int cur, int cnt) {
	if (cnt == N) {
		res = max(res, stoi(num));
		return;
	}
	for (int i = cur; i < num.length(); i++) {
		for (int j = i + 1; j < num.length(); j++) {
				swap(num[i], num[j]);
				solve(i, cnt + 1);
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
		solve(0, 0);
		printf("#%d %d\n", tc, res);
	}
}