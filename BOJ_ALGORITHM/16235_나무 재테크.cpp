#include<cstdio>
#include<algorithm>
#include<vector>
#include<cmath>
using namespace std;
typedef pair<pair<int, int>, int>p;
vector<p> vt, dead;
int N, M, K, A[11][11], map[11][11];
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };
bool comp(p a, p b) {
	return a.second < b.second;
}
void spring() {
	for (int i = 0; i < M; i++) {
		if (map[vt[i].first.first][vt[i].first.second] >= vt[i].second) { //양분이 많을 때
			map[vt[i].first.first][vt[i].first.second] -= vt[i].second;
			vt[i].second++;
		}
		else {
			dead.push_back({ { vt[i].first.first,vt[i].first.second },vt[i].second });
			vt[i].second = 0;
		}
	}
}
void summer() {
	for (int i = 0; i < (int)dead.size(); i++) {
		map[dead[i].first.first][dead[i].first.second] += (int)floor(dead[i].second / 2);
	}
}
void fall() {
	for (int i = 0; i < M; i++) {
		if (vt[i].second % 5 == 0 && vt[i].second!=0) {
			for (int j = 0; j < 8; j++) {
				if (vt[i].first.first + dx[j]<1 || vt[i].first.first + dx[j]>N || vt[i].first.second + dy[j]<1 || vt[i].first.second + dy[j]>N)
					continue;
				vt.push_back({ { vt[i].first.first + dx[j],vt[i].first.second + dy[j] },1 });
			}
		}
	}
}
void winter() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			map[i][j] += A[i][j];
		}
	}
}
void process() {
	vector<p> temp;
	for (int i = 0; i < vt.size(); i++) {
		if (vt[i].second == 0)
			continue;
		temp.push_back({ { vt[i].first.first,vt[i].first.second },vt[i].second });
	}
	vt.clear();
	vt=temp;
	M = vt.size();
	dead.clear();
	sort(vt.begin(), vt.end());
}
int main() {
	scanf("%d%d%d", &N, &M, &K);
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			scanf("%d", &A[r][c]);
			map[r][c] = 5;
		}
	}
	for (int i = 0; i < M; i++) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		vt.push_back({ { a,b },c });
	}
	for (int y = 1; y <= K; y++) {
		//봄
		spring();
		//여름
		summer();
		//가을
		fall();
		//겨울
		winter();
		//처리
		process();
	}
	printf("%d\n", (int)vt.size());
}