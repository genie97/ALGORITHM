#include<cstdio>
#include<algorithm>
using namespace std;
int T, N, cnt = 0;
int main() {
	scanf("%d", &T);
	while (T--) {
		int moving=0, idx=0,box[102];
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {	
			int w;
			scanf("%d", &w);
			if (w >= 50)
				moving++;
			else 
				box[idx++] = w; //50�̸� �ڽ�
		}
		cnt++;
		if (idx == 0) { //50�̻��� �ڽ����� 1���� ������ �������� �ִ�
			printf("#%d %d\n", cnt, moving);
		}
		else {
			sort(box, box + idx);
			int weight = box[--idx];
			int sum = weight;
			for (int i = 0; i < idx; i++) {
				sum += weight;
				if (sum >= 50) {
					weight = box[--idx];
					sum = weight;
					moving++;
				}
			}
			printf("#%d %d\n", cnt, moving);
		}
	}
}