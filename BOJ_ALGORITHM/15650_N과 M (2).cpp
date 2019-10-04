#include<cstdio>
#include<vector>
using namespace std;
int N, M;
vector<int> vt;
int num[9] = { 0, };
void make_comb(int idx, int cnt) {
	if (cnt == M) {
		for (int i = 0; i < vt.size(); i++) {
			printf("%d ", vt[i]);
		}
		printf("\n");
		return;
	}
	for (int i = idx; i <= N; i++) {
		if (num[i] == 1) continue;
		vt.push_back(i);
		num[i] = 1;
		make_comb(i, cnt + 1);
		num[i] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d %d", &N, &M);
	make_comb(1, 0);
}