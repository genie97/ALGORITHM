#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;
int T, N;
string testN;
string num[10] = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };
int main() {
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> testN;
		cin >> N;
		vector<int> vt;
		string sen;
		for (int i = 0; i < N; i++) {
			cin >> sen;
			for (int i = 0; i < 10; i++) {
				if (sen == num[i]) {
					vt.push_back(i);
					break;
				}
			}
		}
		sort(vt.begin(), vt.end());
		cout << "#" << tc << endl;
		for (int i = 0; i < vt.size(); i++) {
			cout << num[vt[i]] << " ";
		}
		cout << endl;
	}
}