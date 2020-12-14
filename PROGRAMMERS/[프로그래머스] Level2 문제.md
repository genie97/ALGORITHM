## [프로그래머스] Level2 문제 풀이

##### [다리를 지나는 트럭](https://programmers.co.kr/learn/courses/30/lessons/42583)



##### [주식가격](https://programmers.co.kr/learn/courses/30/lessons/42584)



##### [스킬트리](https://programmers.co.kr/learn/courses/30/lessons/49993)



##### [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586)



##### [멀쩡한 사각형](https://programmers.co.kr/learn/courses/30/lessons/62048)



##### [124 나라의 숫자](https://programmers.co.kr/learn/courses/30/lessons/12899)



##### [프린터](https://programmers.co.kr/learn/courses/30/lessons/42587)

```java
import java.util.*;
// 인쇄 대기 목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼낸다
// 나머지 인쇄 대기 목록에서 J보다 중요도가 높은 문서가 하나라도 존재하면 맨뒤로 보낸다
// 그렇지 않으면 J를 인쇄한다
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
		boolean[] isPrint = new boolean[priorities.length];
		
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < priorities.length; i++) {
			q.add(new int[] { i, priorities[i] });
		}
		while (!q.isEmpty()) {
			int[] doc = q.peek(); // 가장 앞에 있는 문서를 대기목록에서 꺼낸다
			boolean isHigh = true;
			for (int i = 0; i < priorities.length; i++) {
				if(isPrint[i]) 
					continue;
				if (doc[1] < priorities[i] && doc[0] != i) {
					q.poll();
					q.add(doc);
					isHigh = false;
					break;
				}
			}
			if (!isHigh)
				continue;
			// 그게 아니면
			q.poll();
			isPrint[doc[0]] = true;
			
			if (doc[0] == location)
				return answer;
			answer++;
		}

		return answer;
    }
}
```



##### [카카오프렌즈 컬러링북](https://programmers.co.kr/learn/courses/30/lessons/1829)

```java
#include <vector>
#include <queue>
#include <algorithm>
#include<functional>
using namespace std;

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

vector<vector<int>> map = { { 1,1,1,0 },{ 1,2,2,0 },{ 1,0,0,1 },{ 0,0,0,1 },{ 0,0,0,3 },{ 0,0,0,3 } };

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
vector<int> solution(int m, int n, vector<vector<int>> picture) {
	int number_of_area = 0;
	int max_size_of_one_area = 0;

	vector<int> answer(2);
	answer[0] = number_of_area;
	answer[1] = max_size_of_one_area;

	vector<int> area; //면적 값 저장하는 곳
	queue<pair<int, int>> q; //bfs를 위한 큐
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (picture[i][j] <= 0) continue;
			else {
				q.push({ i,j });
				int color = picture[i][j]; //어떤 색인지 구역 확인하려고
				picture[i][j] = -1; //방문했다 체크
				int cnt = 1; //영역 세기 위한 변수
				while (!q.empty()) {
					int x = q.front().first; //어디 위친지
					int y = q.front().second; //어디 위친지
					q.pop();
					for (int i = 0; i < 4; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
						if (picture[nx][ny] == color) {
							q.push({ nx, ny });
							picture[nx][ny] = -1; //방문 체크
							cnt++;
						}
					}
				}
				area.push_back(cnt);
			}
		}
	}
	sort(area.begin(), area.end(), greater<int>());
	number_of_area = area.size();
	max_size_of_one_area = area[0];
	answer[0] = number_of_area;
	answer[1] = max_size_of_one_area;

	return answer;
}
```



##### [삼각 달팽이](https://programmers.co.kr/learn/courses/30/lessons/68645)



##### [문자열 압축](https://programmers.co.kr/learn/courses/30/lessons/60057)

