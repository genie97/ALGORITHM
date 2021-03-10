import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시침이 12로부터 몇도 돌아가있는지 주어질때 몇시 몇분인지 출력
// 2도에 1분
// 채점 특이 케이스 => 마지막 케이스에 \n 들어가면 오답으로 판단 (이유 알수없음!)
public class SWEA9997_D3_미니멀리즘시계 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int degree = Integer.parseInt(br.readLine());
			int minute = degree * 2;
			int h = minute / 60;
			int m = minute % 60;
			sb.append(h).append(' ').append(m);
			if(tc < T) {
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
