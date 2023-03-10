package PostBigNumberSearch;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/154539
 */

/*
 * 본래 숫자가 담겨있는 배열을 X[]라고 칭한다. X는 X1 ~ Xn까지 존재한다.
 * 	배열 X내의 임의의 원소를 Xk라고 칭한다. ( 1 <= k <= n - 1)
 * 찾고자 하는 정답이 되는 배열을 A[]라고 칭한다.
 * 
 * if Xk < X(k+1) ==> A[k] = X(k+1)
 * if Xk == X(k+1) ==> A[k] = A[k+1]
 * 
 * if(Xk > X(k+1)
 * 	이 경우를 해결하기 위해 I[]배열을 도입한다. I[]배열은 A[]배열의 값에 들어오는 Number의 인덱스를 저장한다. 
 * 		Xk > X(k+1)일 경우, A[k+1]을 확인, 
 * 		Xk > A[k+1]일 경우 I[k+1]을 확인. 
 * 			if Xk < N[ I[k+1] ] --> A[k] = N[ I[k+1] ]
 * 			if Xk == N[ I[k+1] ] --> A[k] = A[ I[k+1] ] 
 * 			if Xk > N[ I[k+1] ] --> I[ A[ I[k+1] ] ]을 확인 후, 이 과정을 반복. 
 * 		  
 * 
 */
class Solution {
	int[] answer;
	int[] index;
	int length;
    public int[] solution(int[] numbers) {
    	length = numbers.length;
        init();
        
        answer[length - 1] = -1;
        index[length - 1] = -1;
        
        for(int i = length - 2; i >= 0; i--) {
        	if(numbers[i] < numbers[i + 1]) {
        		answer[i] = numbers[i + 1];
        		index[i] = i + 1;
        	}
        	else if(numbers[i] == numbers[i + 1]) {
        		answer[i] = answer[i + 1];
        		index[i] = index[i + 1];
        	}
        	else {	
        		int next = i + 1;
        		
        		while(numbers[i] > answer[next] && answer[next] != -1) {
        			next = index[next];
        		}
        		if(answer[next] == -1) {
        			answer[i] = -1;
        			index[i] = -1;
        		}
        		else if(numbers[i] < answer[next]) {
        			answer[i] = answer[next];
        			index[i] = index[next];
        		}
        		else if(numbers[i] == answer[next]) {
        			answer[i] = answer[index[next]];
        			index[i] = index[index[next]];
        		}
        	}
        }
        
        return answer;
    }
    public void init() {
    	answer = new int[length];
    	index = new int[length];
    }
}