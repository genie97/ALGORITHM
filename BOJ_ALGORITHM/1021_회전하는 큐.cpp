#include<cstdio>
#include<queue>
using namespace std;
deque<int> dq;
//왼쪽으로 밀면 맨앞에 있는 값이 맨뒤로 감
void left() {
	int a = dq.front();
	dq.pop_front();
	dq.push_back(a);
}
//오른쪽으로 밀면 맨뒤에 있는 값이 맨앞으로 감
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
		if (dq[0] == x) { //처음 0번자리에 뽑으려는 x가 있을 때는 바로 뽑고 개수 줄이기
			dq.pop_front();
			N--;
		} 
		else if (dq.back() == x) { //맨 마지막자리에 뽑으려는 x가 있을 때는 앞으로 right하고 숫자 증가 후 지우기
			right();
			front_cnt++;
			dq.pop_front();
			N--;
		}
		else { //그 외 나머지인 경우 index를 찾는 것이 중요
			int idx = 0;
			for (int i = 0; i < dq.size(); i++) { //deque에서 인덱스 찾기
				if (dq[i] == x)  break; 
				idx++;
			}

			if (idx < (N % 2 == 0 ? N / 2 : (N + 1) / 2)) { //인덱스가 중간보다 앞에 있으면 앞으로 빼기
				while (dq[0] != x) {
					left();
					back_cnt++;
				}
				dq.pop_front();
				N--;
			}
			else { //중간부터~뒤까지면 뒤로 빼기
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
