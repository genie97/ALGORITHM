#include<cstdio>
int N;
int arr[8];
int main() {
	for (int tc = 0; tc < 10; tc++) {
		scanf("%d", &N);
		for (int i = 0; i < 8; i++) {
			scanf("%d", &arr[i]);
		}
		int k = 0;
		int index = -1;
		while (1) {
			for (int i = 1; i <= 5; i++) {
				if (k == 8) k = 0;
				if ((arr[k] - i) <= 0) {
					arr[k] = 0;
					index = k;
					break;
				}
				arr[k] = arr[k] - i;
				k++;
			}
			if (index >= 0 && index < 8) break;
		}
		printf("#%d ", N);
		for (int i = index + 1; i < 8; i++)
			printf("%d ", arr[i]);
		for (int i = 0; i <= index; i++)
			printf("%d ", arr[i]);
		printf("\n");
	}
}