import java.util.Scanner;

public class SWEA1210_D4_Ladder1 {
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int testCase = 1; testCase <= 10; testCase++) {
            int[][] map = new int[101][101];
            int dest_x=0, dest_y=0;
            int TC = sc.nextInt();
            for (int i = 0; i < map.length-1; i++) {
                for (int j = 0; j < map[i].length-1; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j]==2) {
                        dest_x = i;
                        dest_y = j;
                    }
                }
            }
            move(map, dest_x ,dest_y);
            System.out.println("#"+TC + " " + ans); 
        }
    }
 
    private static void move(int[][] map, int dest_x, int dest_y) {
        if(dest_x == 0) {
            ans = dest_y;
            return;
        }
        map[dest_x][dest_y]=0;
        if(dest_y != 0 && map[dest_x][dest_y-1]==1) {
            move(map, dest_x, dest_y-1);
        }
        else if(dest_y != 99 && map[dest_x][dest_y+1]==1) {
            move(map, dest_x, dest_y+1);
        }
        else
            move(map, dest_x-1, dest_y);    
        map[dest_x][dest_y]=1;
    }
 
}