```java
class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
		if(s.length()==1) { // 문자열의 길이는 1이상 1000이하이다!
			answer = 1;
			return 1;
		}
		int len = s.length() / 2; // 압축시도 길이 (1~전체길이의 절반)
		for (int l = 1; l <= len; l++) {
			String chkStr = "";
			int i = 0;
			for (; i < s.length() - l + 1;) {
				String tmp = s.substring(i, i + l);
				int cnt = putStr(tmp, i + l, s, l);
				if (cnt - 1 == 0) {
					chkStr += tmp;
					i += l;
					continue;
				}
				chkStr += (cnt + tmp);
				i += (cnt * l);
			}
			chkStr += (s.substring(i));
			answer = Math.min(answer, chkStr.length());
		}

		return answer;
	}

	static int putStr(String tmp, int idx, String s, int len) {
		// 마지막 도달
		if (idx >= s.length())
			return 1;

		int cnt = 1;
		for (int i = idx; i < s.length() && i + len <= s.length(); i += len) {
			if (!s.substring(i, i + len).equals(tmp))
				break;
			cnt++;
		}
		return cnt;
	}
}
```



##### [괄호 변환](https://programmers.co.kr/learn/courses/30/lessons/60058)

```java
import java.util.Stack;
class Solution {
    public String solution(String p) {
     String answer = "";

		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
		if (p == "")
			return "";
		// 이미 올바른 문자열 이라면 그대로 return 한다
		if (rightString(p))
			return p;

		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u,v로 분리한다
		// u는 균형잡힌 괄호 문자열로 더 이상 분리 할 수 없다
		// v는 빈 문자열이 될 수 있다.
		String u = makeBalance(p);
		int idx = u.length();
		String v = p.substring(idx);

		// 3. u가 올바른 문자열이면 다시 y,v로 나눈다
		// 3-1. u에 이어붙힌 후 반환
		if (rightString(u)) {
			u += solution(v);
			return u;
		}

		// 4. u가 올바른 문자열이 아니라면?
		else {
			// 4-1. 빈문자열에 '(' 붙이기
			String tmp = "";
			tmp += "(";
			// 4-2. 문자열 v에 대해 1부터 재귀 수행
			tmp += solution(v);
			// 4-3. ')' 다시 붙이기
			tmp += ")";
			// 4-4. u의 처음, 마지막 문자 제거, 나머지 문자열의 괄호 방향 뒤집어서 뒤에 붙이기
			u = u.substring(1); // 처음 문자 제거
			u = u.substring(0, u.length()-1); // 나머지 문자 제거
			for (int i = 0; i < u.length(); i++) {
				tmp += (u.charAt(i)) == '(' ? ')' : '(';
			}
			answer = tmp;
		}
		// 4-5. 반환
		return answer;
    }
    static boolean rightString(String p) {
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < p.length(); i++) {
			if (s.isEmpty()) {
				s.push(p.charAt(i));
				continue;
			}
			char a = s.peek();
			char b = p.charAt(i);
			if (a == '(' && b == ')')
				s.pop();
			else
				s.push(b);
		}
		if (s.isEmpty())
			return true;
		else
			return false;
	}

	static String makeBalance(String p) {
		int openCnt = 0, closeCnt = 0;
		String tmp = "";
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(') {
				openCnt++;
				tmp += '(';
			}
			if (p.charAt(i) == ')') {
				tmp += ')';
				closeCnt++;
			}
			if (openCnt == closeCnt)
				return tmp;
		}
		return tmp;
	}
}
```



##### [가장 큰 수](https://programmers.co.kr/learn/courses/30/lessons/42746)

```c++
#include <string>
#include <algorithm>
#include <vector>
#include <sstream>
#include <iostream>
using namespace std;
bool comp(int a, int b){
    string one = to_string(a)+to_string(b);
    string two = to_string(b)+to_string(a);
    if(one<=two) return false;
    else return true;
}
string solution(vector<int> numbers) {
    sort(numbers.begin(), numbers.end(), comp);
    string answer = "";
    for(int i=0;i<numbers.size();i++){
        answer+=to_string(numbers[i]);
    }
    if(atoi(answer.c_str())==0)
        answer="0";
    return answer;
}
```



