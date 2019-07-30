#include<cstdio>
#include<vector>
#include<algorithm>
#include<utility>

using namespace std;

int main() {
	int T, a, b, moneyA=0,moneyB = 0;
	typedef pair <int, int> p;

	vector <p> AC(7);
	AC[0] = make_pair(0, 0);
	AC[1] = make_pair(1, 500);
	AC[2] = make_pair(3, 300);
	AC[3] = make_pair(6, 200);
	AC[4] = make_pair(10, 50);
	AC[5] = make_pair(15, 30);
	AC[6] = make_pair(21, 10);

	vector <p> BC(6);
	BC[0] = make_pair(0, 0);
	BC[1] = make_pair(1, 512);
	BC[2] = make_pair(3, 256);
	BC[3] = make_pair(7, 128);
	BC[4] = make_pair(15, 64);
	BC[5] = make_pair(31, 32);

	scanf("%d", &T);

	for (int i = 0; i < T; i++) {
		scanf("%d %d", &a, &b);

		for (int i = 0; i < 7; i++) {
			if (a <= AC[i].first) {
				moneyA += (AC[i].second) * 10000;
				break;
			}
		}

		for (int i = 0; i < 6; i++) {
			if (b <= BC[i].first) {
				moneyB += (BC[i].second) * 10000;
				break;
			}
		}
	
		printf("%d\n", moneyA+moneyB);

		moneyA = 0, moneyB = 0;
	}
}
