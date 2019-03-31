#include<cstdio>
#include<string>
using namespace std;
string str;
int cnt = 0;
int main() {
	int day;
	scanf("%d", &day);
	str = to_string(day);
	day = str.at(str.length()-1) - '0';
	for (int i = 0; i < 5; i++) {
		int a = 0;
		scanf("%d", &a);
		if (a == day)
			cnt++;
	}
	printf("%d\n", cnt);
}