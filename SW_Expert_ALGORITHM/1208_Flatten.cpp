#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;
int box[101], cnt, dump, maxV,minV,maxIndex,minIndex;
vector <int> vt[101];
int main() {
	for (int tc = 1; tc <= 10; tc++) {
		cnt = 0;
		maxV = 0;
		minV = 0;
		maxIndex = 0;
		minIndex = 0;
		scanf("%d", &dump);
		for (int i = 1; i <= 100; i++) {
			scanf("%d", &box[i]);
			vt[box[i]].push_back(i);
		}
		while (1) {
			maxV = *max_element(box + 1, box + 101);
			minV = *min_element(box + 1, box + 101);
			maxIndex = vt[maxV].back();
			minIndex = vt[minV].back();

			if (cnt == dump || maxV-minV <= 1) {
				break;
			}

			vt[maxV].pop_back();
			vt[minV].pop_back();
			box[maxIndex] -= 1;
			vt[box[maxIndex]].push_back(maxIndex);
			box[minIndex] += 1;

			vt[box[minIndex]].push_back(minIndex);
			cnt++;
		}
		printf("#%d %d\n", tc, box[maxIndex] - box[minIndex]);
	}
}