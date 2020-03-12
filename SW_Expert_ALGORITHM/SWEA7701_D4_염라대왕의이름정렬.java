import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/*Treeset 이용, 입력 받아서 넣을 때부터 sorting해서 넣기 => 580ms */
public class SWEA7701_D4_염라대왕의이름정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (o1.length() < o2.length())
						return -1;
					else {
						if (o1.length() == o2.length())
							return o1.compareTo(o2);
						else
							return 1;
					}
				}
			});
			
			for (int i = 0; i < N; i++) {
				String n = br.readLine();
				ts.add(n);
			}
			
			sb.append("#").append(tc).append("\n");
			Iterator<String> it = ts.iterator();
			while(it.hasNext()) {
				sb.append(it.next()).append("\n");;
			}
		}
		System.out.println(sb);
	}

}
/* HashSet 사용 list로 변환 후 sort => 681 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class SWEA7701_D4_염라대왕의이름정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			HashSet<String> s = new HashSet<>();
			for (int i = 0; i < N; i++) {
				String n = br.readLine();
				s.add(n);
			}
			ArrayList<String> list = new ArrayList<>(s);
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (o1.length() < o2.length())
						return -1;
					else {
						if (o1.length() == o2.length())
							return o1.compareTo(o2);
						else
							return 1;
					}
				}
			});
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i)).append("\n");
			}
		}
		System.out.println(sb);
	}

}
*/
