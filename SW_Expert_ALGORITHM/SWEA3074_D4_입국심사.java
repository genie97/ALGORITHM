import java.util.Arrays;
import java.util.Scanner;

public class SWEA3074_D4_입국심사 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long N = sc.nextLong();
			long M = sc.nextLong();
			long[] time = new long[(int)N];
			long max = 0;
			for (int i = 0; i < N; i++) {
				time[i] = sc.nextLong();
				if (max < time[i]) {
					max = time[i];
				}
			}
			long MaxTime = max * M; // 최대 시간
			long MinTime = 0;
            long ans = MaxTime;
			while (MaxTime >= MinTime) {
				long mid = (MaxTime + MinTime) / 2;
				long sum = 0L;
				for (int i = 0; i < time.length; i++) {
					sum += (mid / time[i]);
				}
				if (sum < M) {
					MinTime = mid + 1;
				} else {
                    ans = Math.min(ans, mid);
					MaxTime = mid - 1;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
