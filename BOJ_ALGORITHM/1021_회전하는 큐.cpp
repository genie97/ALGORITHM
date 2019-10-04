#include<cstdio>
#include<queue>
using namespace std;
deque<int> dq;
void left() {
	int a = dq.front();
	dq.pop_front();
	dq.push_back(a);
}
void right() {
	int a = dq.back();
	dq.pop_back();
	dq.push_front(a);
}
int main() {
	int N, M;
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		dq.push_back(i + 1);
	}
	int back_cnt = 0;
	int front_cnt = 0;

	for (int i = 0; i < M; i++) {
		int x;
		scanf("%d", &x);
		if (dq[0] == x) {
			dq.pop_front();
			N--;
		}
		else if (dq.back() == x) {
			right();
			front_cnt++;
			dq.pop_front();
			N--;
		}
		else {
			int idx = 0;
			for (int i = 0; i < dq.size(); i++) {
				if (dq[i] == x)  break; 
				idx++;
			}

			if (idx < (N % 2 == 0 ? N / 2 : (N + 1) / 2)) {
				while (dq[0] != x) {
					left();
					back_cnt++;
				}
				dq.pop_front();
				N--;
			}
			else {
				while (dq.back() != x) {
					right();
					front_cnt++;
				}
				right();
				front_cnt++;
				dq.pop_front();
				N--;
			}
		}
	}
	printf("%d\n", back_cnt + front_cnt);
}