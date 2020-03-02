import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA7699_D4_수지의수지맞는여행  {
 
    public static int R;
    public static int C;
    public static int ans;
    public static char[][] map;
    public static boolean[] visit;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            visit = new boolean[26];
            for (int i = 0; i < map.length; i++) {
                String str = br.readLine();
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = str.charAt(j);
                }
            }
            visit[map[0][0] - 'A'] = true;
            ans = Integer.MIN_VALUE;
            dfs(0, 0, 1);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
 
    private static void dfs(int x, int y, int cnt) {
        if (ans < cnt) {
            ans = cnt;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                continue;
            int c = map[nx][ny] - 'A';
            if (visit[c])
                continue;
            visit[c] = true;
            dfs(nx, ny, cnt + 1);
            visit[c] = false;
        }
    }
}
