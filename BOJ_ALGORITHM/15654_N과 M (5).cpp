#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;
int num[10001], in[8];
vector<int> vt;
int N, M;
void make_perm(int cnt){
	if (cnt == M) {
		for (int i = 0; i < vt.size(); i++) {
			printf("%d ", vt[i]);
		}
		printf("\n");
	}
	for (int i = 0; i < N; i++) {
		if (num[in[i]] == 1)continue;
		num[in[i]] = 1;
		vt.push_back(in[i]);
		make_perm(cnt + 1);
		num[in[i]] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		scanf("%d", &in[i]);
	}
	sort(in, in + N);
	make_perm(0);
}