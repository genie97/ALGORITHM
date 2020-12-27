## [프로그래머스] Level2 문제 풀이

##### [다리를 지나는 트럭](https://programmers.co.kr/learn/courses/30/lessons/42583)

```java
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = -1;
		Queue<int[]> q = new LinkedList<>();
		int idx = 0;

		while (true) {
			answer++;

			Queue<int[]> tmp = new LinkedList<>();
			int sum = 0;

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				tmp.add(new int[] { cur[0], cur[1] + 1 });
				sum += cur[0];
			}
			q = tmp;

			if (idx == truck_weights.length && q.isEmpty())
				break;

			if (!q.isEmpty() && q.peek()[1] == bridge_length) {
				sum -= q.peek()[0];
				q.poll();
			}

			if (idx < truck_weights.length && (q.isEmpty() || sum + truck_weights[idx] <= weight)) {
				q.add(new int[] { truck_weights[idx++], 0 });
			}

		}
		return answer;
    }
}
```

```java
import java.util.*;

class Solution {
    static class Truck {
        int weight;
        int move;
        
        public Truck(int weight){
            this.weight = weight;
            this.move = 1;
        }
        
        public void moving(){
            this.move++;
        }
        
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> arriveQ = new LinkedList<>();
        
        for(int w : truck_weights){
            waitQ.offer(new Truck(w));
        }
        
        int time = 0;
        int cur_weight = 0;
        
        while(!waitQ.isEmpty() || !arriveQ.isEmpty()){
            time++;
            // 다리를 건너고 있는 트럭이 없는 경우 추가
            if(arriveQ.isEmpty()){
                Truck t = waitQ.poll();
                cur_weight += t.weight;
                arriveQ.offer(t);
                continue;
            }
            // 다리를 건너고 있는 트럭이 있는 경우 이동 시키기
            for(Truck t : arriveQ){
                t.moving();
            }
            
            // 다리를 다 건넌 트럭이 있다면 제외
            if(arriveQ.peek().move > bridge_length){
                Truck t = arriveQ.poll();
                cur_weight -= t.weight;
            }
            
            // 다리를 더 건널 수 있는지 확인
            if(!waitQ.isEmpty() && cur_weight + waitQ.peek().weight <= weight){
                Truck t = waitQ.poll();
                cur_weight += t.weight;
                arriveQ.offer(t);
            }
        }   
        
        return time;
    }
}
```



##### [주식가격](https://programmers.co.kr/learn/courses/30/lessons/42584)

```java
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0; i < prices.length - 1; i++){
            for(int j = i + 1; j < prices.length; j++){
                answer[i]++;
                if(prices[i] > prices[j]){
                    break;
                }
            }
        }
        return answer;
    }
}
```



##### [스킬트리](https://programmers.co.kr/learn/courses/30/lessons/49993)

```java
class Solution {
    static int[] skills_order = new int[26];
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i = 0; i < skill.length(); i++){
            skills_order[skill.charAt(i)-'A'] = i + 1;
        }
        for(int i = 0; i < skill_trees.length; i++){
            skill = skill_trees[i];
            int prev = -1;
            int cur = 0;
            boolean isPossible = true;
            for(int j = 0; j < skill.length(); j++){
                if(skills_order[skill.charAt(j)-'A'] == 0) 
                    continue;
                prev = cur;
                cur = skills_order[skill.charAt(j)-'A'];
                if(prev + 1 != cur)
                    isPossible = false;
            }
            if(isPossible) 
                answer++;
        }
        return answer;
    }
}
```

```java
// 정규식 사용하는 법
// [^abc] : abc를 제외한 나머지 글자
// 시간적으로는 위에 코드가 더 빠르지만 정규식 개념을 알아두자!

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
       
        for(int i = 0; i < skill_trees.length; i++){
            skill_trees[i] = skill_trees[i].replaceAll("[^" + skill + "]", "");
            if(skill.indexOf(skill_trees[i]) == 0) 
                answer++;
        }
        return answer;
    }
}
```



##### [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586)

```java
import java.util.*;

class Solution {
    public static class work {
		int remains;
		int speeds;

		work(int remains, int speeds) {
			this.remains = remains;
			this.speeds = speeds;
		}

		public void passedDay() {
			this.remains -= this.speeds;
		}
	}
    
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();

		Queue<work> q = new LinkedList<>();
		for (int i = 0; i < progresses.length; i++) {
			q.add(new work(100 - progresses[i], speeds[i]));
		}
		while (!q.isEmpty()) {
			for (work w : q) {
				w.passedDay();
			}
			int cnt = 0;
			while (!q.isEmpty() && q.peek().remains <= 0) {
				q.poll();
				cnt++;
			}
			if (cnt == 0)
				continue;
			list.add(cnt);
		}
		return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
}
```

