#include<cstdio>
#include<algorithm>
#include<cmath>
using namespace std;
int main() {
	while (1) {
		int arr[3];
		scanf("%d%d%d", &arr[0], &arr[1], &arr[2]);
		if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) break;
		sort(arr, arr + 3);
		if (arr[2] == sqrt((int)pow(arr[0], 2) + (int)pow(arr[1], 2)))
			printf("right\n");
		else
			printf("wrong\n");
	}
}