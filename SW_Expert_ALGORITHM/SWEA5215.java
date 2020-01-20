import java.util.Scanner;
public class SWEA5215{
	static int sum_score = 0;
	static int sum_cal = 0;
	static int max_score = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {	
			int N = sc.nextInt(); // ����
			int L = sc.nextInt(); // ���� Į�θ�
			
			int[] score = new int[N]; // ������
			int[] cal = new int[N]; // Į�θ�
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
	 * score: �� ����
	 * cal: Į�θ� 
	 * visited: �湮ó��
	 * start: ���� �ε���
	 * n: ���� �ִ� ����
	 * r: ���� ���� ����
	 * */
	private static void comb(int[] score, int[] cal, boolean[] visited, int start, int n, int r, int L) {
		if(r==-1) { //index�� 0�ΰź��� ������ -1�� ��, return �� ��!
			if(sum_cal <= L) { //���� Į�θ����� ������
				 if(max_score < sum_score) {
					 max_score = sum_score;
				}
			}
			return; //���� ���� ��ŭ�̸� return
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
