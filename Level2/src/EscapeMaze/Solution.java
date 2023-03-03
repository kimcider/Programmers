package EscapeMaze;
import java.util.LinkedList;
import java.util.Queue;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/159993
 */



/*
 * BFS, DFS 공부 
 * 
 */


/*
 * 
 * TODO:
 * 
 * BFS가 최단거리 탐색에 가장 유용할 것이라고 판단. 
 * public void BFS(~~)는 시작지점부터 목적지까지의 BFS를 수행한다. 
 * BFS 과정 중 시작 - 끝까지의 거리가 얼마인지를 반환하도록 수정하면 된다. 
 * 
 * 매 방문마다 이전 Point정보를 저장해두면 될듯 하다. 
 * 
 * 그렇게 한 후, 시작점 - 레버 사이의 거리 + 레버 - 종료점 사이의 거리가 이 문제의 답이 될 것이다. 
 * 
 * TODO: 길이 없는 경우 -1을 리턴해야한다.  
 */
class Solution{
	Point startPoint =  new Point(3,3);
	Point leverPoint = new Point(3,3);
	Point exitPoint;
	public int solution(String[] maps) {
		
		for(int i = 0; i < maps.length; i++) {
			for(int j = 0; j < maps[i].length(); j++) {
				System.out.print(maps[i].charAt(j));
			}
			System.out.println();
		}
		
		System.out.println(startPoint.equals(leverPoint));
		
		
		for(int i = 0; i < maps.length; i++) {
			for(int j = 0; j < maps[i].length(); j++) {
				if(maps[i].charAt(j) == 'S') {
					startPoint = new Point(j, i);
				}
				if(maps[i].charAt(j) == 'L') {
					leverPoint = new Point(j, i);
				}
				if(maps[i].charAt(j) == 'E') {
					exitPoint = new Point(j, i);
				}
			}
		}
		
		String[] tempMaps = maps.clone();
		int get = BFS(tempMaps, startPoint, leverPoint);

		return 0;
	}
	private int BFS(String[] maps, Point start, Point destination) {
		Queue<Point> queue = new LinkedList<Point>();
		Point current = start;
		visited(maps, current);
		while(true) {
			//상하좌우 체쿠 후, inqueue
			//up
			if(current.getY() > 0) {
				Point temp = new Point(current.getX(), current.getY() - 1);
				if(isVisited(maps, temp) == false) {
					queue.add(temp);
				}
			}
			//down
			if(current.getY() < maps.length) {
				Point  temp = new Point(current.getX(), current.getY() + 1);
				if(isVisited(maps, temp) == false) {
					queue.add(temp);
				}
			}
			
			//left
			if(current.getX() > 0) {
				Point temp = new Point(current.getX() - 1, current.getY());
				if(isVisited(maps, temp) == false) {
					queue.add(temp);
				}
			}
			//right
			if(current.getX() < maps[0].length()) {
				Point temp = new Point(current.getX() + 1, current.getY());
				if(isVisited(maps, temp) == false) {
					queue.add(temp);
				}
			}
			
			current = queue.poll();
			if(current.equals(destination)) {
				System.out.println("목적지 도착");
				break;
			}
			
			if(isVisited(maps, current) == false) {
				visited(maps, current);
			}
			
		
		}
		for(int i = 0; i < maps.length; i++) {
			for(int j = 0; j < maps[i].length(); j++) {
				System.out.print(maps[i].charAt(j));
			}
			System.out.println();
		}
		
		

		return 0;
	}
	
	public boolean isVisited(String[] maps, Point point) {
		if(maps[point.getY()].charAt(point.getX()) != 'X') return false;
		else return true;
	}
	public void visited(String[] maps, Point point) {
		maps[point.getY()] = maps[point.getY()].substring(0, point.getX()) + "X" + maps[point.getY()].substring(point.getX() + 1);
	}

}

class Point{
	int x;
	int y;

	public Point() {
		x = 999;
		y = 999;
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
}