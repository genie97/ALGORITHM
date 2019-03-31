#include<cstdio>
#include<string>
#include<iostream>
using namespace std;
int A, B, C;
string str;
long long result;
int cnt[10] = { 0, };
int main() {
	scanf("%d%d%d", &A, &B, &C);
	str = to_string(A*B*C);
	for (int i = 0; i < str.length(); i++) {
		int a = str.at(i) - '0';
		cnt[a]++;
	}
	for (int i = 0; i < 10; i++) {
		printf("%d\n", cnt[i]);
	}
}