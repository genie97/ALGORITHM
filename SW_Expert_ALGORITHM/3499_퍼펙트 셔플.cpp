#include<cstdio>
#include<iostream>
#include<string>
#include<sstream>
using namespace std;
int T, N;
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d", &N);
		if (N % 2 == 0) N -= 1;
		int i = 0, j = 0, k = 0;
		string in, dec1[501], dec2[501], input;
		cin >> in; //공백을 기준으로 string을 입력받는 경우, 첫번째 입력은 공백기준으로 짤려서 in에 저장됨
		dec1[j++] = in;
		getline(cin, in); //첫 번쨰 공백 이후의 모든 string이 in에 다시 저장됨
		stringstream ss(in); 
		while (ss >> input) { //stringstream인 ss가 공백 기준으로 다시 잘라서 input에 저장해줌  
			i++;
			if (i <= N / 2) dec1[j++] = input;
			else dec2[k++] = input;
		}
		string res = "";
		for (int i = 0; i <= N / 2; i++) {
			res.append(dec1[i]);
			res.append(" ");
			res.append(dec2[i]);
			if (i != N / 2)res.append(" ");
		}
		cout << "#" << tc << " " << res << endl;
	}
}
