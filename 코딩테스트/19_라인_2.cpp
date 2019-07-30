#include <iostream>
#include<string>
using namespace std;
int main(void) {
	string str, res;
	string digit[100], upper[100], lower[100] = { "", };
	int B = 0, S = 0, D = 0;
	cin >> str;
	int len = str.length();
	for (int i = 0; i < len; i++) {
		if (isalpha(str[i])) {
			if (isupper(str[i])) {
				upper[B] = str[i];
				B++;
			}
			else {
				lower[S] = str[i];
				S++;
			}
		}
		else {
			digit[D] = str[i];
			D++;
		}
	}
	if (B != D)
		cout << "error" << endl;
	else {
		for (int i = 0; i < B; i++) {
			if (S = 0) {
				if (digit[i] == "1") {
					res += upper[i];
				}
				else
					res += upper[i] + digit[i];
			}
			else {
				if (digit[i] == "1") {
					res += upper[i] + lower[i];
				}
				else
					res += upper[i] + lower[i] + digit[i];
			}
		}
		cout << res << endl;
	}
}
