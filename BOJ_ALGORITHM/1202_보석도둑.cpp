#include<cstdio>
#include<utility>
#include<algorithm>
#include<queue>

using namespace std;
int N, K;
pair<int, int> jew[300001];
int C[300001];
long long sum = 0;

struct compare {
	bool operator()(const int &a, const int &b) {
		return a < b;
	}
};

int main() {
	scanf("%d%d", &N, &K);
	for (int i = 0; i < N; i++) {
		scanf("%d%d", &jew[i].first, &jew[i].second); //first: 무게 second: 가격
	}
	for (int i = 1; i <= K; i++) {
		scanf("%d", &C[i]);
	}

	sort(jew, jew + N);
	sort(C + 1, C + K + 1);

	priority_queue<int, vector<int>, compare> pq;

	for (int i = 1, j = 0; i <= K; i++) {
		while (j < N && jew[j].first <= C[i]) {
			pq.push(jew[j].second);
			j++;
		}
		if (!pq.empty()) {
			sum = sum + pq.top();
			pq.pop();
		}
	}
	printf("%lld\n", sum);
}