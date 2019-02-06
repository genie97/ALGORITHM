## [c++] set container

**set container**

> - 연관 컨테이너 (associative container) 중 하나
> - 노드 기반 컨테이너
> - 균형 이진트리로 구현
> - key라 불리는 원소들의 집합으로 이루어짐 (원소는 key라고 할 수 있음)
> - **key값은 중복이 허용 되지않는다**
> - insert 멤버 함수에 의해 삽입이 되면 원소는 **자동 정렬 된다** (정렬기준: 오름차순)
> - inorder traversal을 통하여 순서대로 출력이 가능하다 (iterator를 사용한다)



**set 사용하기**

> - set 헤더 선언하기
> - set < [Data Type] > [변수 이름] 으로 선언하기



**set의 생성자와 연산자**

> - set <[Data type]> set1 : 기본 선언
> - set <[Data type]> set1 (pred) : pred를 통해 정렬기준을 세운다
> - set <[Data type]> set2 (set1) : set1을 복사한 set2를 선언
> - "==", "!=", "<", ">", "<=", ">=" 의 연산자 사용이 가능하다



**set의 멤버 함수**

> - set1.begin()과 set1.end(): 첫번째 원소부터 마지막 원소까지 interator로 사용 가능
>
> - set1.rbegin()과 set1.rend(): 역으로 출력하고 싶을 때 사용할 수 있다.
>
>   ```c++
>   for (auto i = st.rbegin(); i != st.rend(); i++) {
>   		cout << *i << "\n";
>   	}
>   ```
>
> - set1.clear(): set의 모든 원소 제거
>
> - set1.count(): set의 모든 원소 개수 반환
>
> - set1.empty(): set이 비어있는지 확인
>
> - set1.insert(k): 원소 k를 삽입 
>
>   set1.insert(iter, k): iter가 가리키는 위치부터 k를 삽입할 위치를 탐색한 후 삽입
>
> - set1.erase(iter): iter가 가리키는 원소를 제거하고, 제거한 원소의 다음 원소를 가리키는 반복자를 반환
>
>   set1.erase(start, end): [start, end)범위의 원소를 모두 제거
>
> - set1.find(k): 원소 kfmf 가리키는 반복자를 반환하고, 없다면 s.end()와 같은 반복자를 반환
>
> - set1.swap(set2): set1과 set2를 교환
>
> - set1.upper_bound(k): 원소 k가 끝나는 구간의 반복자
>
> - set1.lower_bound(k): 원소 k가 시작하는 구간의 반복자
>
> - set1.equal_range(k): 원소 k가 시작하는 구간과 끝나는 구간의 반복자 pair 객체를 반환
>
> - set1.value_comp() / set1.key_comp(): 정렬 기준 조건자를 리턴
>
> - set1.size(): set의 크기를 반환
>
> - set1.max_size(): 최대크기(남은 메모리 크기)를 반환