```java
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
		int[] dayOfend = new int[100];
		int day = -1;
		for (int i = 0; i < progresses.length; i++) {
			while (progresses[i] + (day * speeds[i]) < 100) {
				day++;
			}
			dayOfend[day]++;
		}
		return Arrays.stream(dayOfend).filter(i -> i != 0).toArray();
	}
}
```



##### [멀쩡한 사각형](https://programmers.co.kr/learn/courses/30/lessons/62048) :x:



##### [124 나라의 숫자](https://programmers.co.kr/learn/courses/30/lessons/12899) :x:



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

```java
class Solution {
    public int[] solution(int n) {
        int size = n * (n + 1) / 2;
		int[] answer = new int[size];
		int len = n;
		int cx = -1;
		int cy = 0;

		int[][] map = new int[n][n];

		outer: for (int num = 1; num <= size;) {

			for (int i = cx + 1; i <= cx + len; i++) {
				map[i][cy] = num++;
				if (num > size)
					break outer;
			}
			cx += len;

			len--;

			for (int i = cy + 1; i <= cy + len; i++) {
				map[cx][i] = num++;
				if (num > size)
					break outer;
			}
			cy += len;
			len--;

			for (int i = cx - 1, j = cy - 1; i >= cx - len && j >= cy - len; i--, j--) {
				map[i][j] = num++;
				if (num > size)
					break outer;
			}
			cx -= len;
			cy -= len;
			len--;
		}

		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				answer[idx++] = map[i][j];
			}
		}
		return answer;
    }
}
```

```java
class Solution {
    public int[] solution(int n) {
        int[] answer = new int[(n * (n + 1)) / 2];
		int[][] map = new int[n][n];

		int x = -1, y = 0;
		int num = 1;
		for (int i = 0; i < n; i++) { // 이동이 총 n번
			for (int j = i; j < n; j++) {
				if (i % 3 == 0) { // 세로 이동
					++x; 
				} else if (i % 3 == 1) { // 가로 이동
					++y;
				} else { // 대각선 이동
					--x;
					--y;
				}
				map[x][y] = num++;
			}
		}

		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				answer[idx++] = map[i][j];
			}
		}

		return answer;
    }
}
```



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

```java
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i : scoville) {
			pq.add(i);
		}
		
		while (pq.peek() < K) {
			if (pq.size() == 1)
				return -1;
			int first = pq.poll();
			int second = pq.poll();
			int cur_scoville = first + (second * 2);
			pq.add(cur_scoville);
			answer++;
		}
		return answer;
    }
}
```



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



##### [조이스틱](https://programmers.co.kr/learn/courses/30/lessons/42860) :x:



##### [큰 수 만들기](https://programmers.co.kr/learn/courses/30/lessons/42883) :star:

```java
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
		int idx = 0;
        
		for (int i = 0; i < number.length() - k; i++) {
			int max = 0;
			for (int j = idx; j <= i + k; j++) {
				int num = number.charAt(j) - '0';
				if (max < num) {
					max = num;
					idx = j + 1;
				}
			}
			answer.append(max);
		}

		return answer.toString();
    }
}
```



##### [H-Index](https://programmers.co.kr/learn/courses/30/lessons/42747) :star:

```java
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        System.out.println(citations[citations.length-1]);
        for(h = Math.min(citations[citations.length-1], citations.length); h>=0; h--){
            int count = 0;
            for(int num : citations){
                if(num >= h){
                    count++;   
                }
            }
            if(count >= h){
                break;
            }
        }
        return h;
    }
}
```

```java
public int solution(int[] citations) {
        Arrays.sort(citations);

        int max = 0;
        for(int i = citations.length-1; i > -1; i--){
            int min = (int)Math.min(citations[i], citations.length - i);
            if(max < min) max = min;
        }

        return max;
}
```



##### [전화번호 목록](https://programmers.co.kr/learn/courses/30/lessons/42577)

```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, new Comparator<String>(){
           public int compare(String a, String b){
               return Integer.compare(a.length(), b.length());
           } 
        });
        
        for(int i = 0; i < phone_book.length; i++){
            for(int j = i + 1; j < phone_book.length; j++){
                if(phone_book[j].startsWith(phone_book[i])){
                    return false;
                }
            }
        }
        return answer;
    }
}
```



