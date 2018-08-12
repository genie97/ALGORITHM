#include<cstdio>
#include<algorithm>
using namespace std;
int home[200000];
int main() {
	int N, C, dis;
	scanf("%d %d", &N, &C);
	for (int i = 0; i < N; i++) {
		scanf("%d", &home[i]);
	}
	sort(home, home + N);
	int left = 1;
	int right = home[N - 1] - home[0];
	while (left <= right) {
		int cnt = 1;
		int mid = (left + right) / 2;
		int start = home[0];
		for (int i = 1; i < N; i++) {
			if (home[i] - start >= mid) {
				cnt++;
				start = home[i];
			}
		}
		if (cnt >= C) {
			dis = mid;
			left = mid + 1;
		}
		else
			right = mid - 1;
	}
	printf("%d\n", dis);
}
