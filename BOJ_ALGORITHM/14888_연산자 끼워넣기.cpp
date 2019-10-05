#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;
int N, in[12], arith[4], num[101];
int MAX = -987654321, MIN = 987654321;
vector<int> vt;
vector<int> cal;
int calculation() {
	int i = 0;
	int res = in[0];
	for (int j = 0; j < cal.size(); j++) {
		switch (cal[j]) {
		case 0:
			res = res + in[++i];
			break;
		case 1:
			res = res - in[++i];
			break;
		case 2:
			res = res * in[++i];
			break;
		case 3:
			res = res / in[++i];
			break;
		}
	}
	return res;
}
void make_arith(int cnt) {
	if (cnt == N - 1) {
		int res = calculation();
		MAX = max(MAX, res);
		MIN = min(MIN, res);
		return;
	}
	for (int i = 0; i < 4; i++) {
		if (arith[i] == 0) continue;
		arith[i]--;
		cal.push_back(i);
		make_arith(cnt + 1);
		arith[i]++;
		cal.pop_back();
	}
}
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &in[i]);
	}
	for (int i = 0; i < 4; i++) {
		scanf("%d", &arith[i]);
	}
	make_arith(0);
	printf("%d\n%d\n", MAX, MIN);
}