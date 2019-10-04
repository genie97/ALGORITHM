#include<cstdio>
#include<cmath>
using namespace std;
int main() {
	int T;
	scanf("%d", &T);
	for (int i = 1; i <= T; i++)
	{
		long long x, y;
		long long n = 1;
		scanf("%lld %lld", &x, &y);
		while (n*n <= (y - x)) {
			n++;
		}
		n--;
		long long rem = (y - x) - (n*n);
		rem = (long long)ceil((double)rem / (double)n);
		printf("%d\n", 2 * n - 1 + rem);
	}
}