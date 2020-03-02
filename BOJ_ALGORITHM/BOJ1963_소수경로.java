import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1963_소수경로 {

	public static boolean[] prime = new boolean[10000];
	public static boolean[] visit;
	public static int[] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		eratos();
		while ((T--) > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			if (num1 == num2) {
				sb.append(0).append("\n");
				continue;
			} else {
				visit = new boolean[10000];
				memo = new int[10000];
				bfs(num1, num2);
				if(memo[num2] == 0) {
					sb.append("Impossible").append("\n");
				}else {					
					sb.append(memo[num2]).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	public static void bfs(int num1, int num2) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(num1);
		visit[num1] = true;
		memo[num1] = 0;

		while (!q.isEmpty()) {
			int curNum = q.poll();
			// 4자리 분해하기
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 10; j++) {
					int nextNum = changeNum(curNum, i, j);
					if (nextNum == -1)
						continue;
					if (prime[nextNum] && !visit[nextNum]) {
						visit[nextNum] = true;
						q.add(nextNum);
						memo[nextNum] = memo[curNum] + 1;
					}
				}
			}
		}

	}

	public static int changeNum(int num, int idx, int d) {
		if (idx == 0 && d == 0)
			return -1; // 천의 자리 수 0
		int[] nArr = new int[4];
		for (int i = num, index = 4; i > 0; i /= 10) {
			nArr[--index] = (i % 10);
		}
		if (nArr[idx] == d)
			return -1; // 기존 자리에 있는 수와 바꾸려는 값이 같다면 return

		nArr[idx] = d;
		int cn = 0;
		for (int i = 1000, index = 0; index < nArr.length; i /= 10) {
			cn += (nArr[index++] * i);
		}
		return cn;
	}

	// 소수 미리 구해놓기
	public static void eratos() {
		for (int i = 2; i < 9999; i++) {
			prime[i] = true;
		}
		for (int i = 2; i < prime.length; i++) {
			if (prime[i]) {
				for (int j = i + i; j < prime.length; j += i) {
					prime[j] = false;
				}
			}
		}
	}

}
