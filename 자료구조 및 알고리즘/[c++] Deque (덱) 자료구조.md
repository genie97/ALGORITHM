## [c++] Deque (덱) 자료구조

- Double Ended Queue
- 앞과 뒤에서 삽입과 삭제가 가능한 자료구조
- Stack의 FIFO방식과 Queue의 LIFO방식 둘 다 사용할 수 있다

------

* 특징
  1. 크기가 가변적이다.
  2. 앞과 뒤에서 삽입 및 삭제가 용이하다
  3. 중간 데이터 삽입 및 삭제는 어렵다
  4. 랜덤 접근이 가능하다



- 주요 함수

  1. dq.push_back(a) 

     > "a를 맨 뒤에 삽입"

  2. dq.push_front(a)

     > "a를 맨 앞에 삽입"

  3. dq.pop_back()

     > "맨 뒤의 원소 꺼내기"

  4. dq.pop_front()

     > "맨 앞의 원소 꺼내기"