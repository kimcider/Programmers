package EscapeMaze;
import java.util.LinkedList;
import java.util.Queue;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/159993
 */

/*
 * BFS가 최단거리 탐색에 가장 유용할 것이라고 판단. 
 * public void BFS(~~)는 시작지점부터 목적지까지의 BFS를 수행한다. 
 * BFS 과정 중 시작 - 끝까지의 거리가 얼마인지를 반환한다. 
 * 
 * 시작점 - 레버 사이의 거리 + 레버 - 종료점 사이의 거리가 이 문제의 답이 될 것이다. 
 * 길이 없는 경우 -1을 리턴한다. 
 */


/*  고려사항
 * 
 * visit을 queue에서 제거할 때 수행할 경우 시간초과가 뜬다. 
 * 
 * queue에 넣는 순간, 해당 좌표를 visit할 것이 확정되기 때문에 큐에 넣을 때 visit을 수행하여도 상관이 없다. 
 * 
 *  또한, 큐에 넣을 때 visit을 수행해야 큐에 들어가는 총 원소의 갯수가 줄어들기 때문에 BFS에서는 큐에 넣을 때 visit을 수행하는 것이 timecomplexity를 매우 효과적으로 줄일 수 있다.
 *  
 */
class Solution{
	Point startPoint;
	Point leverPoint;
	Point exitPoint;
	
	public int solution(String[] maps) {
		for(int i = 0; i < maps.length; i++) {
			for(int j = 0; j < maps[i].length(); j++) {
				char character = maps[i].charAt(j);
				
				if(character == 'S') {
					startPoint = new Point(j, i);
				}
				else if(character == 'L') {
					leverPoint = new Point(j, i);
				}else if(character == 'E') {
					exitPoint = new Point(j, i);					
				}
			}
		}
		
		String[] tempMaps = maps.clone();
		int startToLeverLength = BFS(tempMaps, startPoint, leverPoint);
		
		tempMaps = maps.clone();
		int leverToExitLength = BFS(tempMaps, leverPoint, exitPoint);
		
		if(startToLeverLength == -1 )return -1;
		if(leverToExitLength == -1 )return -1;
		
		return startToLeverLength + leverToExitLength;
	}
	
	private int BFS(String[] maps, Point start, Point destination) {
		Queue<Point> queue = new LinkedList<Point>();
		Point current = start;
		visited(maps, current);
		boolean findFlag = false;
		
		while(true) {
			int currentX = current.getX();
			int currentY = current.getY();
			//상하좌우 이동 가능한지 체크 후, Queue에 넣는다.
			//up
			if(currentY > 0) {
				if(isVisited(maps, currentX, currentY - 1) == false) {
					Point temp = new Point(currentX, currentY - 1);
					temp.setCounter(current);
					queue.add(temp);	
					visited(maps, temp);
				}
			}
			//down
			if(currentY < maps.length - 1) {
				if(isVisited(maps, currentX, currentY + 1) == false) {
					Point  temp = new Point(currentX, currentY + 1);
					temp.setCounter(current);
					queue.add(temp);	
					visited(maps, temp);
				}
			}
			
			//left
			if(currentX > 0) {
				if(isVisited(maps, currentX - 1, currentY) == false) {
					Point temp = new Point(currentX - 1, currentY);
					temp.setCounter(current);
					queue.add(temp);
					visited(maps, temp);
				}
			}
			//right
			if(currentX < maps[0].length() - 1) {
				if(isVisited(maps, currentX + 1, currentY) == false) {
					Point temp = new Point(currentX + 1, currentY);
					temp.setCounter(current);
					queue.add(temp);	
					visited(maps, temp);
				}
			}
			current = queue.poll();
			if(current == null) {
				break;
			}
			else if(current.equals(destination)) {
				findFlag = true;
				break;
			}
		}
		
		if(findFlag == true) {
			return current.getCounter();
		}
		return -1;
	}
	
	public boolean isVisited(String[] maps, int x, int y) {
		if(maps[y].charAt(x) != 'X')return false;
		return true;
	}
	public boolean isVisited(String[] maps, Point point) {
		if(maps[point.getY()].charAt(point.getX()) != 'X') return false;
		else return true;
	}
	public void visited(String[] maps, Point point) {
		maps[point.getY()] = maps[point.getY()].substring(0, point.getX()) + "X" + maps[point.getY()].substring(point.getX() + 1);
	}


	class Point{
		int x;
		int y;
		
		int counter;
	
		public Point() {
			x = 999;
			y = 999;
			counter = 0;
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setX(int val) {
			x = val;
		}
		public void setY(int val) {
			y = val;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		
		public boolean equals(Point target) {
			if(x != target.getX()) return false;
			if(y != target.getY()) return false;
			return true;
		}
		
		public int getCounter() {
			return counter;
		}
		public void setCounter(Point before) {
			counter = before.getCounter() + 1;
		}
	}
}
