import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA3378_D4_스타일리쉬들여쓰기 {
	public static class Indent {
		int s, m, l, c; // (), {}, [], 들여쓰기 수

		public Indent(int s, int m, int l, int c) {
			this.s = s;
			this.m = m;
			this.l = l;
			this.c = c;
		}
	}

	public static Indent[][] indent;
	public static ArrayList<int[]> RCSlist;
	public static int p;
	public static int q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			indent = new Indent[2][];
			indent[0] = new Indent[p + 1]; // 마스터한 사람
			indent[1] = new Indent[q + 1]; // 나

			// 초기화
			indent[0][0] = new Indent(0, 0, 0, 0);
			indent[1][0] = new Indent(0, 0, 0, 0);
			// 마스터한 사람
			for (int i = 1; i <= p; i++) {
				String str = br.readLine();
				countBracket(0, i, str);
			}

			// 나
			for (int i = 1; i <= q; i++) {
				String str = br.readLine();
				countBracket(1, i, str);
			}

			RCSlist = new ArrayList<int[]>();
			getRCS();

			int[] meAns = getMyString();
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < meAns.length - 1; i++) {
				sb.append(meAns[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int[] getMyString() {
		int[] ret = null;
		int n = indent[1].length;

		for (int i = 0; i < RCSlist.size(); i++) {
			int r = RCSlist.get(i)[0];
			int c = RCSlist.get(i)[1];
			int s = RCSlist.get(i)[2];
			int[] res = new int[indent[1].length];
			for (int j = 1; j < n; j++) {
				res[j] = r * indent[1][j].s + c * indent[1][j].m + s * indent[1][j].l;
			}
			if (ret == null) {
				ret = res;
			} else {
				for (int j = 1; j < n; j++) {
					if (ret[j] != res[j])
						ret[j] = -1;
				}
			}
		}
		return ret;
	}

	public static void getRCS() {
		for (int i = 1; i <= 20; i++) { // R
			for (int j = 1; j <= 20; j++) { // C
				for (int k = 1; k <= 20; k++) { // S
					if (isPossible(i, j, k)) {
						RCSlist.add(new int[] { i, j, k });
					}
				}
			}
		}
	}

	public static boolean isPossible(int r, int c, int s) {
		for (int i = 1; i < p; i++) {
			int sb = indent[0][i].s;
			int mb = indent[0][i].m;
			int lb = indent[0][i].l;
			int count = indent[0][i + 1].c;

			int res = sb * r + mb * c + s * lb;
			if (res != count)
				return false;
		}
		return true;
	}

	public static void countBracket(int xIdx, int yIdx, String str) {
		int c = 0;
		int s = indent[xIdx][yIdx - 1].s;
		int m = indent[xIdx][yIdx - 1].m;
		int l = indent[xIdx][yIdx - 1].l;

		// 들여쓰기 세기
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.')
				break;
			c++;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '(':
				s++;
				break;
			case ')':
				s--;
				break;
			case '{':
				m++;
				break;
			case '}':
				m--;
				break;
			case '[':
				l++;
				break;
			case ']':
				l--;
				break;
			}
		}

		indent[xIdx][yIdx] = new Indent(s, m, l, c);
	}

}
