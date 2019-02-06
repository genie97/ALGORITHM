#include<iostream>
#include<cstdio>
#include<string>
#include<set>
using namespace std;
set<string> st;
int n;
int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		string name, isEnter;
		cin >> name;
		cin >> isEnter;
		if (isEnter=="enter")
			st.insert(name);
		else
			st.erase(name);
	}
	for (auto i = st.rbegin(); i != st.rend(); i++) {
		cout << *i << "\n";
	}
}