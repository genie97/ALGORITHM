import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931_회의실배정 {
	public static class Time {
		int s, e;

		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Time[] time = new Time[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			time[i] = new Time(s, e);
		}
		Arrays.sort(time, new Comparator<Time>() {
			public int compare(Time t1, Time t2) {
				if (t1.e == t2.e)
					return t1.s - t2.s;
				return t1.e - t2.e;
			}
		});
		int cnt = 0;
		int eTime = 0;
		for (int i = 0; i < time.length; i++) {
			if (eTime <= time[i].s) {
				eTime = time[i].e;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
