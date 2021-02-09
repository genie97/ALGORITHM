import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 8개의 톱니를 가진 톱니바퀴 4개가 일렬
// 톱니는 N극 또는 S극
// 톱니바퀴 번호가 있는데 가장 왼쪽이 1,2,3,4순
// 톱니바퀴를 K번 회전시키려고 한다
// 톱니바퀴 회전은 한칸 기준 => 시계방향, 반시계방향
// 맞닿은 톱니 극이 다르면 A 회전방향이랑 반대로 B가 회전

// 첫째줄 1번 톱니바퀴 상태
// 둘째줄 2번 톱니바퀴 상태
// 셋째줄 3번 톱니바퀴 상태
// 넷째줄 4번 톱니바퀴 상태
// N극은 0 S극은 1
// 다섯째줄에는 회전횟수
// 다음 k개 줄에는 회전시킨 방법
// 첫번째 정수는 회전시킨 톱니번호, 두번째수는 방향 (1이 시계, -1이 반시계)
// K번 회전 후 점수계산하기
//(12시방향 = 인덱스 0번)
// 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점 
// 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
// 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
// 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점

// 인덱스별 특징
// 0번지 => 최종 점수 계산
// 2번지와 6번지 => 1번 - 2번 (2,6)검사 / 2번 - 3번 (2,6) 검사 / 3번 - 4번 (2,6)
// 돌게 되면 방향 반대로 돌아야함!

// 시계방향 공식 (i+1)%8
// 반시계방향 공식 (i+7)%8
public class BOJ14891_톱니바퀴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] saw_tooth = new int[4][8];
		for (int i = 0; i < saw_tooth.length; i++) {
			String input = br.readLine();
			for (int j = 0; j < saw_tooth[i].length; j++) {
				saw_tooth[i][j] = input.charAt(j) - '0';
			}
		}
		// 회전수
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int number = Integer.parseInt(st.nextToken()) - 1; // 톱니 번호
			int dir = Integer.parseInt(st.nextToken()); // 방향
			saw_tooth = move(number, dir, saw_tooth);
		}
		int sum = 0;
		for (int i = 0; i < saw_tooth.length; i++) {
			sum += (saw_tooth[i][0] == 0 ? 0 : (int) Math.pow(2, i));
		}
		System.out.println(sum);
	}

	static int[][] move(int number, int dir, int[][] saw_tooth) {
		int[][] tmp = new int[4][8];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tmp[i][j] = saw_tooth[i][j];
			}
		}

		// 기준 톱니기준 앞쪽
		int copy_dir = dir;
		for (int i = number; i > 0; i--) {
			// 같으면 회전하지 않음
			if (saw_tooth[i][6] == saw_tooth[i - 1][2])
				break;
			// 같지 않으면 회전함
			copy_dir = copy_dir == -1 ? 1 : -1;
			if (copy_dir == -1) {
				for (int j = 0; j < saw_tooth[i - 1].length; j++) {
					tmp[i - 1][(j + 7) % 8] = saw_tooth[i - 1][j];
				}
			} else {
				for (int j = 0; j < saw_tooth[i - 1].length; j++) {
					tmp[i - 1][(j + 1) % 8] = saw_tooth[i - 1][j];
				}
			}
		}
		// 기준 톱니 기준 뒤쪽
		copy_dir = dir;
		for (int i = number; i < saw_tooth.length - 1; i++) {
			// 같으면 회전하지 않음
			if (saw_tooth[i][2] == saw_tooth[i + 1][6])
				break;
			// 같지 않으면 회전함
			copy_dir = copy_dir == -1 ? 1 : -1;
			if (copy_dir == -1) {
				for (int j = 0; j < saw_tooth[i + 1].length; j++) {
					tmp[i + 1][(j + 7) % 8] = saw_tooth[i + 1][j];
				}
			} else {
				for (int j = 0; j < saw_tooth[i + 1].length; j++) {
					tmp[i + 1][(j + 1) % 8] = saw_tooth[i + 1][j];
				}
			}
		}
		// 기준 톱니 회전 시키기

		if (dir == -1) {
			for (int j = 0; j < saw_tooth[number].length; j++) {
				tmp[number][(j + 7) % 8] = saw_tooth[number][j];
			}
		} else {
			for (int j = 0; j < saw_tooth[number].length; j++) {
				tmp[number][(j + 1) % 8] = saw_tooth[number][j];
			}
		}
		return tmp;
	}
}
