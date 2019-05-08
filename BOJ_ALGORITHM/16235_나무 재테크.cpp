#include<cstdio>
#include<algorithm>
#include<vector>
#include<cmath>
#include<cstring>
using namespace std;
vector<int> vt[12][12], temp[12][12];
int N, M, K, summer[12][12] = {0,}, A[12][12], map[12][12];
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };

void springNsummer() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (!vt[i][j].empty()) {
				sort(vt[i][j].begin(), vt[i][j].end());
				for (int k = 0; k < vt[i][j].size(); k++) {
					if (map[i][j] >= vt[i][j][k]) { //양분이 많을 때
						map[i][j] -= vt[i][j][k];
						vt[i][j][k]++; //나이 증가
					}
					else {
						//나무 죽이기
						summer[i][j] += (int)floor(vt[i][j][k] / 2);
						vt[i][j].erase(vt[i][j].begin() + k);
						k--;
					}
				}
			}
		}
	}
	// 죽은 나무 양분 더해주기
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			map[i][j] += summer[i][j];
		}
	}
	memset(summer, 0, sizeof(summer));
	
	//임시로 살아있는 나무 정리
	for(int i=1;i<=N;i++){
		for (int j = 1; j <= N; j++) {
			temp[i][j].clear();
			for (int k = 0; k < vt[i][j].size(); k++)
				temp[i][j].push_back(vt[i][j][k]);
		}
	}
}
void fall() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			for (int k = 0; k < vt[i][j].size(); k++) {
				if (vt[i][j][k] % 5==0) {
					for (int d = 0; d < 8; d++) {
						if (i + dx[d]<1 || i + dx[d]>N || j + dy[d]<1 || j + dy[d]>N)
							continue;
						temp[i + dx[d]][j + dy[d]].push_back(1);
					}
				}
			}
		}
	}
	//살아있는 나무 정리
	for (int i = 1; i<=N; i++) {
		for (int j = 1; j <=N; j++) {
			vt[i][j].clear();
			for (int k = 0; k < temp[i][j].size(); k++)
				vt[i][j].push_back(temp[i][j][k]);
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
		vt[a][b].push_back(c);
	}

	for (int y = 1; y <= K; y++) {
		//봄과 여름
		springNsummer();
		//가을
		fall();
		//겨울
		winter();
	}
	int ans = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			ans += vt[i][j].size();
		}
	}
	printf("%d\n", ans);
}

/* 
해당 풀이 시간초과 발생, vector의 크기만큼 반복문을 돌리는 것보다는 map크기만큼만 돌리는것이 효율적!
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
}*/
