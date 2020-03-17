import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1812_D5_수정이의타일자르기 {
	public static class Rectangle implements Comparable<Rectangle> {
		int min, max;

		public Rectangle(int w, int h) {
			if (w < h) {
				max = h;
				min = w;
			} else {
				max = w;
				min = h;
			}
		}

		// 한 변의 길이가 최대인 사각형부터 꺼낼 예정
		@Override
		public int compareTo(Rectangle o) {
			return o.min - this.min;
		}

	}

	public static PriorityQueue<Rectangle> q;
	public static int ans;
	public static int[] Si;
	public static int N;
	public static int M;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Si = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				Si[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(Si);
			q = new PriorityQueue<>();
			ans = 1;
			q.add(new Rectangle(M, M));
			for (int i = N - 1; i >= 0; i--) {
				cut(1 << Si[i]);
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	public static void cut(int len) {
		Rectangle r = q.poll();
		if (len <= r.min) {
			q.add(new Rectangle(r.min - len, len));
			q.add(new Rectangle(r.min, r.max - len));
		} else {
			q.add(r);
			q.add(new Rectangle(M - len, len));
			q.add(new Rectangle(M, M - len));
			ans++;
		}
	}
}
