#include<cstdio>
#include<vector>
using namespace std;
int M, N;
vector<int> vt;
int num[9] = { 0, };
void make_perm(int cnt) {
	if (cnt == M) {
		for (int i = 0; i<vt.size(); i++) {
			printf("%d ", vt[i]);
		}
		printf("\n");
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (num[i] > M)continue;
		num[i]++;
		vt.push_back(i);
		make_perm(cnt + 1);
		vt.pop_back();
		num[i]--;
	}
}
int main() {
	scanf("%d%d", &N, &M);
	make_perm(0);
}