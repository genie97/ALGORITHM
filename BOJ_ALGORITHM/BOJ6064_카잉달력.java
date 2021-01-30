import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// M과 N보다 작거나 같은 두 개의 자연수 x,y를 가지고 <x:y>와 같은 형식 표현
// 첫해  <x:y> 다음해 <x':y'>
// x < M 이면 x' = x + 1
// 그렇지 않으면 x' = 1
// y < N 이면 y' = y + 1
// 그렇지 않으면 y' = 1
// <M:N>은 마지막 해

// 시간초과에 대한 아이디어
// x의 값을 고정시키고 y의 값이 가능한지 살펴본다
// M,N,x,y로 주어졌을때,
// ordering의 후보군은 M * k + x 가 될 수 있음 (k=1,2,3...)
// y는 (ordering - y) % N == 0 으로 확인할 수 있다.
// 불가능에 대한 확인 조건은 M*N의 횟수를 초과할 때까지 찾지 못한다면 불가능이다! 

public class BOJ6064_카잉달력 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int k = 0;
			int ordering = 0;

			while (ordering <= M * N) {
				ordering = k * M + x;
				if ((ordering - y) % N == 0) {
					break;
				}
				k++;
			}
			if (ordering > M * N) {
				ordering = -1;
			}
			sb.append(ordering).append('\n');
		}
		System.out.println(sb);
	}
}
