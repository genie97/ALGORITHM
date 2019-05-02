#include<cstdio>
#define M_PI 3.14159265358979323846
using namespace std;
double R;
int main() {
	scanf("%lf", &R);
	printf("%6f\n", M_PI * R * R);
	printf("%6f\n", R * R * 2);
	//double과 float형에서 정밀함에 대한 차이가 있다!!
}