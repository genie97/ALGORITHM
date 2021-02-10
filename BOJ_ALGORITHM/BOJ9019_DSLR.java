import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 레지스터에는 0~10000미만의 십진수를 저장한다
// n을 다음과 같이 변환한다
// n의 네자리를 d1 d2 d3 d4라고 하자 ((d1 × 10 + d2) × 10 + d3) × 10 + d4
// 1. D: n을 두배로 바꾼다. 결과값이 9999보다 큰 경우에는 10000으로 나눈 나머지를 취한다. (2n mod 10000)
// 2. S: n에서 1을 뺀 결과 n-1을 레지스터에 저장한다. 단 n이 0일경우에는 9999이 저장된다
// 3. L: n의 각자릿수를 왼편으로 회전시킨다 d1,d2,d3,d4 > d2,d3,d4,d1
// 4. R: n의 각자릿수를 오른쪽으로 회전시킨다 d1,d2,d3,d4 > d4,d1,d2,d3
// 서로 다른 두 정수 A,B에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램이다

public class BOJ9019_DSLR {
	static class Register {
		int n;
		int cnt;
		String command;

		public Register(int n, int cnt, String command) {
			super();
			this.n = n;
			this.cnt = cnt;
			this.command = command;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int MOD = 10000;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			boolean[] check = new boolean[10000];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			Queue<Register> q = new LinkedList<>();
			q.add(new Register(A, 0, ""));
			check[A] = true;

			outer: while (!q.isEmpty()) {
				int size = q.size();
				for (int sz = 0; sz < size; sz++) {
					Register r = q.poll();
					if (r.n == B) {
						sb.append(r.command).append('\n');
						break outer;
					}
					// D (2n mod 10000)
					int num = r.n * 2 % MOD;
					if (!check[num]) {
						q.add(new Register(num, r.cnt + 1, r.command + "D"));
						check[num] = true;
					}

					// S n-1을 레지스터에 저장한다. 단 n이 0일경우에는 9999이 저장된다
					num = (r.n + 9999) % MOD;
					if (!check[num]) {
						q.add(new Register(num, r.cnt + 1, r.command + "S"));
						check[num] = true;
					}

					// L 일의자리부터 백의자리까지는 한자리씩 올리고, 천의자리를 일의자리로 바꿔준다.
					num = (r.n % 1000) * 10 + r.n / 1000;
					if (!check[num]) {
						q.add(new Register(num, r.cnt + 1, r.command + "L"));
						check[num] = true;
					}

					// R 일의자리를 천의자리로 바꿔주고, 천의자리부터 십의자리까지는 한자리씩 내린다.
					num = (r.n % 10) * 1000 + r.n / 10;
					if (!check[num]) {
						q.add(new Register(num, r.cnt + 1, r.command + "R"));
						check[num] = true;
					}
					// integer형 > char형
//					char[] tmp = new char[4];
//					String str_num = r.n + "";
//					while (str_num.length() < 4) {
//						str_num = '0' + str_num;
//					}
//
//					// L d1,d2,d3,d4 > d2,d3,d4,d1
//					for (int j = 0; j < tmp.length; j++) {
//						tmp[(j + 3) % 4] = str_num.charAt(j);
//					}
//					if (!check[make_int(tmp)]) {
//						q.add(new Register(make_int(tmp), r.cnt + 1, r.command + "L"));
//					}
//
//					// R d1,d2,d3,d4 > d4,d1,d2,d3
//					for (int j = 0; j < tmp.length; j++) {
//						tmp[(j + 1) % 4] = str_num.charAt(j);
//					}
//					if (!check[make_int(tmp)]) {
//						q.add(new Register(make_int(tmp), r.cnt + 1, r.command + "R"));
//					}
				}
			}
		}
		System.out.println(sb);
	}

	static int make_int(char[] a) {
		int num = a[0] - '0';
		for (int i = 1; i < a.length; i++) {
			num = num * 10 + (a[i] - '0');
		}
		return num;
	}
}
