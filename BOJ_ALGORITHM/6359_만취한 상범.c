#include<stdio.h>
#define OPEN 1
#define CLOSE 0
#define MAX 101
int student[MAX];

int main() {
	int test, roomNum;
	scanf("%d", &test);
	for (int i = 0; i < test; i++) {
		scanf("%d", &roomNum);
		student[i] = solve(roomNum);
	}
	for (int i = 0; i < test; i++) {
		printf("%d\n", student[i]);
	}
}
int solve(int roomNum) {
	int cnt = 0;
	int room[MAX];
	for (int i = 1; i <= roomNum; i++) {
		for (int j = 1; j <= roomNum; j++) {
			if (i == 1)
				room[j] = OPEN;
			else if (j%i == 0) {
				if (room[j] == OPEN)
					room[j] = CLOSE;
				else
					room[j] = OPEN;
			}
		}
	}
	for (int i = 1; i <= roomNum; i++) {
		if (room[i] == OPEN)
			cnt++;
	}
	return cnt;
}