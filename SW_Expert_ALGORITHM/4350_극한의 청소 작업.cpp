#include<cstdio>
#include<algorithm>
#include<cstring>
using namespace std;
int T;
long long convert(char *str, int len, int sign) {
	long long start = sign ? 0 : 1;
	long long ret = 0;
	long long j = 1;
	for (int i = len - 1; i >= start; i--) {
		if (str[i] >= '4') str[i]--;
		ret += (str[i] - '0') * j;
		j *= 9;
	}
	return ret;
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		long long A, B;
		char tempA[15], tempB[15]; //string으로 A,B 임시변수
		int signA = 1, signB = 1; //부호
		scanf("%s %s", tempA, tempB);
		int lenA = strlen(tempA);
		int lenB = strlen(tempB);
		if (tempA[0] == '-')
			signA = 0;
		if (tempB[0] == '-')
			signB = 0;
		A = convert(tempA, lenA, signA);
		B = convert(tempB, lenB, signB);
		long long ans;
		if (signA == signB && A>B) ans = A - B;
		else if (signA != signB) ans = A + B - 1;
		else ans = B - A;
		printf("#%d %lld\n", tc, ans);
	}
}