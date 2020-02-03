import java.util.Scanner;

public class SWEA1225_D2_암호생성기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 0; t < 1; t++) {
			int T = sc.nextInt();
			int[] arr = new int[8];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			int diff = 1;
			int idx = 0;
			boolean flag = false;
			while (true) {
				for (int i = 0; i < 8; i++) {
					if (diff == 6) {
						diff = 1;
					}
					int sub = arr[i] - diff;
					if(sub <= 0) {
						arr[i] = 0;
						idx = i + 1;
						flag = true;
						break;
					}
					arr[i] = sub;
					diff++;
				}
				if(flag)break;
			}
			System.out.println("#" + T);
			for (int i = 0; i < arr.length; i++) {
				if(idx == 8) idx = 0;
				System.out.print(arr[idx] + " ");
				idx++;
			}
			System.out.println();
		}
	}

}
