#include<iostream>
#include<string>
using namespace std;
int flag = 1;
int main() {
	int N, alph[27] = {0,};
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		alph[s[0] - 'a']++;
	}
	for (int i = 0; i < 26; i++) {
		if (alph[i] >= 5) {
			cout << (char)(i + 'a');
			flag = 0;
		}
	}
	if(flag)
		cout << "PREDAJA";
	cout << endl;
}