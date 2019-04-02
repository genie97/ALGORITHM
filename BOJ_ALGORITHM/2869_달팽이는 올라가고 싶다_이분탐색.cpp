#include<cstdio>
#include<algorithm>
using namespace std;
long long A, B, V;
int main() {
	scanf("%lld %lld %lld", &A, &B, &V);
	long long day = 1000000001;
	long long left = 0;
	long long right = V;
	while (left <= right) {
		long long mid = (left + right) / 2;
		if (V <= mid*(A - B) + A) {
			if (day > mid + 1)
				day = mid + 1;
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	printf("%lld\n", day);
}
