#include<cstdio>
#include<cstring>
#include<string>
using namespace std;
long long digitNum[10] = { 0,9,90,900, 9000,90000,900000,9000000,90000000,900000000 };
long long digitMax[10] = { 0,9,99,999,9999,99999,999999,9999999,99999999,999999999 };
int main() {
	long long N;
	scanf("%lld", &N);
	int len = to_string(N).length();
	long long ans = 0;
	for (int i = 1; i < len; i++)
		ans += i * digitNum[i];
	
	ans += (len * (N - digitMax[len - 1]));
	printf("%lld", ans);
}