##### [구명보트](https://programmers.co.kr/learn/courses/30/lessons/42885)

```java
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
		int[] w = new int[241];

        for (int i : people) {
			w[i]++;
		}
        for (int i = 0; i < people.length; i++) {
			if (w[people[i]] <= 0)
				continue;
			w[people[i]]--;
			int remain = limit - people[i];
			while (remain >= 40) {
				if (w[remain] > 0) {
					w[remain]--;
					break;
				} else {
					remain--;
				}
			}
			answer++;
		}
        
		return answer;
    }
}
```



##### [위장](https://programmers.co.kr/learn/courses/30/lessons/42578)

```java
import java.util.*;

class Solution {
    
    public int solution(String[][] clothes) {
		Map<String, Integer>map = new HashMap<>();

		for (int i = 0; i < clothes.length; i++) {
			if (!map.containsKey(clothes[i][1])) {
				map.put(clothes[i][1], 1);
			} else {
				int v = map.get(clothes[i][1]);
				map.put(clothes[i][1], v + 1);
			}
		}
        
        int sum = 1;    
        for(String key : map.keySet()){
            sum *= (map.get(key) + 1); // 해당 의상에 대한 옷 종류 + 아예 안입는 경우
        }
		return sum-1; // 모든 옷을 안입는 경우는 존재하지 않으므로 -1
	}
}
```



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

```java
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int block = brown + yellow;
        for(int i =(int)(Math.sqrt(block)); i > 0 ; i--){
            if(block % i != 0) continue;
            int nh = i - 2;
            int nw = (block / i) - 2;
            if(nw * nh == yellow){
                answer[0] = nw + 2;
                answer[1] = nh + 2;
                break;
            }
        }
        return answer;
        
    }
}
```



##### [쿼드압축 후 개수 세기](https://programmers.co.kr/learn/courses/30/lessons/68936) :x:



##### [가장 큰 정사각형 찾기](https://programmers.co.kr/learn/courses/30/lessons/12905) :x:



##### [단체사진 찍기](https://programmers.co.kr/learn/courses/30/lessons/1835) :x:

 

##### [튜플](https://programmers.co.kr/learn/courses/30/lessons/64065)

```java
import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] subSet = s.split("},");
		for(int i = 0; i < subSet.length; i++) {
			subSet[i] = subSet[i].replaceAll("[{ || }]", "");
		}
        Arrays.sort(subSet, new Comparator<String>(){
            public int compare(String a, String b){
                return Integer.compare(a.length(), b.length()); 
            }
        });
        
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < subSet.length; i++) {
			String[] num = subSet[i].split(",");
			for (String n : num) {
				if (set.contains(Integer.parseInt(n)))
					continue;
				set.add(Integer.parseInt(n));
                list.add(Integer.parseInt(n));
			}
		}
        
        
        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
}
```



##### [올바른 괄호](https://programmers.co.kr/learn/courses/30/lessons/12909) 

```java
class Solution {
    boolean solution(String s) {
        int[] check = new int[1];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') check[0]++;
            else check[0]--;
            if(check[0] < 0) return false;
        }
        if(check[0] > 0) return false;
        return true;
    }
}
```



##### [다음 큰 숫자](https://programmers.co.kr/learn/courses/30/lessons/12911)

```java
class Solution {
    public int solution(int n) {
        String bn = Integer.toBinaryString(n);
        int cnt = 0;
        for(int i = 0; i < bn.length(); i++){
            if(bn.charAt(i) == '1'){
                cnt++;        
            }
        }
        while(true){
            n++;
            String nbn = Integer.toBinaryString(n);
            int ncnt = 0;
            for(int i = 0; i < nbn.length(); i++){
                if(nbn.charAt(i) == '1'){
                    ncnt++;        
                }
            }
            if(cnt == ncnt) 
                break;
        }ekdma
        return n;
    }
}
```

```java
// bitCount로 세기
class Solution {
    public int solution(int n) {
        int cnt = Integer.bitCount(n);
        
        while(true){
            n++;
            if(cnt == Integer.bitCount(n)) 
                break;
        }
        return n;
    }
}
```

```java
// 비트 연산
class Solution {
    public int solution(int n) {
        int postPattern = n & -n;
        int smallPattern = ((n ^ (n + postPattern)) / postPattern) >> 2;
        return (n + postPattern) | smallPattern;
    }
}
```



##### [땅따먹기](https://programmers.co.kr/learn/courses/30/lessons/12913) :x:



