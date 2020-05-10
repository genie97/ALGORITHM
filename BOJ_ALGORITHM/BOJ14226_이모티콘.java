import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226_이모티콘 {
	static char[] action = { 'V', 'C', 'R' };
	static int S;

	static class EMOTICON {
		char act; // 작업 명
		int cnt; // 총 작업 횟수
		int emozi; // 현재 화면에 있는 이모티콘 수
		int clipboard; // 현재 복사되어 있는 이모티콘 수

		public EMOTICON(char act, int cnt, int emozi, int clipboard) {
			super();
			this.act = act;
			this.cnt = cnt;
			this.emozi = emozi;
			this.clipboard = clipboard;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			S = Integer.parseInt(br.readLine());
			int res = bfS();
			System.out.println(res);
	}

	static int bfS() {
		Queue<EMOTICON> q = new LinkedList<>();
		boolean[][] visit = new boolean[1001][1001];
		q.add(new EMOTICON('V', 1, 1, 1)); // 처음 시작은 복사만 가능!
		visit[1][1] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				EMOTICON cur = q.poll();

				if (cur.emozi == S)
					return cur.cnt;

				for (int j = 0; j < 3; j++) {
					int emozi = cur.emozi;
					int clipboard = cur.clipboard;

					switch (action[j]) {
					case 'V':
						if (emozi + clipboard > 1000 || clipboard > 1000 || visit[emozi + clipboard][clipboard])
							continue;
						visit[emozi + clipboard][clipboard] = true;
						q.add(new EMOTICON('V', cur.cnt + 1, emozi + clipboard, clipboard));
						break;
					case 'C':
						if (emozi > 1000 ||visit[emozi][emozi])
							continue;
						visit[emozi][emozi] = true;
						q.add(new EMOTICON('C', cur.cnt + 1, emozi, emozi));
						break;
					case 'R':
						if (emozi <= 0)
							continue;
						if (emozi > 1000  || clipboard > 1000 || visit[emozi - 1][clipboard])
							continue;
						visit[emozi - 1][clipboard] = true;
						q.add(new EMOTICON('R', cur.cnt + 1, emozi - 1, clipboard));
						break;
					}
				}
			}
		}

		return -1;
	}

}