##### [더 맵게](https://programmers.co.kr/learn/courses/30/lessons/42626)



##### [소수 찾기](https://programmers.co.kr/learn/courses/30/lessons/42839)

```java
import java.util.*;

class Solution {
    static Set<Integer> s;
	static boolean[] selected;

	static boolean[] prime;

	static void isPrime() {
		for (int i = 2; i < prime.length; i++) {
			prime[i] = true;
		}
		for (int i = 2; i < prime.length; i++) {
			if (!prime[i])
				continue;

			for (int j = 2 * i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}
	}

	

	public int solution(String numbers) {
		int total = (int) Math.pow(10, numbers.length());
		prime = new boolean[total];
		isPrime();
		s = new HashSet<>();

		for (int i = 0; i < numbers.length(); i++) {
			selected = new boolean[numbers.length()];
			dfs(0, i + 1, numbers, "");
		}

		return s.size();
	}

	static void dfs(int cnt, int len, String numbers, String value) {
		if (cnt == len) {
			int v = Integer.parseInt(value);
			if (prime[v]) {
				s.add(v);
			}
			return;
		}

		for (int i = 0; i < numbers.length(); i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			String origin = value;
			value += numbers.charAt(i);
			dfs(cnt + 1, len, numbers, value);
			value = origin;
			selected[i] = false;
		}
	}
}
```



##### [조이스틱](https://programmers.co.kr/learn/courses/30/lessons/42860)



##### [큰 수 만들기](https://programmers.co.kr/learn/courses/30/lessons/42883)



##### [H-Index](https://programmers.co.kr/learn/courses/30/lessons/42747)



##### [전화번호 목록](https://programmers.co.kr/learn/courses/30/lessons/42577)



##### [구명보트](https://programmers.co.kr/learn/courses/30/lessons/42885)



##### [위장](https://programmers.co.kr/learn/courses/30/lessons/42578)



##### [타겟 넘버](https://programmers.co.kr/learn/courses/30/lessons/43165)

```java
class Solution {
    static int ans;
    static boolean[] visit;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        visit = new boolean[numbers.length];
        dfs(numbers, target, 0, 0);
        answer = ans;
        return answer;
    }
    static void dfs(int[] numbers, int target, int cnt, int sum){
        if(cnt == numbers.length){
            if(sum == target){
                ans++;
            }
            return;
        }
        dfs(numbers, target, cnt+1, sum-numbers[cnt]);
        dfs(numbers, target, cnt+1, sum+numbers[cnt]);
    }
}
```



##### [카펫](https://programmers.co.kr/learn/courses/30/lessons/42842)



##### [쿼드압축 후 개수 세기](https://programmers.co.kr/learn/courses/30/lessons/68936)



##### [가장 큰 정사각형 찾기](https://programmers.co.kr/learn/courses/30/lessons/12905)



##### [단체사진 찍기](https://programmers.co.kr/learn/courses/30/lessons/1835)



##### [튜플](https://programmers.co.kr/learn/courses/30/lessons/64065)



##### [올바른 괄호](https://programmers.co.kr/learn/courses/30/lessons/12909)



##### [다음 큰 숫자](https://programmers.co.kr/learn/courses/30/lessons/12911)



##### [땅따먹기](https://programmers.co.kr/learn/courses/30/lessons/12913)



##### [폰켓몬](https://programmers.co.kr/learn/courses/30/lessons/1845)



##### [숫자의 표현](https://programmers.co.kr/learn/courses/30/lessons/12924)



##### [이진 변환 반복하기](https://programmers.co.kr/learn/courses/30/lessons/70129)



##### [최댓값과 최솟값](https://programmers.co.kr/learn/courses/30/lessons/12939)



##### [최솟값 만들기](https://programmers.co.kr/learn/courses/30/lessons/12941)



##### [피보나치 수](https://programmers.co.kr/learn/courses/30/lessons/12945)

