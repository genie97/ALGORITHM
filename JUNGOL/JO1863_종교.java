import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JO1863_종교 {
	public static int N;
	public static int M;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] p = new int[N + 1];
		ArrayList<ArrayList<Integer>> edge = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			edge.add(new <Integer>ArrayList());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 간선 정보 저장
			edge.get(from).add(to);
			edge.get(to).add(from);
		}
		// 부모를 자기 자신으로 저장
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
		int setCnt = 0;
		// 인접리스트 확인하면서 q에 넣기
		for (int i = 1; i <= N; i++) {
			if (p[i] != i)
				continue;
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			setCnt++;
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int j = 0; j < edge.get(v).size(); j++) {
					int c = edge.get(v).get(j);
					if (p[c] == p[v])
						continue;
					q.offer(c);
					p[c] = p[v];
				}
			}
		}
		System.out.println(setCnt);
	}
}
