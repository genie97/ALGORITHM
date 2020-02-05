import java.util.Scanner;
 
public class SWEA3809_D3_화섭이의정수나열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            String num ="";
             
            for (int i = 0; i < N; i++) {
                int a = sc.nextInt();
                num+=a;
            }
            int chNum = 0;
            while(true) {
                if(!num.contains(""+chNum)) break;
                chNum++;
            }
            System.out.println("#" + tc + " " + chNum);
        }
    }
}
