#include<cstdio>
#include<algorithm>
using namespace std;
bool comp(pair<int, int> a, pair<int, int> b) {
	return a.first > b.first;
}
int main() {
	pair<int, int> score[8];
	int index[5];
	for (int i = 0; i < 8; i++) {
		scanf("%d", &score[i].first);
		score[i].second = i+1;
	}
	sort(score, score + 8,comp);
	int sum = 0;
	for (int i = 0; i < 5; i++) {
		sum += score[i].first;
		index[i] = score[i].second;
	}
	printf("%d\n", sum);
	sort(index, index + 5);
	for (int i = 0; i < 5; i++) {
		printf("%d ", index[i]);
	}
}