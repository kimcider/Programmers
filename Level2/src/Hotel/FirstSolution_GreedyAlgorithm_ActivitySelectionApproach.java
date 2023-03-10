package Hotel;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/155651
 */

/*
 * 스케쥴링 문제를 응용. 
 * 
 * 스케쥴링 문제: Greedy 알고리즘을 사용
 * 		원소들을 끝나는 시간을 기준으로 정렬.
 * 		가장 끝나는 시간이 빠른 원소들부터 일정 내에 삽입. 
 * 
 * 위 스케쥴링 알고리즘에서 몇 개의 일정이 있어야 모든 원소를 포함할 수 있는지를 카운트. 
 * 
 * 
 * priority queue를 이용하고,
 * 일정에 넣을 수 없는 원소가 나올 때 마다 다른 큐에 옮기는 방식
 * 
 * 전체 데이터 삽입: nlogn
 * 각 데이터 체크시마다: log n
 * 이 작업을 최소 n 번, 최대 n^2번 수행. 
 * 
 * Time Complexity: O(n^2 log n) 
 * 
 *
 */

import java.time.LocalTime;
import java.util.PriorityQueue;

class FirstSolution_GreedyAlgorithm_ActivitySelectionApproach {
	PriorityQueue<Job> jobQueue;
	PriorityQueue<Job> nextQueue;
    public int solution(String[][] book_time) {
    	jobQueue = new PriorityQueue<>();
    	nextQueue = new PriorityQueue<>();
    	insertWholeJob(book_time);
    	int answer = 1;
    
    	LocalTime prevEndTime = LocalTime.of(0, 0);
    	
    	while(true) {
    		while(jobQueue.isEmpty() == false) {
        		Job now = jobQueue.poll();
        		if(now.start.isBefore(prevEndTime)) {
        			if(nextQueue.isEmpty()) {
        				nextQueue = new PriorityQueue<>();
        			}
        			nextQueue.offer(now);
        		}
        		else {
        			if(now.end.isBefore(LocalTime.of(23, 50))) {
        				prevEndTime = now.getEnd().plusMinutes(10);
        			}else {
        				break;
        			}
        		}
        	}
    		if(nextQueue.isEmpty() && jobQueue.isEmpty()) {
    			break;
    		}
    		while(nextQueue.isEmpty() == false) {
    			jobQueue.offer(nextQueue.poll());
    		}
    		answer++;
    		prevEndTime = LocalTime.of(0, 0);
    	}
    	
    	
        return answer;
    }
    
    public void insertWholeJob(String[][] book_time) {
    	for(int i = 0; i < book_time.length; i++) {
    		Job tempJob = new Job(book_time[i]);
    		jobQueue.offer(tempJob);
    	}
    }
    
    
}

class Job implements Comparable<Job>{
	LocalTime start;
	LocalTime end;
	
	Job(String[] work){
		start = LocalTime.parse(work[0]);
		end = LocalTime.parse(work[1]);
	}
	
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public int compareTo(Job other) {
		if(end.isBefore(other.end)) return -1;
		else if(end.isAfter(other.end)) return 1;
		else return 0;
	}
}