import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 몇번만에 정렬이 되는지에 대한 문제 (얼마나 이동했는지의 최댓값)
// bool change = false;
// for (int i=1; i<=n+1; i++) {
//     change = false;
//     for (int j=1; j<=n-i; j++) {
//         if (a[j] > a[j+1]) {
//             change = true;
//             swap(a[j], a[j+1]);
//         }
//     }
//     if (change == false) {
//         cout << i << '\n';
//         break;
//     }
// }
public class BOJ1377_버블소트 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			list.add(new int[] { Integer.parseInt(br.readLine()), i });
		}
		
		int cnt = 0;
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		for (int i = 0; i < list.size(); i++) {
			int step = list.get(i)[1] - i;
			cnt = Math.max(list.get(i)[1] - i, cnt);
		}
		System.out.println(cnt + 1);
	}
}
