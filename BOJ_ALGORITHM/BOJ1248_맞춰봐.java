import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 규현은 0~10까지 수를 알고 있다.
// 1~10까지 수를 썻다 (0은 잠시 까먹)
// 규현이 써놓은 숫자에 -를 썻다
// -10~10까지 알게 되었다
// 종이에 N개를 썻다
// 가능한 모든 N*(N+1)/2개의 구간합을 구했다
// 규현이가 쓴 수를 A
// A[i]는 규현이가 i번째 쓴 수
// S[i][j]는 A[i]부터 A[j]까지 합이 0보다 크면 + 0 이면 0, 0보다 작으면 -이다
// A도 -10~10까지의 정수로만 이루어져야 한다
// 수열의크기
// N(N+1)/2길이의 문자열
// 규현이가 쓴 N개의 수 A를 구해서 출력하면 된다.

public class BOJ1248_맞춰봐 {
	static int[] candidate;
	static char[][] S;
	static int N;
	static boolean isFill;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();

		S = new char[N][N];
		int idx = 0;
		
		// 구간합 부호 표시하기
		for (int i = 0; i < S.length; i++) {
			for (int j = i; j < S[i].length; j++) {
				S[i][j] = str.charAt(idx++);
			}
		}
		
		candidate = new int[N]; // 리스트 담는 배열
		isFill = false; // sb 채우면 다 종료하긴
		dfs(0);
		System.out.println(sb);
	}

	static void dfs(int cnt) {
		if (isFill)
			return;
		if (cnt == N) {
			for (int i = 0; i < candidate.length; i++) {
				sb.append(candidate[i]).append(' ');
			}
			isFill = true;
			return;
		}
		for (int i = -10; i <= 10; i++) {
			candidate[cnt] = i;
			if (possible(cnt)) { // 후보군 부호 확인
				dfs(cnt + 1);
			}
		}
	}

	static boolean possible(int cnt) {
		int sum = 0;
		for (int i = cnt; i >= 0; i--) {
			sum += candidate[i];
			if (S[i][cnt] == '+' && sum <= 0)
				return false;
			if (S[i][cnt] == '-' && sum >= 0)
				return false;
			if (S[i][cnt] == '0' && sum != 0)
				return false;
		}
		return true;
	}
}
