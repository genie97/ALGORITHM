#include<cstdio>
#include<string>
#include<set>
#include<algorithm>
#include<vector>
using namespace std;
int num[8], in[8];
vector<int> vt;
set<string> visit;
int N, M;
int duplication() {
	string temp = "";
	for (int i = 0; i < vt.size(); i++) {
		char A = vt[i] + '0';
		temp = temp + A;
	}
	if (visit.find(temp) == visit.end()) {
		visit.insert(temp);
		return 0;
	}
	else
		return 1;
}
void make_comb(int idx, int cnt) {
	if (cnt == M) {
		if (!duplication()) {
			for (int i = 0; i < vt.size(); i++) {
				printf("%d ", vt[i]);
			}
			printf("\n");
		}
		return;
	}
	for (int i = idx; i < N; i++) {
		if (num[i] == 1) continue;
		num[i] = 1;
		vt.push_back(in[i]);
		make_comb(i, cnt + 1);
		num[i] = 0;
		vt.pop_back();
	}
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		scanf("%d", &in[i]);
	}
	sort(in, in + N);
	make_comb(0, 0);
}