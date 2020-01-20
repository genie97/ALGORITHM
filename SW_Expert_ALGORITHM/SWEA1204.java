import java.util.Scanner;
public class SWEA1204{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 0; tc < TC; tc++) {
			int tc_num=0;
			int[] score_map = new int[101];
			tc_num = sc.nextInt();
			for (int i = 0; i < 1000; i++) {
				score_map[sc.nextInt()]++;
			}
			int max=0, maxIndex=0;
			for (int i = 0; i < score_map.length; i++) {
				if(max <= score_map[i]) {
					maxIndex = i;
					max = score_map[i];
				}
			}
			System.out.println("#"+tc_num + " "  + maxIndex);
		}
	}
}
