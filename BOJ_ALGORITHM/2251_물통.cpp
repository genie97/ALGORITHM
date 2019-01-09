#include<cstdio>
#include<queue>
#include<algorithm>
#include<utility>
using namespace std;
typedef pair < pair<int, int>, int> p;
queue <p>  q;

bool visited[202][202];
bool ans[202];
int a, b, c;

void bfs() {
	q.push({ {0,0},c });
	while (!q.empty()) {
		p front = q.front();
		q.pop();
		if (visited[front.first.first][front.first.second])
			continue;
		visited[front.first.first][front.first.second] = true;
		if (front.first.first == 0)
			ans[front.second] = true;
		//1. A->B 
		if (front.first.first + front.first.second > b) {
			q.push({ { front.first.first + front.first.second - b,b},front.second });
		}
		else {
			q.push({ {0,front.first.first + front.first.second },front.second });
		}

		//2. A->C 
		if (front.first.first + front.second > c) {
			q.push({ { front.first.first + front.first.second - c,front.first.second },c });
		}
		else {
			q.push({ { 0,front.first.second },front.first.first + front.second });
		}
		
		//3. B->A 
		if (front.first.second + front.first.first > a) {
			q.push({ { a, front.first.second + front.first.first - a },front.second });
		}
		else {
			q.push({ { front.first.second + front.first.first,0 },front.second });
		}
		//4. B->C
		if (front.first.second + front.second > c) {
			q.push({ { front.first.first, front.first.second + front.second - c },c });
		}
		else {
			q.push({ { front.first.first,0 },front.first.second + front.second });
		}
		
		//5. C->A
		if (front.second + front.first.first > a) {
			q.push({ { a,front.first.second }, front.second + front.first.first - a });
		}
		else {
			q.push({ { front.second + front.first.first ,front.first.second },0 });
		}
		//6. C->B
		if (front.second + front.first.second > b) {
			q.push({ { front.first.first ,b },front.second + front.first.second - b });
		}
		else {
			q.push({ { front.first.first , front.second + front.first.second },0 });
		}
	}
}
int main() {
	scanf("%d %d %d", &a, &b, &c);
	bfs();
	for (int i = 0; i <= c; i++) {
		if (ans[i])
			printf("%d ", i);
	}
}