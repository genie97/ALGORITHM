#include<iostream>
#include<string>
#include<vector>
#include<cstring>

using namespace std;

int N, K, ans = 0;
bool alp[26];
vector<string> vt;

int max(int a, int b) {
	if (a > b)
		return a;
	return b;
}
int readWord() {
	bool check;
	int cnt = 0;
	for (int i = 0; i < vt.size(); i++) {
		check = true;
		string str = vt[i];
		for (int j = 0; j < str.length(); j++) {
			if (alp[str[j] - 'a'] == false) {
				check = false;
				break;
			}
		}
		if (check == true)
			cnt++;
	}
	return cnt;
}
void dfs(int idx, int cnt) {
	if (cnt == K) {
		ans = max(ans, readWord());
		return;
	}
	for (int i = idx; i < 26; i++) {
		if (alp[i] == true)
			continue;
		alp[i] = true;
		dfs(i, cnt + 1);
		alp[i] = false;
	}
}

int main() {
	/* 12ms정도 차이남 */
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		vt.push_back(s);
	}

	if (K < 5) {
		cout << 0 << "\n";
		exit(0); //exit(1) 런타임에러 발생함
	}

	alp['a' - 'a'] = true;
	alp['n' - 'a'] = true;
	alp['t' - 'a'] = true;
	alp['i' - 'a'] = true;
	alp['c' - 'a'] = true;
	K = K - 5;
	dfs(0, 0);
	cout << ans << "\n";
}