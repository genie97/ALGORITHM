import java.util.Scanner;
public class SWEA5215{
	static int sum_score = 0;
	static int sum_cal = 0;
	static int max_score = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {	
			int N = sc.nextInt(); // 재료수
			int L = sc.nextInt(); // 제한 칼로리
			
			int[] score = new int[N]; // 맛점수
			int[] cal = new int[N]; // 칼로리
			boolean[] visited = new boolean[N];
			
			for(int i=0;i<N;i++) {
				score[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}
			int maxV = 0;
			for(int i=0;i<N;i++) {
				sum_score=0;
				sum_cal = 0;
				max_score = 0;
				comb(score, cal, visited, 0, N, i, L);
				if(maxV<max_score)
					maxV = max_score;
			}
			System.out.println("#" + testCase +" " + maxV);
		}
	}
	/*
	 * score: 맛 점수
	 * cal: 칼로리 
	 * visited: 방문처리
	 * start: 시작 인덱스
	 * n: 조합 최대 개수
	 * r: 현재 조합 개수
	 * */
	private static void comb(int[] score, int[] cal, boolean[] visited, int start, int n, int r, int L) {
		if(r==-1) { //index가 0인거부터 보려면 -1일 때, return 할 것!
			if(sum_cal <= L) { //제한 칼로리보다 작으면
				 if(max_score < sum_score) {
					 max_score = sum_score;
				}
			}
			return; //조합 개수 만큼이면 return
		}
		else {
			for(int i=start;i<n;i++) {
				visited[i] = true;
				sum_cal += cal[i];
				sum_score += score[i];
				comb(score, cal, visited, i+1, n, r-1, L);
				visited[i] = false;
				sum_cal -= cal[i];
				sum_score -= score[i];
			}
		}
	}
}
