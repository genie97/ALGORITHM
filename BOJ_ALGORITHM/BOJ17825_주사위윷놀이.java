import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ17825_주사위윷놀이 {
	static int[] dice;
	static ArrayList<int[]> horse;
	static Map<Integer, Integer> redRoute; // 일반 경로
	static Map<Integer, Integer> blueRoute; // 10,20,30 경로
	static Map<Integer, Integer> goalRoute; // 도착지로 올라가는 경로
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = sc.nextInt();
		}
		horse = new ArrayList<>();
		// 말의 처음 시작은 0이라고 생각한다
		for (int i = 0; i < 4; i++) {
			horse.add(new int[] { 0, 0 }); // 말 위치, 방향 (아래로 내려감)
		}
		// 경로 설정해두기
		setRoute();
		// 중복 순열
		ans = Integer.MIN_VALUE;
		selectHorse(0, 0);
		System.out.println(ans);
	}

	static void setRoute() {
		redRoute = new HashMap<Integer, Integer>();
		// 시작점~도착점 전까지
		for (int i = 0; i < 40; i += 2) {
			redRoute.put(i, i + 2);
		}
		// 도착점 추가
		redRoute.put(40, 50);

		blueRoute = new HashMap<Integer, Integer>();
		for (int i = 10; i < 19; i += 3) { // 10에서 이동
			blueRoute.put(i, i + 3);
		}
		blueRoute.put(19, 25);

		for (int i = 20; i < 24; i += 2) { // 20에서 이동
			blueRoute.put(i, i + 2);
		}
		blueRoute.put(24, 25);

		blueRoute.put(30, 28);
		for (int i = 28; i > 25; i -= 1) { // 10에서 이동
			blueRoute.put(i, i - 1);
		}

		goalRoute = new HashMap<Integer, Integer>();
		for (int i = 25; i < 40; i += 5) {
			goalRoute.put(i, i + 5);
		}
		goalRoute.put(40, 50);
	}

	static void selectHorse(int cnt, int sum) {
		if (cnt == 10) { // 10회 이동
			if (sum > ans) {
				ans = sum;
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int pos = horse.get(i)[0];
			int dir = horse.get(i)[1];
			int ret = move(cnt, i);
		
			if (ret > -1) { // 이동이 가능한 경우
				selectHorse(cnt + 1, sum + ret);
			}
			horse.get(i)[0] = pos;
			horse.get(i)[1] = dir;
		}
	}

	static int move(int cnt, int idx) {
		int cur = horse.get(idx)[0];
		int dir = horse.get(idx)[1];
		int moveCnt = dice[cnt];

		// 도착 지점
		if (cur == 50)
			return 0;

		for (int i = 0; i < moveCnt; i++) {
			if (dir == 0) // 아래로 내려가는 방향 - 일반 경로
				cur = redRoute.get(cur);
			if (dir == 1) // 10,25,30 파란경로
				cur = blueRoute.get(cur);
			if (dir == 2) // 도착지로 가는 경로
				cur = goalRoute.get(cur);

			if (cur == 25) {
				dir = 2;
			} else if (cur == 50)
				break;
		}

		if (cur == 50) { // 주사위 만큼 이동 후, 도착지인 경우
			horse.get(idx)[0] = cur;
		} else {
			if (dir == 0 && (cur == 10 || cur == 20 || cur == 30)) { // 일반 경로 타다가 파란색 원을 만나는 경우
				dir = 1;
			}

			boolean movable = true;
			for (int i = 0; i < 4; i++) {
				if (idx != i) { // 다른 말이 이미 있는 경우
					if (cur == horse.get(i)[0] && (cur == 40 || dir == horse.get(i)[1])) {
						movable = false;
					}
				}
			}
			if (movable) {
				horse.get(idx)[0] = cur;
				horse.get(idx)[1] = dir;
				return cur;
			} else {
				return -1;
			}
		}
		return 0;
	}
}
