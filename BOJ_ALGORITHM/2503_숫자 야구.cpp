#include<cstdio>
#include<cstring>
#include<string>
using namespace std;

bool check[1000];
int strike, ball, number, N, ans=0;
int strike_cnt, ball_cnt;
string temp, ori_num, comp_num;

int main() {
	scanf("%d", &N);
	memset(check, true, sizeof(check));
	for (int i = 123; i <= 987; i++) {
		temp = to_string(i);
		if (temp[0] == temp[1] || temp[0] == temp[2] || temp[1] == temp[2])
			check[i] = false;
		if (temp[0] - '0' == 0 || temp[1] - '0' == 0 || temp[2] - '0' == 0)
			check[i] = false;
	}

	for (int i = 0; i < N; i++) {
		scanf("%d%d%d", &number, &strike, &ball);
		for (int j = 123; j <= 987; j++) {
			strike_cnt = 0;
			ball_cnt = 0;
			if (check[j]) {
				ori_num = to_string(number);
				comp_num = to_string(j);
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (x == y&&ori_num[x] == comp_num[y])
							strike_cnt++;
						if (x != y&&ori_num[x] == comp_num[y])
							ball_cnt++;
					}
				}
				if (strike_cnt != strike || ball_cnt != ball)
					check[j] = false;
			}
		}
	}
	for (int i = 123; i <= 987; i++) {
		if (check[i])
			ans++;
	}
	printf("%d\n", ans);
}