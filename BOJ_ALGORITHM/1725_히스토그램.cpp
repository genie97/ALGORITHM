#include<cstdio>
#include<stack>
using namespace std;
int his[100000];

int main() {
	int num, left, width, height;
	int area = 0;
	scanf("%d", &num);
	for (int i = 0; i < num; i++)
		scanf("%d", &his[i]);
	stack <int> s;
	for (int i = 0; i < num; i++) {
		left = i;
		while (!s.empty() && his[s.top()] > his[i]) {
			height = his[s.top()];
			s.pop();
			width = i;
			if (!s.empty())
				width = (i - s.top() - 1);
			if (area < width*height)
				area = width*height;
		}
		s.push(i);
	}
	while (!s.empty()) {
		height = his[s.top()];
		s.pop();
		int width = num;
		if (!s.empty())
			width = num - s.top() - 1;
		if (area < width*height)
			area = width*height;
	}
	printf("%d\n", area);
}