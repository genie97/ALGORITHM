#include<cstdio>
#include<algorithm>
using namespace std;
int main() {
	int n;
	int keyboard[1001];
	int ans=0;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &keyboard[i]);
	}
	sort(keyboard, keyboard + n);
	for (int i = 0; i < n-1; i++) {
		int sub = keyboard[i + 1] - keyboard[i];
		if (sub != 1)
			ans += sub-1;
		else
			continue;
	}
	printf("%d\n", ans);
}