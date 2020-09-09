import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ10814_나이순정렬 {
	static class Member implements Comparable<Member> {
		int idx;
		int age;
		String name;

		public Member(int idx, int age, String name) {
			super();
			this.idx = idx;
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Member o) {
			if (this.age <= o.age) {
				if (this.age == o.age) {
					return Integer.compare(this.idx, o.idx);
				}
				return Integer.compare(this.age, o.age);
			}
			return Integer.compare(this.age, o.age);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Member> members = new ArrayList<>();
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			members.add(new Member(i, age, name));
		}
		
		Collections.sort(members);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < members.size(); i++) {
			sb.append(members.get(i).age).append(' ').append(members.get(i).name).append('\n');
		}
		
		System.out.println(sb);
	}
}
