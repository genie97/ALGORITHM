#include<cstdio>
#include<algorithm>
using namespace std;
int stalagmite[100000]; //석순
int stalacite[1000000]; //종유석
int result[1000000];
int N, H;
int miteDestroy(int height);
int citeDestroy(int height);

int main() {
	int cnt = 0;
	int height;
	int citeD, miteD;
	scanf("%d%d", &N, &H);
	for (int i = 0; i < N; i++) {
		scanf("%d", &height);
		if (i % 2)
			stalagmite[i / 2] = height;
		else
			stalacite[i / 2] = height;
	}
	sort(stalagmite, stalagmite + (N / 2));
	sort(stalacite, stalacite + (N / 2));

	for (int i = 1; i <= H; i++) {
		citeD = citeDestroy(i);
		miteD = miteDestroy(i);
		result[i - 1] = citeD + miteD;
	}

	sort(result, result + H);

	for (int i = 1; i <= H; i++) {
		if (result[i - 1] == result[0])
			cnt++;
	}
	printf("%d %d", result[0], cnt);
}

int miteDestroy(int height) {
	int s = 0, e = (N / 2) - 1;
	int mid, destroy = 0;
	while (s <= e) {
		mid = (s + e) / 2;
		if (stalacite[mid] < height)
			s = mid + 1;
		else
			e = mid - 1;
	}
	destroy = (N / 2) - 1 - e;
	return destroy;
}
int  citeDestroy(int height) {
	int s = 0, e = (N / 2) - 1;
	int mid, destroy = 0;
	while (s <= e) {
		mid = (s + e) / 2;
		if (stalagmite[mid] < H - height + 1)
			s = mid + 1;
		else
			e = mid - 1;
	}
	destroy = (N / 2) - 1 - e;
	return destroy;
}
