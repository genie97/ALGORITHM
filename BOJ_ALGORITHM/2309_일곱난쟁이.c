#include<stdio.h>
int height[9];
int sum = 0;
int index1, index2;

void sort();
void find();

int main() {
	for (int i = 0; i < 9; i++) {
		scanf("%d", &height[i]);
	}

	sort();
	find();

	for (int i = 0; i < 9; i++) {
		if (i == index1 || i == index2)
			continue;
		else
			printf("%d ", height[i]);
	}
}

void sort() {
	int temp = 0;
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			if (height[i] < height[j]) {
				temp = height[j];
				height[j] = height[i];
				height[i] = temp;
			}
		}
	}
}

void find() {
	for (int i = 0; i < 9; i++) {
		sum = sum + height[i];
	}

	for (int i = 0; i < 9; i++) {
		for (int j = 1; j < 9; j++) {
			if (height[i] + height[j] == sum - 100) {
				index1 = i;
				index2 = j;
			}
		}
	}
}