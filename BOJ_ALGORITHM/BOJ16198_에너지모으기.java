import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// n개의 에너지 구슬이 일렬로 놓여져 있다
// i번째 에너지구슬의 무게는 Wi
// 에너지를 모으는 방법
// 1. 에너지 구슬 하나를 고른다. 고른 에너지 구슬의 번호를 x라고 한다. (단 처음과 끝은 선택x)
// 2. x번째 에너지 구슬을 제거한다.
// 3. Wx-1 x Wx+1의 에너지를 모을 수 있다.
// 4. N을 1 감소시키고, 에너지 구슬을 다시 1~N까지로 매긴다
// 에너지 구슬의 개수 N
// 에너지 구슬의 무게 W

public class BOJ16198_에너지모으기 {
	static boolean[] used;
	static int max;
	static int[] W;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		W = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;

		used = new boolean[N];
		for (int i = 1; i < W.length - 1; i++) {
			used[i] = true;
			choose(i, 1, W[i - 1] * W[i + 1]);
			used[i] = false;
		}
		System.out.println(max);
	}

	static void choose(int idx, int cnt, int value) {
		if (cnt == N - 2) {
			max = Math.max(max, value);
			return;
		}
		for (int i = 1; i < W.length - 1; i++) {
			if (used[i])
				continue;
			used[i] = true;
			int d_x = i;
			// 선택한 구슬 기준으로 옆에 있는 구슬을 확인하기 위해 (제거된 구슬은 used[i] = true로 표기)
			while (--d_x >= 0) {
				if (!used[d_x])
					break;
			}
			int i_x = i;
			while (++i_x >= 0) {
				if (!used[i_x])
					break;
			}

			choose(i, cnt + 1, value + W[d_x] * W[i_x]);
			used[i] = false;
		}
	}
}
