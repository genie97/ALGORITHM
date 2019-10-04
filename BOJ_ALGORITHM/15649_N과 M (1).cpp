#include<cstdio>
#include<vector>
using namespace std;
int num[9] = {0,};
int N, M;
vector<int> vt;
void make_perm(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < vt.size(); i++) {
			printf("%d ", vt[i]);
		}
		printf("\n");
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (num[i] == 1)continue;
		vt.push_back(i);
		num[i] = 1;
		make_perm(cnt + 1);
		num[i] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d %d", &N, &M);
	make_perm(0);
}