```java
class Solution {
    static int[] dp;
    public int solution(int n) {
        int answer = 0;
        dp = new int[n + 1];

        for(int i = 0; i < dp.length; i++){
            dp[i] = -1;
        }
        
        dp[0] = 0;
        dp[1] = 1;
        fibo(n);
        
        answer = dp[n] % 1234567;
        return answer;
    }
    static int fibo(int n){
        if(dp[n] != -1) 
            return dp[n] % 1234567;
        return dp[n] = (fibo(n-1) + fibo(n-2)) % 1234567;
    }
}
```



##### [수식 최대화](https://programmers.co.kr/learn/courses/30/lessons/67257)



##### [행렬의 곱셈](https://programmers.co.kr/learn/courses/30/lessons/12949)

```java
import java.util.*;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				for(int k = 0; k < arr1[0].length; k++){
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
			}
		}
        
		return answer;
    }
}
```



##### [JadenCase 문자열 만들기](https://programmers.co.kr/learn/courses/30/lessons/12951)

```java
class Solution {
    public String solution(String s) {
        String answer = "";
        s = s.toLowerCase();
        
		boolean isFirst = true;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
            answer += isFirst ? Character.toUpperCase(ch) : ch;
            isFirst = (ch == ' ') ? true : false;
		}
        return answer;
    }
}
```



##### [N개의 최소공배수](https://programmers.co.kr/learn/courses/30/lessons/12953)



##### [짝지어 제거하기](https://programmers.co.kr/learn/courses/30/lessons/12973)



##### [소수 만들기](https://programmers.co.kr/learn/courses/30/lessons/12977)



##### [점프와 순간 이동](https://programmers.co.kr/learn/courses/30/lessons/12980)



##### [영어 끝말잇기](https://programmers.co.kr/learn/courses/30/lessons/12981)



##### [예상 대진표](https://programmers.co.kr/learn/courses/30/lessons/12985)



##### [[1차\] 뉴스 클러스터링](https://programmers.co.kr/learn/courses/30/lessons/17677)



##### [[1차\] 프렌즈4블록](https://programmers.co.kr/learn/courses/30/lessons/17679)

```java
import java.io.*;
import java.util.*;

class Solution {
    static char[][] map;
	static boolean[][] bomb;   
    Queue<Integer> q = new LinkedList<>();
    Map<String, String> map1 = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
		map = new char[board.length][board[0].length()];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}

		boolean isOver = false;

		while (!isOver) {
			isOver = true;
			bomb = new boolean[map.length][map[0].length];

			for (int i = 0; i < map.length - 1; i++) {
				for (int j = 0; j < map[i].length - 1; j++) {
					if (map[i][j] != '.' && fourBlock(i, j)) { // 블록이 있는 경우만 확인하기
						isOver = false;
						bomb[i][j] = true;
						bomb[i + 1][j] = true;
						bomb[i][j + 1] = true;
						bomb[i + 1][j + 1] = true;
					}
				}
			}
			answer += removeFourBlock();
			downBlock();
		}
		return answer;
    }
    static void downBlock() {
		for (int i = map.length - 1; i >= 0; i--) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '.') {
					int ni = i, nj = j;
					while (--ni >= 0) {
						if (map[ni][nj] != '.') {
							map[i][j] = map[ni][nj];
							map[ni][nj] = '.';
							break;
						}
					}
				}
			}
		}
	}
	static int removeFourBlock() {
		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (bomb[i][j]) {
					cnt++;
					map[i][j] = '.'; // 비었다.
				}
			}
		}
		return cnt;
	}
	static boolean fourBlock(int i, int j) {
		char ch = map[i][j];
		return map[i + 1][j] == ch && map[i][j + 1] == ch && map[i + 1][j + 1] == ch;
	} 
}
```



##### [[1차\] 캐시](https://programmers.co.kr/learn/courses/30/lessons/17680)



