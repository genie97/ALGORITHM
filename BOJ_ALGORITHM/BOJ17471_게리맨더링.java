import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ17471_게리맨더링 {
	public static int N; // 구역 개수 (정점 개수)
	public static int[] s; // 정점의 부모 넣는 배열
	public static boolean[] visit; // 조합 만들기 위한 visit배열
	public static int[] list; // 조합이 담기는 리스트
	public static ArrayList<ArrayList<Integer>> edge; // 구역에 대한 인접리스트
	public static int[] p; // 각 정점별 사람 수
	public static int min; // 인원 차이의 최소 값

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		p = new int[N + 1]; 
		st = new StringTokenizer(br.readLine());
		// 인구 수 입력
		for (int i = 1; i < p.length; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		// 인접행렬 초기화
		edge = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			edge.add(new <Integer>ArrayList());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int vertexCnt = Integer.parseInt(st.nextToken()); // 인접 정점 개수
			for (int j = 0; j < vertexCnt; j++) {
				int v = Integer.parseInt(st.nextToken()); // 인접 정점
				// 인접 정보 저장
				edge.get(i + 1).add(v);
			}
		}
		min = Integer.MAX_VALUE;
		for (int caseN = 1; caseN <= N / 2; caseN++) { // 한 집합의 가능 개수
			s = new int[N + 1];

			list = new int[caseN];
			visit = new boolean[N + 1];
			gerrymandering(caseN, 0, 1); // 조합 확인하기
		}
		if (min == Integer.MAX_VALUE) { // 구역을 나누지 못할 때
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	public static void gerrymandering(int caseN, int cnt, int index) {
		if (caseN == cnt) {
			int diff = isPossible(caseN); // BFS로 연결이 되어있는지 확인하기
//			System.out.println(caseN + " 개 조합" + " " + diff);
			if (min > diff) { // 최소값 갱신하기
				min = diff;
			}
			return;
		}
		for (int i = index; i <= N; i++) { // 조합 만들기
			if (visit[i])
				continue;
			list[cnt] = i;
			visit[i] = true;
			gerrymandering(caseN, cnt + 1, i);
			visit[i] = false;
			list[cnt] = 0;
		}
	}

	public static int isPossible(int caseN) { //연결 여부 확인하기
	
		// parent 세팅
		for (int i = 1; i <= N; i++) {
			s[i] = i;
		}

		int[] as = new int[N - caseN];
		int idx = 0;
		// 다른 그룹 저장
		for (int i = 1; i <= N; i++) {
			if (!visit[i])
				as[idx++] = i;
		}

		// 조합으로 넘어온 그룹 체크
		int sv = list[0];
		int vcnt = 1;
		Queue<Integer> q = new LinkedList<>();
		q.offer(sv);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < list.length; i++) {
				if (v == list[i]) // 같은 정점은 패스
					continue;
				int c = list[i];
				if (!found(c, v)) // 인접행렬 가지고 연결되어 있는지 확인
					continue;
				if (s[v] == s[c]) // 이미 부모가 같으면 패스
					continue;
				s[c] = s[v];
				q.add(c);
				vcnt++;
			}
		}
		if (vcnt != caseN) // 연결이 잘 되어있지 않다!
			return Integer.MAX_VALUE;

		// parent 세팅
		for (int i = 1; i <= N; i++) {
			s[i] = i;
		}
		
		// 다른 조합 그룹 체크 (위와 같은 로직)
		q = new LinkedList<>();
		vcnt = 1;
		sv = as[0];
		q.offer(sv);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < as.length; i++) {
				if (v == as[i])
					continue;
				int c = as[i];
				if (!found(c, v))
					continue;
				if (s[v] == s[c])
					continue;
				s[c] = s[v];
				q.add(c);
				vcnt++;
			}
		}
		if (vcnt != N - caseN)
			return Integer.MAX_VALUE;

		/*각 set의 인구의 합, 인원 차이 리턴*/
		int set1 = 0;
		for (int i = 0; i < list.length; i++) {
			set1 += p[list[i]];
		}
		int set2 = 0;
		for (int i = 0; i < as.length; i++) {
			set2 += p[as[i]];
		}
		return Math.abs(set1 - set2);
	}

	public static boolean found(int c, int v) { // 인접행렬로 정점이 연결되어 있는지 확인
		for (int i = 0; i < edge.get(v).size(); i++) {
			if (edge.get(v).get(i) == c)
				return true;
		}
		return false;
	}
}