##### [폰켓몬](https://programmers.co.kr/learn/courses/30/lessons/1845)

```java
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> s = new HashSet<>();
        for(int num : nums){
            s.add(num);
        }
        answer = Math.min((nums.length / 2), s.size());
        return answer;
    }
}
```



##### [숫자의 표현](https://programmers.co.kr/learn/courses/30/lessons/12924) :x:



##### [이진 변환 반복하기](https://programmers.co.kr/learn/courses/30/lessons/70129)

```java
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int turn = 0;
        int cnt = 0;
        while(s.length() != 1 || s.charAt(0) != '1'){
            int len = s.length();
            s = s.replaceAll("[0]","");
            
            cnt += (len - s.length());
            
            s = Integer.toBinaryString(s.length());
            turn++;
        } 
        answer[0] = turn;
        answer[1] = cnt;
        
        return answer;
    }
}
```



##### [최댓값과 최솟값](https://programmers.co.kr/learn/courses/30/lessons/12939)

```java
import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            maxV = Math.max(maxV, n);
            minV = Math.min(minV, n);
        }
        
        return minV + " " + maxV;
    }
}
```



##### [최솟값 만들기](https://programmers.co.kr/learn/courses/30/lessons/12941) :x:



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



##### [수식 최대화](https://programmers.co.kr/learn/courses/30/lessons/67257) :x:



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

```java
class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        
        for(int i = 1; i < arr.length; i++){
            int gcd_n = gcd(answer, arr[i]);
            answer = gcd_n * (answer / gcd_n) * (arr[i] / gcd_n);
        }
        
        return answer;
    }
    static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
}
```



##### [짝지어 제거하기](https://programmers.co.kr/learn/courses/30/lessons/12973)

```java
class Solution
{
    public int solution(String s)
    {
        for (int i = 0; i < s.length() - 1;) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				String f = s.substring(0, i);
				String b = s.substring(i + 2);
				s = f + b;
				i = 0;
				continue;
			}
			i++;
		}
		if (s.length() == 0)
			return 1;
		else
			return 0;
    }
}
```



##### [소수 만들기](https://programmers.co.kr/learn/courses/30/lessons/12977)

```java
class Solution {
    static boolean[] isSelected;
    static int ans;
    
    public int solution(int[] nums) {
        ans = 0;
        isSelected = new boolean[nums.length];
        
        dfs(0, 0, 0, nums);
        
        return ans;
    }

    static boolean isPrime(int num){
        if(num == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    
    static void dfs(int cnt, int idx, int sum, int[] nums){
        if(cnt == 3){
            if(isPrime(sum)){
                ans++;
            }
            return;
        }
        for(int i = idx; i < nums.length; i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            dfs(cnt + 1, i, sum + nums[i], nums);
            isSelected[i] = false;    
        }
    }
}
```



##### [점프와 순간 이동](https://programmers.co.kr/learn/courses/30/lessons/12980) :x:



##### [영어 끝말잇기](https://programmers.co.kr/learn/courses/30/lessons/12981)

```java
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> s = new HashSet<>();
        s.add(words[0]);
        
        for(int i = 1; i < words.length; i++){
            if(words[i-1].charAt(words[i-1].length() -1) != words[i].charAt(0) 
               || s.contains(words[i])) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
            s.add(words[i]);
        }

        return answer;
    }
}
```



##### [예상 대진표](https://programmers.co.kr/learn/courses/30/lessons/12985) :x:



##### [[1차\] 뉴스 클러스터링](https://programmers.co.kr/learn/courses/30/lessons/17677) :x:



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



##### [[1차\] 캐시](https://programmers.co.kr/learn/courses/30/lessons/17680) :x:



##### [오픈채팅방](https://programmers.co.kr/learn/courses/30/lessons/42888) :x:



##### [후보키](https://programmers.co.kr/learn/courses/30/lessons/42890) :x:



##### [[3차\] 방금그곡](https://programmers.co.kr/learn/courses/30/lessons/17683)​