##### [오픈채팅방](https://programmers.co.kr/learn/courses/30/lessons/42888)



##### [후보키](https://programmers.co.kr/learn/courses/30/lessons/42890)



##### [[3차\] 방금그곡](https://programmers.co.kr/learn/courses/30/lessons/17683)



##### [[3차\] 압축](https://programmers.co.kr/learn/courses/30/lessons/17684)



##### [[3차\] 파일명 정렬](https://programmers.co.kr/learn/courses/30/lessons/17686)



##### [[3차\] n진수 게임](https://programmers.co.kr/learn/courses/30/lessons/17687)





<hr>

### SQL 문제

##### [최솟값 구하기](https://programmers.co.kr/learn/courses/30/lessons/59038)

```sql
-- 코드를 입력하세요
SELECT MIN(DATETIME) AS 시간
FROM ANIMAL_INS
```



##### [동물 수 구하기](https://programmers.co.kr/learn/courses/30/lessons/59406)

```sql
-- 코드를 입력하세요
SELECT COUNT(*) AS count 
FROM ANIMAL_INS;
```



##### [중복 제거하기](https://programmers.co.kr/learn/courses/30/lessons/59408)

```sql
-- 코드를 입력하세요
SELECT COUNT(DISTINCT(NAME)) AS count
FROM ANIMAL_INS;
```



##### [고양이와 개는 몇 마리 있을까](https://programmers.co.kr/learn/courses/30/lessons/59040)

```sql
-- 코드를 입력하세요--
SELECT ANIMAL_TYPE, COUNT(*) AS count
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE;
```



##### [NULL 처리하기](https://programmers.co.kr/learn/courses/30/lessons/59410)

```sql
-- 코드를 입력하세요 --
SELECT ANIMAL_TYPE, (CASE WHEN NAME IS NULL THEN "No name" ELSE NAME END) AS NAME, 
SEX_UPON_INTAKE
FROM ANIMAL_INS
```



##### [동명 동물 수 찾기](https://programmers.co.kr/learn/courses/30/lessons/59041)

```sql
-- 코드를 입력하세요
SELECT DISTINCT NAME, COUNT(NAME) AS COUNT
FROM ANIMAL_INS
GROUP BY NAME
HAVING COUNT >= 2
```



##### [입양 시각 구하기(1)](https://programmers.co.kr/learn/courses/30/lessons/59412)

```sql
-- 코드를 입력하세요--
SELECT HOUR(DATETIME) AS HOUR, COUNT(HOUR(DATETIME)) AS COUNT
FROM ANIMAL_OUTS
GROUP BY HOUR
HAVING HOUR BETWEEN 9 AND 19;
```



##### [루시와 엘라 찾기](https://programmers.co.kr/learn/courses/30/lessons/59046)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME = 'Lucy' OR NAME = 'Ella' OR NAME = 'Pickle'
OR NAME = 'Rogan' OR NAME = 'Sabrina' OR NAME = 'Mitty'
```



##### [이름에 el이 들어가는 동물 찾기](https://programmers.co.kr/learn/courses/30/lessons/59047)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE NAME LIKE '%EL%' AND ANIMAL_TYPE = 'Dog'
ORDER BY NAME
```



##### [중성화 여부 파악하기](https://programmers.co.kr/learn/courses/30/lessons/59409)

```sql
-- 코드를 입력하세요
SET @SEX_UPON := 'X';
SELECT ANIMAL_ID, NAME, 
CASE WHEN (SEX_UPON_INTAKE LIKE 'Spayed%' OR SEX_UPON_INTAKE LIKE 'Neutered%') 
THEN @SEX_UPON := 'O'
ELSE @SEX_UPON := 'X'END AS 중성화
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
```



##### [DATETIME에서 DATE로 형 변환](https://programmers.co.kr/learn/courses/30/lessons/59414)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME,'%Y-%m-%d') AS 날짜 
FROM ANIMAL_INS 
ORDER BY ANIMAL_ID
```

