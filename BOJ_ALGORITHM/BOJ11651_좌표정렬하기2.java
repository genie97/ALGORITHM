import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ11651_좌표정렬하기2 {
	static class Pos implements Comparable<Pos> {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.y <= o.y) {
				if (this.y == o.y) {
					return Integer.compare(this.x, o.x);
				}
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.y, o.y);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		ArrayList<Pos> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Pos(x, y));
		}
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < list.size(); i++) {
			Pos p = list.get(i);
			sb.append(p.x).append(' ').append(p.y).append('\n');
		}
		System.out.println(sb);
	}
}
