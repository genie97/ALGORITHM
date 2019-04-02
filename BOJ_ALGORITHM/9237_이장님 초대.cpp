#include<cstdio>
#include<utility>
#include<algorithm>
#include<functional>
using namespace std;
int N;
int tree[100000];

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &tree[i]);
	}
	sort(tree, tree + N, greater<int>());
	int maxValue = 0;
	for (int i = 0; i < N; i++) {
		tree[i] = tree[i] + (i +1);
		maxValue = max(maxValue, tree[i]);
	}
	printf("%d\n", maxValue + 1);
}
