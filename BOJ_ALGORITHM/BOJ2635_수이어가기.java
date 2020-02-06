import java.util.ArrayList;
import java.util.Scanner;
//1. 첫번째 숫자가 주어진다.
//2. 두번째수는 양의 정수 중에서 하나 선택
//3. 세번째부터 이후에 나오는 수는 [i+2] = [i+1] - [i]
//4. 음의 정수가 만들어지면 음의 정수를 버리고 끝
//5. 수들의 최대 개수를 출력
//방법 secNum을 1씩 늘려가면서 돌려보고 최대 개수찾기 
//최적화 : 주어진 n의 절반 보다 커지는 수부터 내림차순이 가능해짐!

public class BOJ2635_수이어가기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int caseNum = 0;
		int secNum = 0;
		for (int i = N / 2; i <= N; i++) {
			int a = i;
			int b = N;
			int cnt = 2;
			while (true) {
				int diff = b - a;
				if (diff < 0) {
					break;
				}
				b = a;
				a = diff;
				cnt++;
			}
			if (caseNum < cnt) {
				caseNum = cnt;
				secNum = i;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();

		list.add(N);
		list.add(secNum);
		int idx = 0;
		while (true) {
			int next = list.get(idx) - list.get(idx + 1);
			if (next < 0)
				break;
			list.add(next);
			idx++;
		}
		System.out.println(caseNum);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
