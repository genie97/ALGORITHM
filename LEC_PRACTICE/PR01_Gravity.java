import java.util.Arrays;
public class PR01_Gravity {
	public static void main(String[] args) {
		int[] box = {7,4,2,0,0,6,0,7,0}; //9��
		int[][] map = new int[8][9]; // 8 * 9 ���

//		1.  2���� �迭�� �����
		for (int c = 0; c < map.length; c++) {
			for (int r = map.length-1; r >=0 ; r--) {
				if(box[c] <= 0) { // �ڼ��� ������ 0���� ������ ��������
					break;
				}
				box[c]--;
				map[r][c]=1;
			}
		}
		
//		2. ȸ��
		int[][] room = new int[9][8];
		for (int i = 0; i < room.length; i++) { // 9
			for (int j = 0; j < room[i].length; j++) { //8
				room[i][j] = map[map.length-1-j][i];
			}
		}
				
//		3. �߷¹߻� => �� �ڽ��� ������ ��� (�ִ�) ���
		int max = 0;
		for (int i = room.length-1; i >= 0; i--) {
			for (int j = 0; j < room[i].length; j++) {
				if(room[i][j]==1) {
					int diff = make_gravity(i, j, room);
					if(diff > max) {
						max = diff;
					}
				}
			}
		}
//		for (int i = 0; i < room.length; i++) {
//			for (int j = 0; j < room[i].length; j++) {
//				System.out.print(room[i][j]+ " ");
//			}
//			System.out.println();
//		}
		System.out.println("������ " + max + "�Դϴ�.");
		
	} // end of main
	
	public static int make_gravity(int x, int y, int[][] map) {
		int diff = 0;
		for (int i = x+1; i < map.length; i++) {
			if(map[i][y]==1 || i>= map.length) break;
			diff++;
			map[i-1][y] = 0;
			map[i][y] = 1;
		}
		return diff;
	}
	
} // end of class

