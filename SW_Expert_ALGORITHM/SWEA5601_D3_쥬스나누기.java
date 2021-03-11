import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 원하는만큼 먼저 따름
// n번째사람이 남은잔중에 하나를 갖고
// n-1번째 사람이 남은중에 하나 가져가기
// 최대한의 쥬스를 마시고자 최선의 전략을 쓴다
// 결국엔 1/n이 가장 베스트
// 1L
// 기약분수로 만들어 P/Q꼴로

public class SWEA5601_D3_쥬스나누기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < N; i++) {
				sb.append("1/").append(N).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
