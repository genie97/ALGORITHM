public class PR10_Queue {
	public static int[] queue = new int[3];

	public static void main(String[] args) {
		int front = -1, rear = -1;
		for (int i = 1; i <= 4; i++) {
			if (rear == 2) {
				System.out.println("큐가 꽉찼습니다.");
			} else {
				queue[++rear] = i; // 아이템 삽입
				System.out.println(queue[rear] + " 삽입");
			}
		}

		for (int i = 1; i <= 4; i++) {
			if(front == rear) {
				System.out.println("큐가 비었습니다.");
			}else {
				int d = queue[++front];
				System.out.println(d +" 삭제");
			}
		}
	}
}
© 2020 GitHub, Inc.
