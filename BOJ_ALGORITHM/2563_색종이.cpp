#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;
typedef pair<int, int> p;
int n, visit[101][101] = {0,};
int area;
int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		for (int r = a; r < a + 10; r++) {
			for (int c = b; c < b+10; c++) {
				if (!visit[r][c]) {
					visit[r][c] = 1;
					area += visit[r][c];
				}
			}
		}
	}
	printf("%d ", area);
}