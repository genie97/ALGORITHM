import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BOJ11652_카드 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Map<Long, Long> map = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			if (map.containsKey(num)) {
				long cnt = map.get(num);
				map.put(num, cnt + 1);
			} else {
				map.put(num, 1L);
			}
		}

		long maxCnt = 0;
		long maxNum = 0;
		for (Long num : map.keySet()) {
			long cnt = map.get(num);
			if (cnt > maxCnt) {
				maxCnt = cnt;
				maxNum = num;
			}
		}
		System.out.println(maxNum);
	}
}
