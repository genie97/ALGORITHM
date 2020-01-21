import java.util.Scanner;

public class SWEA2056_D1_�����ϴ޷� {
	public static void main(String[] args) {
		//��ȿ�ϸ� yyyy/mm/dd����
		//��ȿ���������� -1
		//2���� ��� 28�ϱ����� ����
		int[] upper_date = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			String check_date = sc.next();
			int month = Integer.parseInt(check_date.substring(4, 6));
			if(1 <= month && month <= 12) {
				int date = Integer.parseInt(check_date.substring(6));
				if(0 < date && date <= upper_date[month-1]){
					System.out.println("#" + testCase + " " + check_date.substring(0, 4) + "/" + check_date.substring(4,6) + "/" + check_date.substring(6) );
				}
				else{
					System.out.println("#" + testCase + " " + -1 );
				}
			}
			else {
				System.out.println("#" + testCase + " " + -1 );
			}
		}
	}
}