```java
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int play_minutes = Integer.MIN_VALUE;
		// A# - H
		// C# - I
		// D# - J
		// F# - K
		// G# - L
		m = m.replaceAll("[A]#", "H");
		m = m.replaceAll("[C]#", "I");
		m = m.replaceAll("[D]#", "J");
		m = m.replaceAll("[F]#", "K");
		m = m.replaceAll("[G]#", "L");

		for (String list : musicinfos) {
			String[] info = list.split(",");
			String[] time = info[1].split(":");
			int minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
			time = info[0].split(":");
			minutes -= (Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
			info[3] = info[3].replaceAll("[A]#", "H");
			info[3] = info[3].replaceAll("[C]#", "I");
			info[3] = info[3].replaceAll("[D]#", "J");
			info[3] = info[3].replaceAll("[F]#", "K");
			info[3] = info[3].replaceAll("[G]#", "L");

			int len = info[3].length();

			int replay = minutes / len;
			int remain = minutes % len;

			String melody = "";

			for (int i = 0; i < replay; i++) {
				melody += info[3];
			}

			for (int i = 0; i < remain; i++) {
				melody += info[3].charAt(i);
			}
                    
			if (melody.contains(m) && play_minutes < minutes) {
                play_minutes = minutes;
				answer = info[2];
			}
		}
		if (answer == "")
			return "(None)";

		return answer;
    }
}
```



##### [[3차\] 압축](https://programmers.co.kr/learn/courses/30/lessons/17684)

```java
// 길이가 1인 모든 단어를 포함하도록 사전을 초기화
// 사전에서 현재 입력과 일치하는 가장 긴 문자열 W를 찾는다
// W에 해당하는 사전의 색인 번호를 출력하고 입력에서 W 제거
// 입력에서 처리되지 않은 다음 글자가 남아있다면(C) W+C에 해당하는 단어를 사전에 등록
// 다시 2단계로 돌아감
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();

		Map<String, Integer> dict = new HashMap<>();
		int idx = 1;
		for (int i = 0; i < 26; i++) {
			dict.put((char) (i + 'A') + "", idx++);
		}
		String prev = "";
		String cur = "";
		for (int pos = 0; pos < msg.length();) {
			while (true) {
				if (pos >= msg.length())
					break;
				prev = cur;
				cur += msg.charAt(pos++);
				if (!dict.containsKey(cur)) {
					list.add(dict.get(prev));
					dict.put(cur, idx++);
					cur = "";
					pos--;
					break;
				}
			}
		}

		if (dict.containsKey(cur)) {
			list.add(dict.get(cur));
		}
		return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
}
```



##### [[3차\] 파일명 정렬](https://programmers.co.kr/learn/courses/30/lessons/17686) :x:

```java
// 영문 대소문자, 숫자, 공백, 마침표, 빼기, 부호
// HEAD => 숫자가 아닌 문자
// NUMBER => 숫자는 최대 5글자
// TAIL=> 은 나머지 부분
import java.util.*;

class Solution {
    static class Infos implements Comparable<Infos>{
        String file_name;
        String head;
        String number;
        int idx;
        public Infos(String file_name, String head, String number, int idx){
            this.file_name = file_name;
            this.head = head;
            this.number = number;
            this.idx = idx;
        }
        
        public int compareTo(Infos info){
            if (this.head.equals(info.head)) {
                if(Integer.parseInt(this.number) == Integer.parseInt(info.number)){
                    return Integer.compare(this.idx, info.idx);
                } else {
                    return Integer.compare(Integer.parseInt(this.number), Integer.parseInt(info.number));
                }
            } else {
                return this.head.compareTo(info.head);
            }
        }
    }
    public String[] solution(String[] files) {
        ArrayList<Infos> list = new ArrayList<>();
        for(int idx = 0; idx < files.length; idx++){
            String s = files[idx];
            
            int i = 0;
            for(i = 0;  i < s.length(); i++){
                if(Character.isDigit(s.charAt(i)))
                    break;
            }
            String head = s.substring(0, i);
            s = s.substring(i);
            
            String number = "";
            for(i = 0; i < s.length(); i++){
                if(!Character.isDigit(s.charAt(i))) 
                    break;
                 number += s.charAt(i);
            }
            
            list.add(new Infos(head+s, head.toLowerCase(), number, idx));
        }
        
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i).file_name;
        }
        
        return answer;
    }
}
```



##### [[3차\] n진수 게임](https://programmers.co.kr/learn/courses/30/lessons/17687)

```java
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuilder line = new StringBuilder();
        line.append("0");
        
        int idx = 0;
        while(line.length() <= t*m){
            line.append(convert(idx, n));
            idx++;
        }
      
        for(int i = 0; i < line.length(); i++){
            if(i % m == (p-1) % m){
                answer += line.charAt(i);
            }
            if(answer.length() == t) break;
        }
        
        return answer;
    }
    public String convert(int num, int n){
        String result = "";
        char[] digit ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(num > 0){
            int remain = num % n;
            result = digit[remain] + result;
            num/=n;
        }
        return result;
    }
}
```





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

