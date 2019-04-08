#include<iostream>
#include<cstdio>
#include<string>
#include<algorithm>
using namespace std;
string str[5];
int len=0;
int main() {
	for (int i = 0; i < 5; i++) {
		cin >> str[i];
		len = max(len, (int)str[i].length());
	}
	for (int i = 0; i < len; i++) {
		for (int j = 0; j < 5; j++) {
			if (str[j].length()<=i)
				continue;
			printf("%c", str[j][i]);
		}
	}
}
