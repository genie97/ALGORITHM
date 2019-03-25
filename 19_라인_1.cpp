#include<iostream>
#include<math.h>
using namespace std;

bool isSquare(int n) {
	return pow((pow((double)n, (double)0.5)), 2) == n;
}

int main(void) {
	int N, w, h;
	double min = 1e+13;
	cin >> N;
	if (isSquare(N)) {
		cout << 0 << endl;
		return 0;
	}
	for (w = 1; w <= N; w++) {
		if (N%w == 0) {
			h = N / w;
			if (w == h) {
				cout << abs(w - h) << endl;
				return 0;
			}
			if (min > abs(w - h))
				min = abs(w - h);
		}
	}
	cout << min << endl;
	return 0;
}
