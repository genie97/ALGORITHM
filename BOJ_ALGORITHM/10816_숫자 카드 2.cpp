#include<cstdio>
#include<algorithm>
using namespace std;
int N, M;
int card[500000], card2[500000];
int ans[500000];
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &card[i]);
	}
	scanf("%d", &M);
	for (int i = 0; i < M; i++) {
		scanf("%d", &card2[i]);
	}
	sort(card, card + N);
	for (int i = 0; i < M; i++) {
		int min_index = lower_bound(card, card + N, card2[i]) - card;
		int max_index = upper_bound(card, card + N, card2[i]) - card - 1;
		
		if (card[min_index] == card2[i] && card[max_index] == card2[i])
			ans[i] = max_index - min_index+1;
		else
			ans[i] = 0;
	}
	for (int i = 0; i < M; i++) {
		printf("%d ", ans[i]);
	}
}
