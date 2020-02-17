## [JAVA] Compare

1. 외부 비교기 (comparator)

   ```java
   Arrays.sort(G, new Comparator<Edge>() {
   			public int compare(Edge e1, Edge e2) {
   				return e1.val - e2.val;
   			}
   		}); // 오름차순 정렬
   ```

   

2. 내부 비교기 (comparable)

   ```java
   public static class Edge implements Comparable<Edge>{ 
   		int v1; // 정점1
   		int v2; // 정점2
   		int weight; // 가중치
   		
   		public Edge(int v1, int v2, int weight) {
   			this.v1 = v1;
   			this.v2 = v2;
   			this.weight = weight;
   		}
   		
   		public int compareTo(Edge e) { 
   			// this와 매개변수 e 의 비교
   			return this.val - e.val; // 가중치 기준으로 오름차순 정렬			
   		}
   	}
   ```

   