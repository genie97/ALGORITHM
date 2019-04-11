#include<cstdio>
#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
string l[500000], s[500000], ans[500000];
int N, M, cnt = 0;
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		cin >> l[i];
	}
	for (int i = 0; i < M; i++) {
		cin >> s[i];
	}
	sort(l, l + N);
	sort(s, s + M);
	for (int i = 0; i < N; i++) {
		if (binary_search(s, s + M, l[i])) {
			ans[cnt] = l[i];
			cnt++;
		}
	}
	printf("%d\n", cnt);
	for (int i = 0; i < cnt; i++) {
		cout << ans[i] <<endl;
	}
}