#include<cstdio>
#include<vector>
using namespace std;
int N, M;
int num[9] = { 0, };
vector<int> vt;
void make_comb(int idx, int cnt) {
	if (cnt == M) {
		for (int i = 0; i < vt.size(); i++) {
			printf("%d ", vt[i]);
		}
		printf("\n");
		return;
	}
	for (int i = idx; i <= N; i++) {
		if (num[i] > M) continue;
		num[i]++;
		vt.push_back(i);
		make_comb(i, cnt+1);
		num[i]--;
		vt.pop_back();
	}
}
int main() {
	scanf("%d%d", &N, &M);
	make_comb(1, 0);
}