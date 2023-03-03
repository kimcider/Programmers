package Repainting;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/161989
 */
class Solution {
    public int solution(int n, int m, int[] section) {
    	/*
    	 *  a1~an을 칠해야 하는 영역이 있는 시작과 끝이라고 가정.  
    	 *  벽은 '1, 2, 3, .... a1, a2, a3 ... an, 99999, 100000' 이런 식으로 표기될것.
    	 *  
    	 *   a1에서 롤러의 길이 m만큼의 영역을 칠한다. 
    	 *   a1, a2, a3, a4만큼이 칠해진 후,칠한 횟수를 1 증가. 
    	 *   
    	 *   다시 칠해야 할 벽이 새롭게 정의되면 위와 같이 시작부터 m만큼의 영역을 칠한다. 
    	 *   더 이상 칠해야 할 벽이 없을 때 까지 이를 반복한다. 
    	 *  
    	 */
        int counter = 0;
        int index = 0;
        int head = section[index];
        
        int tail = section[section.length - 1];
        
        while(head <= tail) {
        	head = head + m;
        	counter++;
        	
        	while(index < section.length && section[index] < head) {
        		index++;
        	}
        	if(index >= section.length) {
        		return counter;
        	}
        	head = section[index];
        }
        return counter;
    }
}