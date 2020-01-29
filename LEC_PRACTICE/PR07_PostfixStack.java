import java.util.Scanner;

public class PR07_PostfixStack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		char[] stack = new char[20];
		int top = -1;
		
		for (int i = 0; i < str.length; i++) {
			char c = str[i].charAt(0);
			if (c == '(') {
				stack[++top] = c;
			} else if (c == '*' || c == '/') {
				while(top >= 0 && (stack[top]=='*' || stack[top]=='/' ) ) {
					System.out.print(stack[top--]+" ");
				}
				stack[++top] = c;
			} else if (c == '+' || c == '-') {
				while(top >= 0 && (stack[top]=='+' || stack[top]=='-'
						|| stack[top]=='*' || stack[top]=='/') ) {
					System.out.print(stack[top--]+" ");
				}
				stack[++top] = c;
			} else if (c == ')') {
				while(top >= 0 && stack[top]!='(') {
					System.out.print(stack[top--]+" ");
				}
				if(top >= 0 && stack[top]=='(') {
					top--;
				}
			} else {
				System.out.print(str[i]+" ");
			}
		}// end of for
		while(top>=0) {
			System.out.print(stack[top--]+" ");
		}
	}
}
