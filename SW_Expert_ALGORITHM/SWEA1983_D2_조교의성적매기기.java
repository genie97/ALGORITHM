import java.util.Scanner;

public class SWEA1983_D2_조교의성적매기기 {
	public static double[][] score; 
	public static void main(String[] args) {
		String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			score = new double[N][5];
			for (int i = 0; i < score.length; i++) {
				for (int j = 1; j < score[i].length-1; j++) {
					score[i][0] = i;
					score[i][j] = sc.nextInt();
				}
				double total = score[i][1] * 0.35 + score[i][2] * 0.45 + score[i][3] * 0.2;
				score[i][4] = total;
			}

			for (int i = 0; i < score.length; i++) {
				for (int j = i; j < score.length; j++) {
					if(score[j][4] > score[i][4]) { 
						swap(i,j,0);
						swap(i,j,1);
						swap(i,j,2);
						swap(i,j,3);
						swap(i,j,4);
					}
				}
			}
		
			int ans = 0;
			for (int i = 0; i < score.length; i++) {
				if((int)score[i][0]==K-1) {
					ans = i;
					if(ans%2 == 0 && N > 10) ans+=1;
					break;
				}
			}
			if(N==10)
				System.out.println("#" + tc + " " + grade[ans]);
			else {
				System.out.println("#" + tc + " " + grade[ans/(N/10)]);
			}
				
		}
	}

	public static void swap(int i, int j, int idx) {
		double tmp = score[j][idx];
		score[j][idx] = score[i][idx];
		score[i][idx] = tmp;
	}
}
