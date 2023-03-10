package UserDefinedDataTypeToPriorityQueue;

import java.time.LocalTime;
import java.util.PriorityQueue;

public class UserDefinedDataTypeToPriorityQueue {
	public static void main(String[]args) {
		PriorityQueue<Job> pq = new PriorityQueue<>();
		String[][] str1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
		
		for(int i = 0; i < str1.length; i++) {
			pq.offer(new Job(str1[i]));
		}
		
		
		System.out.println(pq.toString());
		for(int i = 0; i < str1.length; i++) {
			pq.poll().print();
		}
		
		PriorityQueue<Integer> pqi = new PriorityQueue<>();
		pqi.offer(1);
		pqi.offer(2);
		pqi.offer(3);
		System.out.println(pqi);
	}
}

class Job implements Comparable<Job>{
	LocalTime start;
	LocalTime end;
	
	public int compareTo(Job other) {
		if(end.isBefore(other.end)) return -1;
		else if(end.isAfter(other.end)) return 1;
		else return 0;
	}
	
	Job(String[] work){
		start = LocalTime.parse(work[0]);
		end = LocalTime.parse(work[1]);
		
		if(end.isBefore(LocalTime.of(23, 50))) {
			end.plusMinutes(10);
		}
	}
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public void print() {
		System.out.println("시작 시간은: " + start + "이고, 끝 시간은: " + end + "입니다.");
	}
}