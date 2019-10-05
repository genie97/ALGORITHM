#include<cstdio>
#include<algorithm>
#include<vector>
#include<string>
#include<set>
using namespace std;
int N, M;
int num[8], in[8];
vector<int> vt;
set<string> visit;

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
void make_perm(int cnt) {
	if (cnt == M) {
		if (!duplication()) {
			for (int i = 0; i < vt.size(); i++) {
				printf("%d ", vt[i]);
			}
			printf("\n");
		}
		return;
	}
	for (int i = 0; i < N; i++) {
		if (num[i] > M)continue;
		num[i]++;
		vt.push_back(in[i]);
		make_perm(cnt + 1);
		num[i]--;
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