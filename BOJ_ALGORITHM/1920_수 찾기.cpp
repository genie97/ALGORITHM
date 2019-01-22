#include<cstdio>
#include<algorithm>
using namespace std;
int N, M;
int A[100002], found;
void search(int key) {
	int left = 0;
	int right = N - 1;
	int mid;
	while (left <= right) {
		mid = (left + right) / 2;
		if (A[mid] == key) {
			printf("1\n");
			return;
		}
		else if (A[mid] > key) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	printf("0\n");
}
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) 
		scanf("%d", &A[i]);
	sort(A, A + N);
	scanf("%d", &M);
	for (int i = 0; i < M; i++) {
		scanf("%d", &found);
		search(found);
	}
}