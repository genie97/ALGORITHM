import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (0,0) 이 흰색 (1,1)이 흰색 
public class BOJ1100_하얀칸 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		int ans = 0;
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map.length; j++) {
				map[i][j] = str.charAt(j);
				if (i % 2 == 0 && j % 2 == 0 && map[i][j] == 'F') {
					ans++;
				}
				if (i % 2 == 1 && j % 2 == 1 && map[i][j] == 'F') {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

}
