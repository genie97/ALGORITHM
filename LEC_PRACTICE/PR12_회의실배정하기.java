import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PR12_회의실배정하기 {
	public static class Time {
		int s, e;

		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		Time[] time = new Time[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			time[i] = new Time(s, e);
		}
		Arrays.sort(time, new Comparator<Time>() {
			public int compare(Time t1, Time t2) {
				return t1.e - t2.e;
			}
		});

		int mCnt = Integer.MIN_VALUE;
		int cnt = 1;
		ArrayList<Time> tmp = new ArrayList<>();
		ArrayList<Time> ans = new ArrayList<>();
		for (int i = 0; i < time.length; i++) {
			int eTime = time[i].e;
			tmp.add(new Time(time[i].s, time[i].e));
			for (int j = i + 1; j < time.length; j++) {
				if (eTime <= time[j].s) {
					cnt++;
					eTime = time[j].e;
					tmp.add(new Time(time[j].s, time[j].e));
				}
				if (mCnt < cnt) {
					mCnt = cnt;
					ans = (ArrayList<Time>) tmp.clone();
				}
			}
			cnt = 1;
			tmp.clear();
		}
		System.out.println(mCnt);
		System.out.println("회의 배정 시간");
		for (int i = 0; i < ans.size(); i++) {
			System.out.println("시작 : " + ans.get(i).s + " 종료 : " + ans.get(i).e);
		}
	}
}
