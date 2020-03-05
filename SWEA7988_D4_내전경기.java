import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7988_D4_내전경기 {
	public static int K;
	public static HashMap<String, Integer> p;
	public static boolean[][] adj; // 인접행렬
	public static int[] team; // 무슨 팀으로 나눌지?
	public static int cnt; // 인원수
	public static boolean poss;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			p = new HashMap<String, Integer>();
			adj = new boolean[2 * K][2 * K];
			cnt = 0;
			
			StringTokenizer st;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				if (!p.containsKey(name1)) {
					p.put(name1, cnt++);
				}
				if (!p.containsKey(name2)) {
					p.put(name2, cnt++);
				}
				adj[p.get(name1)][p.get(name2)] = true;
				adj[p.get(name2)][p.get(name1)] = true;
			}
			
			team = new int[cnt]; // 팀을 나눈 결과
			poss = true; // 팀을 나눌 수 있는가?
			for (int i = 0; i < cnt; i++) {
				if (team[i] == 0) { // 팀 미정
					team[i] = 1; // 1번팀에 넣고 확인하기
					bfs(i);
				}
				if (!poss) // 하나라도 불가능하다면 끝내기
					break;
			}
			System.out.println("#" + tc + " " + (poss ? "Yes" : "No"));
		}
	}

	public static void bfs(int vertex) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(vertex);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < adj[v].length; i++) {
				if (adj[v][i]) { // 인접한 노드면
					if (team[i] != 0 && team[i] == team[v]) { // 이미 팀이 정해졌고, 인접했지만 팀이 같다면 false
						poss = false;
						return;
					}
					if (team[i] == 0) { // 팀이 아직 안정해진 인접노드
						team[i] = team[v] == 1 ? 2 : 1;
						q.add(i);
					}
				}
			}
		}
	}

}
