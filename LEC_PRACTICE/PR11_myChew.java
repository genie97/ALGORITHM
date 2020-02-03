
public class PR11_myChew {

	public static void main(String[] args) {
		int [] queue = new int[30];
		int rear = -1, front = -1; //큐를 위한 변수
		int [] getCandy = new int[30];
		int round = 1; // 마이쮸 받기 한 라운드
		int mychew = 20; // 현재 가지고 있는 마이쮸 개수
		int ans = 0;
		// round 1 (1을 큐에 넣는다)
		queue[++rear] = round;
		getCandy[round]++;
		mychew-=getCandy[round]; // 현재 남은 마이쮸
		
		System.out.println("큐에 있는 사람 수: " + (rear-front));
		System.out.println("[나누어준 사탕 수]");
		System.out.println(round + "번 사람: " + getCandy[round] + "개");
		System.out.println("현재 나누어 준 사탕 수: " + (20-mychew));
		System.out.println();
		
		int dq; // 큐에서 꺼낸 수
		while(true) {
			dq = queue[++front];
			queue[++rear] = dq; //최근에 뺀 값 넣기
			getCandy[dq]++;
			mychew-=getCandy[dq];
			queue[++rear] = ++round; // 최근에 넣었던 값 다음 값
			getCandy[round]++;
			mychew-=getCandy[round];
			
			if(mychew <= 0) {
				ans = dq;
				break;
			}
			
			System.out.println("큐에 있는 사람 수: " + (rear-front));
			System.out.println("[나누어준 사탕 수]");
			for (int i = 1; i <= round; i++) {
				System.out.println(i + "번 사람: " + getCandy[i] + "개");
			}
			System.out.println("현재 나누어 준 사탕 수: " + (20-mychew));
			System.out.println();
			
		}
		System.out.println("큐에 있는 사람 수: " + (rear-front));
		System.out.println("[나누어준 사탕 수]");
		for (int i = 1; i <= round; i++) {
			System.out.println(i + "번 사람: " + getCandy[i] + "개");
		}
		System.out.println("현재 나누어 준 사탕 수: " + (20-mychew));
		System.out.println();
		
		System.out.println(ans+"번 사람이 20번째 마이쮸를 받는다!");
	}

}
