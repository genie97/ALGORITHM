import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 안타: 타자와 모든 주자가 한 루씩 진루한다.
// 2루타: 타자와 모든 주자가 두 루씩 진루한다.
// 3루타: 타자와 모든 주자가 세 루씩 진루한다.
// 홈런: 타자와 모든 주자가 홈까지 진루한다.
// 아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다. 
// 가장 많은 득점하는 타순 찾기
public class BOJ17281_야구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), null);
			for (int j = 0; j < 9; j++) {

			}
		}

	}
}
