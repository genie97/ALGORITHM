#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;

int N;
typedef pair<int, int> p;
p machine[1000001];
vector <p> arr;
int lineNum;
int segT[2000001];

long long sum(int node, int start, int end, int left, int right) {
	if (left > end || right < start)
		return 0;
	if (left <= start && end <= right)
		return segT[node];
	return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
}

void update(int node, int start, int end, int pos) {
	if (pos < start || pos> end) return;
	if (start == end) {
		segT[node] = 1;
		return;
	}
	update(node * 2, start, (start + end) / 2, pos);
	update(node * 2 + 1, (start + end) / 2 + 1, end, pos);
	segT[node] = segT[node * 2] + segT[node * 2 + 1];
}

int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &lineNum);
		machine[lineNum].first = i;
	}
	for (int i = 1; i <= N; i++) {
		scanf("%d", &lineNum);
		machine[lineNum].second = i;
	}
	for (int i = 0; i <= 1000000; i++) {
		if (machine[i].first != 0)
			arr.push_back({ machine[i].first,machine[i].second });
	}
	sort(arr.begin(), arr.end());
	long long crossNum = 0;
	for (int i = 0; i < N; i++) {
		crossNum = crossNum + sum(1, 1, N, arr[i].second, N);
		update(1, 1, N, arr[i].second);
	}
	printf("%lld\n", crossNum);
}

