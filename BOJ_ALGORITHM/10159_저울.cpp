/*9
11
2 1
3 1
2 8
2 9
7 8
4 5
6 7
6 3
1 7
6 2
1 9*/
#include<cstdio>
#include<algorithm>
using namespace std;
int N, M;
bool things1[107][107] = { {false}, }, things2[107][107] = { {false}, };
int main(){
	scanf("%d%d", &N, &M);
	int cnt = 0;
	for (int i = 1; i <= M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		things1[a][b] =  true;
		things2[b][a] = true;
	}
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			if (k == i) continue;
			for (int j = 1; j <= N; j++) {
				if (k == j || j == i) continue;
				if (things1[i][k] == true && things1[k][j] == true )
					things1[i][j] = true;
				if (things2[i][k] == true && things2[k][j] == true)
					things2[i][j] = true;
			}
		}
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			things1[i][j] = things1[i][j] || things2[i][j];
		}
	}
	
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (things1[i][j] == false && i!=j)
				cnt++; 
		}
		printf("%d\n", cnt);
		cnt = 0;
	}
}