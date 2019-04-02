#include<cstdio>
#include<algorithm>
using namespace std;
int INF = 987654321;
int N, M, jew[300002];
int ok(int mid) {
	int sum = 0;
	for (int i = 1; i<=M;	i++) {
		sum += (jew[i]-1)/ mid+1;
	}
	if (sum <= N)
		return 1;
	return 0;
}
int main(){
	scanf("%d %d", &N, &M);
	for (int i = 1; i<=M; i++)
		scanf("%d", &jew[i]);
	int left = 0, right= INF;
	while (left<right) {
		int mid = (left + right) / 2;
		if (ok(mid))
			right = mid;
		else 
			left = mid + 1;
	}
	printf("%d\n", left);
}
