import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138_전구와스위치 {
	static int N;
	static char[] copyOrigin;
	static char[] copyResult;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[] origin = br.readLine().toCharArray();
		char[] result = br.readLine().toCharArray();

		int zeroOnCnt = 0;
		int zeroNotOnCnt = 0;
		int ans = -1;

		// 현재 위치에서 바로 이전 전구의 상태가 다르면 다음 버튼을 눌러서 상태를 변경, 아니면 누르지 않고 상태 유지
		
		// 0을 누르자 / 말자로 나누어 본다
		// 0번 전구를 누른다면 1번에 영향을 준다
		// 1번 전구를 누른다면 0번에 영향을 준다
		
		// 0번 스위치를 누르지 않는다
		zeroOnCnt = push(origin, result);
		if (same(copyOrigin, copyResult)) {
			ans = zeroOnCnt;
		}

		// 0번 스위치를 누른다
		origin[0] ^= 1;
		origin[1] ^= 1;
		zeroNotOnCnt++;

		zeroNotOnCnt += push(origin, result);

		if (same(copyOrigin, copyResult)) {
			if (ans == -1) {
				ans = zeroNotOnCnt;
			} else {
				ans = Math.min(zeroNotOnCnt, zeroOnCnt);
			}
		}

		System.out.println(ans);
	}

	static int push(char[] origin, char[] result) {
		int cnt = 0;
		copyOrigin = origin.clone();
		copyResult = result.clone();

		for (int i = 1; i < copyOrigin.length; i++) {
			// 이전 상태가 다르다면 눌러서 바꿔줘야함
			if (copyOrigin[i - 1] != copyResult[i - 1]) {
				copyOrigin[i - 1] ^= 1;
				copyOrigin[i] ^= 1;
				if (i + 1 < N)
					copyOrigin[i + 1] ^= 1;
				cnt++;
			}
		}
		return cnt;
	}

	static boolean same(char[] origin, char[] result) {
		for (int i = 0; i < result.length; i++) {
			if (origin[i] != result[i]) {
				return false;
			}
		}
		return true;
	}
}
