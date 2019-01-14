#include<cstdio>
#include<algorithm>
using namespace std;
int N, M;
int T[100001];

int main() {
	scanf("%d %d", &N, &M);
	int max = 0;
	for (int i = 1; i <= N; i++) {
		scanf("%d", &T[i]);
		if (max < T[i])
			max = T[i];
	}

	int left = 1, right = max * M, res = max * M;
	while (left <= right) {
		int mid = (left + right) / 2;
		int num = 0;
		for (int i = 1; i <= N; i++) {
			num += mid / T[i];
		}
		if (num < M) {
			left = mid + 1;
		}
		else {
			if (res > mid)
				res = mid;
			right = mid - 1;
		}
	}
	printf("%d\n", res);
}
