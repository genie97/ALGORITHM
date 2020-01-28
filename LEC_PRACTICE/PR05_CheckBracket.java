import java.util.Scanner;
import java.util.Stack;

public class PR05_CheckBracket {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack st = new Stack();
		String line =  sc.next();
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i)=='(') {
				st.push(line.charAt(i));
			}
			else if (line.charAt(i)==')') {
				st.pop();
			}
		}
		if(!st.isEmpty()) {
			System.out.println("괄호의 짝이 맞지 않음");
		}
		else {
			System.out.println("괄호의 짝이 맞음");
		}
		
//		스택을 직접 구현했을 때!
//		char[] s = new char[100];
//		int top = -1;
//		for (int i = 02; i < line.length(); i++) {
//			if(line.charAt(i)=='(') {
//				//s[++top] = line.charAt(i);
//				++top;
//			}
//			else if (line.charAt(i)==')') {
//				top--;
//			}
//		}
//		
//		if(top==-1) {
//			System.out.println("괄호의 짝이 맞음");
//		}
//		else {
//			System.out.println("괄호의 짝이 맞지 않음");
//		}
	}

}
// ()()((()))
// ((()((((()()((()())((())))))
