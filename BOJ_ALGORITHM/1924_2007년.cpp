#include<cstdio>
#include<string>
#include<iostream>
using namespace std;
int year[12] = { 31,28,31,30,31,30,31,31,30,31,30,31 };
string day[7] = { "SUN","MON", "TUE", "WED", "THU", "FRI", "SAT"};
int main() {
	int x, y, sum=0;
	scanf("%d%d", &x, &y);
	for (int i = 1; i < x; i++) {
		sum += year[i-1];
	}
	sum += y;
	int d = sum % 7;
	cout << day[d] << endl ;
}