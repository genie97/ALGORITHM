import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PR02_BabyGinGame {
	public static int[] permArr;
	public static String line;
	public static int idx;
	public static boolean[] visit;
	public static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		line = sc.next();
		permArr = new int[6];
		visit = new boolean[6];
		idx = 0;
		flag = false;
		make_perm(0);
		if(flag) System.out.println("Baby-Gin");
		else System.out.println("No Baby-Gin");
		
	}

	public static void make_perm(int cnt) {
		if (cnt == 6) {
			if (checkBabyGin(permArr)) {
				flag = true;
			}
	
		} else {
			for (int i = 0; i < line.length(); i++) {
				if (visit[i])
					continue;
				visit[i] = true;
				permArr[idx++] = line.charAt(i) - '0';
				make_perm(cnt + 1);
				idx--;
				visit[i] = false;
			}
		}
	}

	public static boolean checkBabyGin(int[] checkArr) {
		String front = Integer.toString(checkArr[0])+Integer.toString(checkArr[1])+Integer.toString(checkArr[2]);
		String back =  Integer.toString(checkArr[3])+Integer.toString(checkArr[4])+Integer.toString(checkArr[5]);

		if (run(front)) {
			if (run(back)) {
				return true;
			} else if (triplet(back)) {
				return true;
			} else {
				return false;
			}
		} else if (triplet(front)) {
			if (run(back)) {
				return true;
			} else if (triplet(back)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;	
		}
	}

	public static boolean run(String s) {
		if (s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2))
			return true;
		else
			return false;
	}

	public static boolean triplet(String s) {
		if (s.charAt(0) + 1 == s.charAt(1) && s.charAt(1) + 1 == s.charAt(2)) {
			return true;			
		}
		else {
			return false;
		}
	}
}
