import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ10825_국영수 {
	static class Student implements Comparable<Student> {
		int korean;
		int english;
		int math;
		String name;

		public Student(int korean, int english, int math, String name) {
			super();
			this.korean = korean;
			this.english = english;
			this.math = math;
			this.name = name;
		}

		@Override
		public int compareTo(Student s) {
			if (this.korean <= s.korean) {
				if (this.korean == s.korean) {
					if (this.english <= s.english) {
						if (this.english == s.english) {
							if (this.math >= s.math) {
								if (this.math == s.math) {
									return this.name.compareTo(s.name);
								}
							}
							return Integer.compare(s.math, this.math);
						}
					}
					return Integer.compare(this.english, s.english);
				}
			}
			return Integer.compare(s.korean, this.korean);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Student> students = new ArrayList<>();

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			int korean = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			students.add(new Student(korean, english, math, name));
		}

		Collections.sort(students);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			sb.append(s.name).append('\n');
		}

		System.out.println(sb);
	}
}
