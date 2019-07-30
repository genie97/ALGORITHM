/*
#include<cstdio>
#include<vector>
#include<algorithm>
#include<utility>
#include<cmath>
#define MIN(a,b) (((a)<(b)?(a):(b)))

using namespace std;
int N, K;
double sum = 0, M, V, S, Vsum = 0;
int m[500];
double ans = 987654321;

int main() {
	scanf("%d %d", &N, &K);

	for (int i = 0; i < N; i++) {
		scanf("%d", &m[i]);
	}
	for (int i = K; K <= N; K++) {
		for (int i = 0; i + K <= N; i++) {
			for (int j = 0; j < K; j++) {
				sum += m[i + j];
			}
			M = sum / (double)K;
			for (int j = 0; j < K; j++) {
				Vsum += pow((m[i + j] - M), 2.0);
			}
			V = (double)(Vsum / K);
			S = sqrt(V);
			ans = min(ans, S);
			sum = 0, M = 0, Vsum = 0, V = 0, S = 0;
		}
	}
	printf("%0.12lf", ans);
}
*/

